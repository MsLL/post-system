package com.upup.demo.postsystem;

import com.upup.demo.postsystem.annotation.Permission;
import com.upup.demo.postsystem.dictionary.PermissionConst;
import com.upup.demo.postsystem.util.WebUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author tao.li
 * @Date 2021/1/27 下午12:29 yes controller, not resource
 */
@Controller
@ResponseBody
public class PostSystemController {
    @Autowired
    ApplicationContext applicationContext;

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public String ping() {
        return "pong";
    }

    //https://stackoverflow.com/questions/14423980/how-to-close-a-spring-applicationcontext
    @Permission(value = PermissionConst.SHUTDOWN)
    @RequestMapping(value = "/shutdown", method = RequestMethod.GET)
    public void stop() {
        ((ConfigurableApplicationContext) applicationContext).close();
    }

    @RequestMapping(value = "listBeans", method = RequestMethod.GET)
    //{"beanClassName":[bean name list]}
    public Map<String, List<String>> listBeans() {
        Map<String, Object> beanNameBean = applicationContext.getBeansOfType(Object.class);

        //根据bean类名排序
        Map<String, List<String>> beanClassName2Beans = new TreeMap<>();

        beanNameBean.forEach((beanName, bean) -> {
            String beanClassName = bean.getClass().getCanonicalName();
            if (!beanClassName2Beans.containsKey(beanClassName)) {
                beanClassName2Beans.put(beanClassName, new ArrayList<String>());
            }
            beanClassName2Beans.get(beanClassName).add(beanName);
        });
        return beanClassName2Beans;
    }

    @RequestMapping(value = "/parse", method = RequestMethod.GET)
    public String parsePserId(@RequestParam("pserId") String pserId) {
        return WebUtil.getPserId(pserId);
    }
}
