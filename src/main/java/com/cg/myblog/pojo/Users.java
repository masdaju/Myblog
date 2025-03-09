package com.cg.myblog.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

/**
 * 
 * @TableName users
 */
@TableName(value ="users")
@Data
public class Users implements Serializable {
    /**
     * 用户id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 角色默认普通用户
     */
    private Object role;

    /**
     * 
     */
    private Date createdAt;

    /**
     * 
     */
    private String avatarUrl;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}