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
 * @since 2020-02-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TbAssstu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "tas_id", type = IdType.AUTO)
    private Integer tasId;

    /**
     * 校友会ID
     */
    private Integer assId;

    /**
     * 校友ID
     */
    private Integer userId;


}
