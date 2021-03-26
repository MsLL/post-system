package com.upup.demo.postsystem.ng.post.service.impl;

import com.upup.demo.postsystem.ng.post.po.Post;
import com.upup.demo.postsystem.ng.post.dao.PostDao;
import com.upup.demo.postsystem.ng.post.model.PostQueryParam;
import com.upup.demo.postsystem.ng.post.service.PostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Post)表服务实现类
 *
 * @author generate by easycode
 * @since 2021-03-17 21:19:25
 */
@Service("postService")
public class PostServiceImpl implements PostService {
    @Resource
    private PostDao postDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Post findById(Integer id) {
        return this.postDao.findById(id);
    }

    /**
     * 查询指定行数据
     * @param param 查询参数对象
     * @return 对象列表
     */
    @Override
    public List<Post> list(PostQueryParam param) {
        return this.postDao.list(param);
    }

    /**
     * 新增数据
     *
     * @param post 实例对象
     * @return 实例对象
     */
    @Override
    public Post insert(Post post) {
        this.postDao.insert(post);
        return post;
    }

    /**
     * 修改数据
     *
     * @param post 实例对象
     * @return 实例对象
     */
    @Override
    public Post update(Post post) {
        this.postDao.update(post);
        return this.findById(post.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.postDao.deleteById(id) > 0;
    }
}