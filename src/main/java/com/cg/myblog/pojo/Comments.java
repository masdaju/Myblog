package com.cg.myblog.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName comments
 */
@TableName(value ="comments")
@Data
public class Comments implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 文章id
     */
    private Integer postId;

    /**
     * 评论的用户id
     */
    private Integer userId;
    private String username;
    /**
     * 评论内容
     */
    private String content;

    /**
     * 
     */
    private Date createdAt;
    @TableField(exist = false)
//    private Users users;
//    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}