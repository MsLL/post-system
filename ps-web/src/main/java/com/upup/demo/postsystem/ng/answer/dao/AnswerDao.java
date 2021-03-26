package com.upup.demo.postsystem.ng.answer.dao;

import com.upup.demo.postsystem.ng.answer.AnswerQueryParam;
import com.upup.demo.postsystem.ng.answer.po.Answer;
import java.util.List;

/**
 * (Answer)表数据库访问层
 *
 * @author generate by easycode
 * @since 2021-03-17 23:31:52
 */
public interface AnswerDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Answer findById(Integer id);

    /**
     * 查询指定行数据
     *
     *
     * @param queryParam@return 对象列表
     */
    List<Answer> list(AnswerQueryParam queryParam);


    /**
     * 新增数据
     *
     * @param answer 实例对象
     * @return 影响行数
     */
    int insert(Answer answer);

    /**
     * 修改数据
     *
     * @param answer 实例对象
     * @return 影响行数
     */
    int update(Answer answer);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}