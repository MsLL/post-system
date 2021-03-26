package com.upup.demo.postsystem.ng.resource;

import com.upup.demo.postsystem.enums.DataRowState;
import com.upup.demo.postsystem.model.ResourceResponseModel;
import com.upup.demo.postsystem.ng.comment.po.Comment;
import com.upup.demo.postsystem.ng.comment.model.CommentQueryParam;
import com.upup.demo.postsystem.ng.comment.service.CommentService;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/comment")
public class CommentResource {
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public String delayOk() throws InterruptedException {
        Thread.sleep(3 * 1000);
        return "pong";
    }

    @PostMapping
    public ResourceResponseModel create(@RequestBody Comment comment) {
        comment.setCreateDatetime(new Date());
        comment.setState(DataRowState.ACTIVE.name());
        commentService.insert(comment);
        return ResourceResponseModel.builder().code(200).build();
    }

    @GetMapping
    public ResourceResponseModel list(
        @RequestParam(value = "answerId", required = false) Integer answerId,
        @RequestParam(value = "state", required = false) String commaSeperateStateList
    ) {
        CommentQueryParam queryParam = new CommentQueryParam();
        if (answerId != null) {
            queryParam.setAnswerId(answerId);
        }
        if (StringUtils.isNotBlank(commaSeperateStateList)) {
            queryParam.setStates(Arrays.asList(commaSeperateStateList.split(",")));
        }
        List<Comment> comments = commentService.list(queryParam);
        return ResourceResponseModel.builder().code(200).data(comments).build();
    }

    @GetMapping("/{id}")
    public ResourceResponseModel getCommentById(@PathVariable("id") Integer commentId) {
        Comment comment = commentService.findById(commentId);
        return ResourceResponseModel.builder().code(200).data(comment).build();
    }

    @PutMapping("/{id}")
    public ResourceResponseModel update(@PathVariable("id") Integer commentId, @RequestBody Comment comment) {
        comment.setId(commentId);
        commentService.update(comment);
        return ResourceResponseModel.builder().code(200).build();
    }

    @PutMapping("/archive/{id}")
    public ResourceResponseModel archive(@PathVariable("id") Integer commentId) {
        Comment comment=new Comment();
        comment.setId(commentId);
        comment.setState(DataRowState.ARCHIVED.name());
        commentService.update(comment);
        return ResourceResponseModel.builder().code(200).build();
    }
}