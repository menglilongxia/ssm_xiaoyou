package com.lgy.xiaoyou_index.service.impl;



import com.lgy.xiaoyou_index.entity.TbComment;
import com.lgy.xiaoyou_index.mapper.TbCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author l9216
 */
@Service
public class CommentService  {
    @Autowired
    private TbCommentMapper commentMapper;


    public List<TbComment> getByid(Integer id) {
        List<TbComment> comments=commentMapper.getById(id);
        return comments;
    }
}
