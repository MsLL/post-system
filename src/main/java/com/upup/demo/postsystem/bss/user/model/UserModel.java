package com.upup.demo.postsystem.bss.user.model;

import java.util.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author tao.li
 * @Date 2021/1/30 下午4:55
 */
@Getter
@Setter
@Builder
@ToString
public class UserModel {
    Integer id;
    String name;
    String phoneNumber;
    Date birthday;
    String salt;
    Date createDateTime;
    Date updateDateTime;
}
