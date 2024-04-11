package com.lgy.tools.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lgy
 * @since 2019-12-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TbStu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * 班级ID
     */
    private Integer classId;

    /**
     * 专业id
     */
    private Integer specId;

    /**
     * 院系id
     */
    private Integer departId;


    /**
     * 姓名
     */
    private String name;

    /**
     * 注册账号
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 性别
     */
    private String sex;

    /**
     * 用户/管理员
     */
    private Integer isAdmin;

    /**
     * 电话
     */
    private String mobile;

    /**
     * 电子邮箱
     */
    private String email;


    /**
     * 工作
     */
    private String job;

    /**
     * 地址
     */
    private String address;

    /**
     * 头像
     */
    private String stuImg;


    /**
     * 班级
     */
    private TbClass tbClass;

    /**
     * 专业
     */
    private TbSpec tbSpec;

    /**
     * 院系
     */
    private TbDepart tbDepart;

    /**
     * 校友会
     */
    private TbAss tbAss;



    public void setStuImg(String uploadUrl) {
    }

    public Integer getUserId() {
        return null;
    }

    public String getUsername() {
    }

    public String getPassword() {
    }
}
