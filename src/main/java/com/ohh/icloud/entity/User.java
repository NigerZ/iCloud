package com.ohh.icloud.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ohh.icloud.common.base.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

/*用户实体类*/
@TableName("icloud_user")
@Data
@Accessors(chain = true)
public class User extends BaseEntity {
    //用户账号
    @TableField(value = "username")
    private String username;
    // 用户密码
    @TableField(value = "password")
    private String password;
    // 用户身份证
    @TableField(value = "id_card")
    private String idCard;
    // 用户昵称
    @TableField(value = "nickname")
    private String nickname;
    @TableField(value = "phone")
    private String phone;

}
