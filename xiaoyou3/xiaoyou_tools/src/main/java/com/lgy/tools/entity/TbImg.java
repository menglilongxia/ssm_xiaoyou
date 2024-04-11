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
 * @since 2020-03-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TbImg implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 图片id
     */
    @TableId(value = "img_id", type = IdType.AUTO)
    private Integer imgId;

    /**
     * 图片Name
     */
    private String imgName;

    /**
     * 图片Url
     */
    private String imgUrl;

    /**
     * 启用状态
     */
    private Integer imgState;

    /**
     * 上传时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime imgTime;


    public void setImgUrl(String uploadUrl) {
    }

    public void setImgState(int i) {
    }

    public void setImgTime(LocalDateTime now) {
    }
}