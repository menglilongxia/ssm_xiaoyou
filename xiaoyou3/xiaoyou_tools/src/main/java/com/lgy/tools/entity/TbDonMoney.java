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
 * @since 2020-04-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TbDonMoney implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 筹款id
     */
    @TableId(value = "mon_id", type = IdType.AUTO)
    private Integer monId;

    /**
     * 筹款项目描述
     */
    private String monDesc;

    /**
     * 筹款金额
     */
    private Integer monCount;

    private Integer monHad;

    /**
     * 筹款项目名称
     */
    private String monName;

    /**
     * 筹款人
     */
    private Integer monPer;

    /**
     * 筹款时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime monTime;

    /**
     * 筹款状态
     */
    private Integer monStatus;

    private String monOpinion;

    private TbStu tbStu;


}
