package com.upup.demo.postsystem.ng.post.dao;

import com.upup.demo.postsystem.ng.post.entity.Post;
import com.upup.demo.postsystem.ng.post.model.PostQueryParam;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Post)表数据库访问层
 *
 * @author generate by easycode
 * @since 2021-03-17 21:19:24
 */
public interface PostDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Post findById(Integer id);


    /**
     * 查询指定行数据
     * @param param 查询参数对象
     * @return 对象列表
     */
    List<Post> list(PostQueryParam param);


    /**
     * 新增数据
     *
     * @param post 实例对象
     * @return 影响行数
     */
    int insert(Post post);

    /**
     * 修改数据
     *
     * @param post 实例对象
     * @return 影响行数
     */
    int update(Post post);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}