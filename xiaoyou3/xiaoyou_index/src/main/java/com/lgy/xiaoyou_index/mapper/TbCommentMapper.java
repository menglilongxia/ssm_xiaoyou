package com.lgy.xiaoyou_index.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lgy.xiaoyou_index.entity.TbComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 */
public interface TbCommentMapper extends BaseMapper<TbComment> {

    String getContentById(Integer outerid);

    TbComment getParentById(Integer id);

    int getParentIdById(Integer id);

    List<TbComment> getById(Integer id);

    void updateCommentCount(@Param("parentId") int parentId);

    List<TbComment> getCommentByid(@Param("id") Integer id,@Param("userId")Integer userId, @Param("type") Integer type);
}
