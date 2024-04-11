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
 * @since 2020-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TbMonStu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 项目捐款id
     */
    @TableId(value = "money_id", type = IdType.AUTO)
    private Integer moneyId;

    /**
     * 项目捐款人id
     */
    private Integer userId;

    /**
     * 项目捐款金额
     */
    private Integer moneyCount;

    /**
     * 捐款时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime moneyTime;

    private Integer monId;

    private TbStu tbStu;

    private TbDonMoney tbDonMoney;

    private Integer sumMoney;

    private Integer monStatus;

    private Integer monType;


}
