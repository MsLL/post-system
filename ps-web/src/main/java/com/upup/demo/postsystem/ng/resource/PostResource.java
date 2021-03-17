package com.upup.demo.postsystem.ng.resource;

import com.upup.demo.postsystem.enums.DataRowState;
import com.upup.demo.postsystem.model.ResourceResponseModel;
import com.upup.demo.postsystem.ng.post.entity.Post;
import com.upup.demo.postsystem.ng.post.service.PostService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author tao.li
 * @Date 2021/1/25 下午8:49
 */
@Controller
@ResponseBody
@RequestMapping("/post")
public class PostResource {

    @Autowired
    PostService postService;

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public String echoOk() {
        return "pong";
    }

    @PostMapping
    public ResourceResponseModel create(@RequestBody Post post) {
        //NOTE-UPUP 2021/3/17 下午11:14 :  时间用服务端时间。
        post.setCreateDatetime(new Date());
        post.setState(DataRowState.ACTIVE.name());
        postService.insert(post);
        return ResourceResponseModel.builder().code(200).build();
    }

    @GetMapping
    public ResourceResponseModel listPost() {
        List<Post> post = postService.queryAllByLimit(0, Integer.MAX_VALUE);
        return ResourceResponseModel.builder().code(200).data(post).build();
    }

    @GetMapping("/{id}")
    public ResourceResponseModel getPostById(@PathVariable("id") int postId) {
        Post post = postService.findById(postId);
        return ResourceResponseModel.builder().code(200).data(post).build();
    }

    //NOTE-UPUP 2021/3/17 下午11:14 : 看起来可以不用这个id，直接放在请求体里面。但是维护rest语义，所以。
    @PutMapping("/{id}")
    public ResourceResponseModel update(@PathVariable("id") int postId, @RequestBody Post post) {
        post.setId(postId);
        postService.update(post);
        return ResourceResponseModel.builder().code(200).build();
    }

    @PutMapping("/archive/{id}")
    public ResourceResponseModel archive(@PathVariable("id") int postId) {
        Post post = new Post();
        post.setId(postId);
        post.setState(DataRowState.ARCHIVED.name());
        postService.update(post);
        return ResourceResponseModel.builder().code(200).build();
    }
}
