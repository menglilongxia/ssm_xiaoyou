package com.lgy.tools.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author lgy
 * @since 2020-02-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TbAss implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 校友会ID
     */
    @TableId(value = "ass_id", type = IdType.AUTO)
    private Integer assId;

    /**
     * 校友会名字
     */
    private String assName;

    /**
     * 校友会简介
     */
    private String assDescription;

    /**
     * 校友会会长
     */
    private Integer assPer;

    /**
     * 联系方式
     */
    private Long assCon;

    /**
     * 校友会交流群
     */
    private Long assGroup;

    /**
     * 审核状态
     */
    private Integer assStatus;

    /**
     * 审核意见
     */
    private String assOpinion;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime assCreatetime;

    /**
     * 审核时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime assExaminetime;

    /**
     * 校友
     */
    private TbStu tbStu;


}
