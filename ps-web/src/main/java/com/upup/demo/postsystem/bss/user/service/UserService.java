package com.upup.demo.postsystem.bss.user.service;

import com.google.common.primitives.Ints;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.upup.demo.postsystem.bss.user.model.UserModel;
import com.upup.demo.postsystem.dictionary.Constants;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author tao.li
 * @Date 2021/1/30 上午1:05 //NOTE-UPUP 2021/3/30 上午12:31 : 不用ORM示例。
 */

@Component
public class UserService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    @Qualifier("mysqlDataSource")
    DataSource dataSource;

    @Autowired
    Gson gson;

    /**
     * check whether user has login post system
     */
    public boolean hasUserLogin(String pserId) {
        if (!pserId.startsWith(Constants.PSER_KEY) || !stringRedisTemplate.hasKey(pserId)) {
            return false;
        }
        return true;
    }

    public void loginUser(String pserId, Object data) {
        Gson gson =
            new GsonBuilder().serializeNulls().create();//https://stackoverflow.com/questions/3923759/gson-ignoring-map-entries-with-value-null
        stringRedisTemplate.opsForValue().set(pserId, gson.toJson(data));
        stringRedisTemplate.expireAt(pserId, new Date(System.currentTimeMillis() + Constants.DEFAULT_COOKIE_MAX_AGE * (long) 1000));
    }

    public void logoutUser(String pserId) {
        stringRedisTemplate.delete(pserId);
    }

    //todo : move to dao and use oom
    public Optional<UserModel> findByUserName(String username) {
        String sqlTemplate = "select user.* from user where user.NAME='%s'";
        //NOTE-UPUP 2021/4/16 下午10:45 : 这种写法，当变量销毁时，对于实现了AutoCloseable接口的类 会自动他的调用close()方法。jdk8+
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(String.format(sqlTemplate, username));
            ResultSet resultSet = statement.executeQuery();

            Optional<UserModel> optional = Optional.empty();
            if (resultSet.next()) {
                UserModel userModel = UserModel.builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .phoneNumber(resultSet.getString("PHONE_NUMBER"))
                    .birthday(resultSet.getDate("BIRTHDAY"))
                    .salt(resultSet.getString("SALT"))
                    .createDateTime(resultSet.getTimestamp("CREATE_DATETIME"))
                    .updateDateTime(resultSet.getTimestamp("UPDATE_DATETIME"))
                    .build();
                optional = Optional.of(userModel);
            }
            return optional;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<UserModel> findById(int userId) {
        String sqlTemplate = "select user.* from user where user.ID=%s";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(String.format(sqlTemplate, userId));
            ResultSet resultSet = statement.executeQuery();

            Optional<UserModel> optional = Optional.empty();
            if (resultSet.next()) {
                UserModel userModel = UserModel.builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .phoneNumber(resultSet.getString("PHONE_NUMBER"))
                    .birthday(resultSet.getDate("BIRTHDAY"))
                    .salt(resultSet.getString("SALT"))
                    .createDateTime(resultSet.getTimestamp("CREATE_DATETIME"))
                    .updateDateTime(resultSet.getTimestamp("UPDATE_DATETIME"))
                    .build();
                optional = Optional.of(userModel);
            }
            return optional;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<UserModel> findByIds(int[] userIds) {
        if (userIds == null || userIds.length == 0) {
            return new ArrayList<>();
        }
        String sqlTemplate = "select user.* from user where user.ID in(%s)";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(String.format(sqlTemplate, Ints.join(",", userIds)));
            ResultSet resultSet = statement.executeQuery();
            List<UserModel> list = new ArrayList<>();
            if (resultSet.next()) {
                UserModel userModel = UserModel.builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .phoneNumber(resultSet.getString("PHONE_NUMBER"))
                    .birthday(resultSet.getDate("BIRTHDAY"))
                    .salt(resultSet.getString("SALT"))
                    .createDateTime(resultSet.getTimestamp("CREATE_DATETIME"))
                    .updateDateTime(resultSet.getTimestamp("UPDATE_DATETIME"))
                    .build();
                list.add(userModel);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //todo : move to dao and use oom
    public boolean varifyPassword(int userId, String password) {
        String sqlTemplate = "select count(1) as count from password_authenticate where USER_ID='%s' and PASSWORD='%s'";

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(String.format(sqlTemplate, userId, password));
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt("count") > 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
