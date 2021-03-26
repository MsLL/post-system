package com.upup.demo.postsystem.ng.answer.service.impl;

import com.upup.demo.postsystem.ng.answer.AnswerQueryParam;
import com.upup.demo.postsystem.ng.answer.po.Answer;
import com.upup.demo.postsystem.ng.answer.dao.AnswerDao;
import com.upup.demo.postsystem.ng.answer.service.AnswerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Answer)表服务实现类
 *
 * @author generate by easycode
 * @since 2021-01-31 23:44:32
 */
@Service("answerService")
public class AnswerServiceImpl implements AnswerService {
    @Resource
    private AnswerDao answerDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Answer findById(Integer id) {
        return this.answerDao.findById(id);
    }

    /**
     * 查询多条数据
     *
     *
     * @param queryParam@return 对象列表
     */
    @Override
    public List<Answer> list(AnswerQueryParam queryParam) {
        return this.answerDao.list(queryParam);
    }

    /**
     * 新增数据
     *
     * @param answer 实例对象
     * @return 实例对象
     */
    @Override
    public Answer insert(Answer answer) {
        this.answerDao.insert(answer);
        return answer;
    }

    /**
     * 修改数据
     *
     * @param answer 实例对象
     * @return 实例对象
     */
    @Override
    public Answer update(Answer answer) {
        this.answerDao.update(answer);
        return this.findById(answer.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.answerDao.deleteById(id) > 0;
    }
}