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
 * @TableName likes
 */
@TableName(value ="likes")
@Data
public class Likes implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 文章id
     */
    private Integer postId;

    /**
     * 
     */
    private Date createdAt;
    @TableField(exist = false)
    private Posts posts;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    public Likes(Integer userId,Integer postId){
        this.userId=userId;
        this.postId=postId;
    }
}