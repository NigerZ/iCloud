package com.ohh.icloud.common.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

@Data
public class BaseEntity {
    @TableId
    Long id;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    Long createTime;
    @TableField(value = "create_user", fill = FieldFill.INSERT)
    Long createUser;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    Long updateTime;
    @TableField(value = "update_user", fill = FieldFill.INSERT_UPDATE)
    Long updateUser;

    @TableLogic(delval = "1")
    @TableField(value = "is_deleted")
    Integer isDeleted;
}
