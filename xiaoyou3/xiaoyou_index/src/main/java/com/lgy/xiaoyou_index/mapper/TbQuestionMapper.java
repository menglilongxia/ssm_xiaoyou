package com.lgy.xiaoyou_index.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgy.xiaoyou_index.entity.TbQuestion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lgy
 * @since 2020-02-18
 */
public interface TbQuestionMapper extends BaseMapper<TbQuestion> {

    String getTitleById(Integer outerId);

    IPage<TbQuestion> getQuestionPage(Page page);

    IPage<TbQuestion> getQuestionPageById(Page page,@Param("userId") int userId);

    TbQuestion getById(int id);

    void updateView(Integer id);

    List<TbQuestion> getByTag(@Param("id") Integer id, @Param("result") String result);

    List<TbQuestion> getTopten();

    void updateComment(@Param("parentId") Integer parentId);
}
