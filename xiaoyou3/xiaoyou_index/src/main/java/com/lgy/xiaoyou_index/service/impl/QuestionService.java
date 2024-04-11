package com.lgy.xiaoyou_index.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgy.xiaoyou_index.entity.TbQuestion;
import com.lgy.xiaoyou_index.mapper.TbQuestionMapper;
import com.lgy.xiaoyou_index.mapper.TbStuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author l9216
 */
@Service
public class QuestionService {

    @Autowired
    private TbQuestionMapper questionMapper;

    @Autowired
    private TbStuMapper stuMapper;

    public IPage<TbQuestion> list(int page, int size) {
        IPage<TbQuestion> questionPage = questionMapper.getQuestionPage(new Page<>(page, size));
        return questionPage;
    }

    public IPage<TbQuestion> list(int userid, int page, int size) {
        IPage<TbQuestion> questionPage = questionMapper.getQuestionPageById(new Page<TbQuestion>(page, size), userid);
        return questionPage;
    }

    public TbQuestion getbyid(int id) {
        TbQuestion question = questionMapper.getById(id);
        return question;
    }

    public void increaseview(int id) {
        questionMapper.updateView(id);
    }

    public List<TbQuestion> getbytag(int id, String result) {
        return questionMapper.getByTag(id, result);
    }

    public List<TbQuestion> gettopten() {
        List<TbQuestion> questions = questionMapper.getTopten();
        return questions;
    }
}
