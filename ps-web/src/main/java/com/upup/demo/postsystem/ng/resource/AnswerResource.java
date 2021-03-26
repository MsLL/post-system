package com.upup.demo.postsystem.ng.resource;

import com.upup.demo.postsystem.enums.DataRowState;
import com.upup.demo.postsystem.model.ResourceResponseModel;
import com.upup.demo.postsystem.ng.answer.AnswerQueryParam;
import com.upup.demo.postsystem.ng.answer.po.Answer;
import com.upup.demo.postsystem.ng.answer.service.AnswerService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author tao.li
 * @Date 2021/1/25 下午10:01
 */
@Controller
@ResponseBody
@RequestMapping("/answer")
public class AnswerResource {
    @Autowired
    AnswerService answerService;

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public String delayOk() throws InterruptedException {
        Thread.sleep(5 * 1000);
        return "pong";
    }

    @PostMapping
    public ResourceResponseModel create(@RequestBody Answer answer) {
        answer.setCreateDatetime(new Date());
        answer.setState(DataRowState.ACTIVE.name());
        answerService.insert(answer);
        return ResourceResponseModel.builder().code(200).build();
    }

    @GetMapping("/{id}")
    public ResourceResponseModel getAnswerById(@PathVariable("id") int answerId) {
        Answer answer = answerService.findById(answerId);
        return ResourceResponseModel.builder().code(200).data(answer).build();
    }

    @GetMapping
    public ResourceResponseModel listAnswers(
        // 比如想查看我的所有评论，那postId就不是必须的
        @RequestParam(value = "postId", required = false) Integer postId,
        @RequestParam(value = "userId", required = false) Integer userId
    ) {
        AnswerQueryParam queryParam = new AnswerQueryParam();
        queryParam.setPostId(postId);
        queryParam.setUserId(userId);
        List<Answer> answers = answerService.list(queryParam);
        return ResourceResponseModel.builder().code(200).data(answers).build();
    }

    @PutMapping("/{id}")
    public ResourceResponseModel update(@PathVariable("id") int answerId, @RequestBody Answer answer) {
        answer.setId(answerId);
        answerService.update(answer);
        return ResourceResponseModel.builder().code(200).build();
    }

    @PutMapping("/archive/{id}")
    public ResourceResponseModel archive(@PathVariable("id") int answerId) {
        Answer answer = new Answer();
        answer.setId(answerId);
        answer.setState(DataRowState.ARCHIVED.name());
        answerService.update(answer);
        return ResourceResponseModel.builder().code(200).build();
    }
}
