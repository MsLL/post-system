package com.upup.demo.postsystem.ng.resource;

import com.upup.demo.postsystem.enums.DataRowState;
import com.upup.demo.postsystem.model.ResourceResponseModel;
import com.upup.demo.postsystem.ng.post.entity.Post;
import com.upup.demo.postsystem.ng.post.model.PostQueryParam;
import com.upup.demo.postsystem.ng.post.service.PostService;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    /**
     * query examples:
     * http://localhost:8080/post
     * http://localhost:8080/post?state=ACTIVE,ARCHIVED
     * http://localhost:8080/post?state=ACTIVE,ARCHIVED&limit=1
     * http://localhost:8080/post?state=ACTIVE,ARCHIVED&limit=1&offset=1
     *
     * 如果时间参数不符合expect,依次检查数据库服务时区、连接uri时区、mapper文件的java->jdbctype数据类型映射。
     */
    @GetMapping
    public ResourceResponseModel listPost(
        @RequestParam(value = "state", required = false) String commaSeperateStateList,
        @RequestParam(value = "limit", required = false) Integer limit,
        @RequestParam(value = "offset", required = false) Integer offset,
        //NOTE-UPUP 2021/3/20 下午7:13 : fix Unable to convert String to Date. https://stackoverflow.com/questions/25646564/unable-to-convert-string-to-date-by-requestbody-in-spring 或者直接用String接收，再手转。
        //@RequestParam(value = "createDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date createDate,   不用上面这种方式了，通过对WebMvcConfig#addFormatters最application级别的处理。
        @RequestParam(value = "createDate", required = false) Date createDate,
        @RequestParam(value = "updateDate", required = false) Date updateDate

    ) {
        PostQueryParam queryParam = new PostQueryParam();
        if (StringUtils.isNotBlank(commaSeperateStateList)) {
            queryParam.setStates(Arrays.asList(commaSeperateStateList.split(",")));
        }
        if (limit != null) {
            queryParam.setLimit(limit);
        }
        if (offset != null) {
            queryParam.setOffset(offset);
        }
        if (createDate != null) {
            queryParam.setCreateDate(createDate);
        }
        if (updateDate != null) {
            queryParam.setUpdateDate(updateDate);
        }
        List<Post> post = postService.list(queryParam);
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
