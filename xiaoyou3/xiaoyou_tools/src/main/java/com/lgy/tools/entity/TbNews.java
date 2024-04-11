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
 * @since 2020-02-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TbNews implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 新闻id
     */
    @TableId(value = "news_id", type = IdType.AUTO)
    private Integer newsId;

    /**
     * 新闻标题
     */
    private String newsTittle;

    /**
     * 新闻内容
     */
    private String newsContent;

    /**
     * 新闻作者
     */
    private Integer newsUser;

    /**
     * 发表时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime newsTime;

    /**
     * 新闻作者实体
     */
    private TbStu tbStu;


    public void setNewsTime(LocalDateTime now) {
    }

    public void setNewsUser(Object userId) {
    }
}
