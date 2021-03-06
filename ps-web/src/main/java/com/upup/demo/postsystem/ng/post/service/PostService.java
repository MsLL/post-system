package com.upup.demo.postsystem.ng.post.service;

import com.upup.demo.postsystem.ng.post.model.PostWrapper;
import com.upup.demo.postsystem.ng.post.model.PostQueryParam;
import com.upup.demo.postsystem.ng.post.po.Post;
import java.util.List;

/**
 * (Post)表服务接口
 *
 * @author generate by easycode
 * @since 2021-03-17 21:19:25
 */
public interface PostService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PostWrapper findById(Integer id);

    /**
     * 查询多条数据
     *
     * @param param@return 对象列表
     */
    List<PostWrapper> list(PostQueryParam param);

    /**
     * 新增数据
     *
     * @param post 实例对象
     * @return 实例对象
     */
    Post insert(Post post);

    /**
     * 修改数据
     *
     * @param post 实例对象
     * @return 实例对象
     */
    Post update(Post post);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);
}