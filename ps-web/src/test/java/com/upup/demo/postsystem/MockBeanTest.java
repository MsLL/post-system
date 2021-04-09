package com.upup.demo.postsystem;

import com.upup.demo.postsystem.enums.DataRowState;
import com.upup.demo.postsystem.ng.post.model.PostQueryParam;
import com.upup.demo.postsystem.ng.post.model.PostWrapper;
import com.upup.demo.postsystem.ng.post.service.PostService;
import java.util.ArrayList;
import java.util.Date;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * @Author tao.li
 * @Date 2021/4/9 下午11:08
 */

//org.junit.platform.commons.JUnitException: @BeforeAll method 'public void com.upup.demo.postsystem.MockBeanTest.setup()' must be static unless the test class is annotated with @TestInstance(Lifecycle.PER_CLASS).
//NOTE-UPUP 2021/4/9 下午11:39 : 两步曲，1.mock掉替换掉的bean，2.对本次test会用的该bean的方法进行when/then设置，不设置就会返回null| emptylist 之类的系统值。
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class MockBeanTest {

    @MockBean
    PostService postService;

    @BeforeAll
    public void  setup(){
        PostWrapper postWrapper=PostWrapper.builder()
            .id(1000)
            .state(DataRowState.ACTIVE.name())
            .content("content")
            .answers(new ArrayList<>())
            .createDatetime(new Date())
            .updateDatetime(new Date())
            .build();
        Mockito.when(postService.findById(1000)).thenReturn(postWrapper);
    }

    //NOTE-UPUP 2021/4/9 下午11:36 : 老的bean会被新生成的代理类的bean换掉
    @Test
    public void test0(){
        //类似com.upup.demo.postsystem.ng.post.service.PostService$MockitoMock$1602870127，看名字就是MockitoMock的代理bean。
        System.out.println(postService.getClass().getCanonicalName());
    }


    //NOTE-UPUP 2021/4/9 下午11:38 : Mockito会校验方法签名，参数
    @Test
    public void test1(){
        System.out.println(postService.findById(1));//null
        System.out.println(postService.findById(1000));
    }

    //NOTE-UPUP 2021/4/9 下午11:26 : 代理生成的mock bean，对方法的实现不会调用原bean。
    //在com.upup.demo.postsystem.ng.post.service.impl.PostServiceImpl#list打个断点，debug该test，没进入。
    @Test
    public void test2(){
        System.out.println(postService.list(PostQueryParam.builder().build()));
    }
}
