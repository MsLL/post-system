package com.upup.demo.postsystem.bss.schedule;

import com.google.common.primitives.Ints;
import com.upup.demo.postsystem.annotation.Permission;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author tao.li
 * @Date 2021/1/31 上午12:02
 */
//todo
@Component
public class ResourcePathPermissionCodeSyncTask {

    @Autowired
    ApplicationContext applicationContext;

    @Scheduled(cron = "0 0 0/1 * * ?")
    public void sycnResourcePathPermissionCode() {
        Map<String, Object> controllers = new HashMap<>();
        controllers.putAll(applicationContext.getBeansWithAnnotation(RestController.class));
        controllers.putAll(applicationContext.getBeansWithAnnotation(Controller.class));

        Map<String, List<Integer>> resourcePathPermissionCodes = new HashMap<>();
        controllers.forEach((name, controllerBean) -> {
            String classLevelResourcePath = null;
            List<Integer> classLevelPermissionCode = null;

            if (controllerBean.getClass().isAnnotationPresent(RequestMapping.class)) {
                classLevelResourcePath = controllerBean.getClass().getDeclaredAnnotation(RequestMapping.class).value()[0];//一般情况只会写一个路径
            }
            if (controllerBean.getClass().isAnnotationPresent(Permission.class)) {
                //https://stackoverflow.com/a/3000758
                classLevelPermissionCode.addAll(Ints.asList(controllerBean.getClass().getDeclaredAnnotation(Permission.class).value()));
            }

            Method[] methods = controllerBean.getClass().getDeclaredMethods();
            for (int i = 0; i < methods.length; i++) {
                Method method = methods[i];
                if (method.isAnnotationPresent(RequestMapping.class)) {
                }
            }
        });
    }
}
