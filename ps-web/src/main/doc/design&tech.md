# 名词解释、含义

1.bss:bussiness support system业务支撑系统。一个系统所必须的，但是又非这个系统的核心模块。典型比如用户登陆，用户权限。 
2.ng:核心模块。对于post service来讲，就是post,answer,comment三个模块。 
# ng
# bss 
## 1.user module
### 1.[技术]怎样用户提交密码的安全性：
1.网站开启HTTPS。没开呢？很多时候都是先http，而后才开HTTPS：前端md5(user_password_input)，后端md5(salt+md5(user_password_input))     
2.每个用户一个自己的salt，作为自己的主属性存在user表里。

### 2.[业务]哪些用户是post system的用户，即哪些人是pser

1.通过username/password登陆的用户；需要用户自己先在页面注册。

2.通过短信/验证码登陆的用户；不需要先注册，在登录Authenticate成功之后，如果发现系统没有该用户，自动注册一个新用户。

3.OAtuh登陆的用户,同2。

4.Appkey/Appsecret登陆的用户,同2。

### 3.[技术]session identification怎么保存

request到来的时候，怎么保存pser，请求处理完还要从删除。具体的，在登陆成功|登陆拦截器认证成功之后，怎么保存用户的pser，请求处理完还要删掉。

所以需要一个类似RequestContext，刚好spring提供了一个这样的东西：RequestContextHolder：https://www.jianshu.com/p/80165b7743cf

实现：

1.在登陆成功|登陆拦截器认证成功之后，从RequestContextHolder拿RequestAttributes对象，往里面塞我们自己的一些数据；

2.在后续整个请求处理的过程中，我们都可以从RequestContextHolder拿RequestAttributes对象，读里面的数据；

3.关于清理，在请求处理完后，spring会自动清理掉这个RequestAttributes对象，我们自己就不用管清理的事情了。只管拿RequestAttributes，往它塞/读数据。

### 4.[技术]session identification的生成

UUID一个字符串，3DES加密，然后Hex返回(直接new String的话可能乱码)。前端带回来的值如果解不出来，说明不是一个系统生成的值。

怎么好像修改加密后的值，还是解密出来了一个字符串，虽然不是原字符串，以为会抛异常呢。为了解决这个事情，在UUID前面加了一个PSER_KEY前缀，这样可以通过检测加密出来的值是不是以PSER_KEY前缀开团来判断是不是系统生成的值。

### 5.[业务，技术]搞个job，定期同步[endpoint，权限码集合]到数据库

```java
@Controller
@ResponseBody
@Permission(value = PermissionConst.xxx)
public class PostSystemController {
    @Permission(value = PermissionConst.SHUTDOWN)
    @RequestMapping(value = "/shutdown", method = RequestMethod.GET)
    public void stop() {
    }
}
```

形如这样的话，数据库就应该有两条记录：

</,[ PermissionConst.xxx]>

<\/shutdown,[PermissionConst.xxx,PermissionConst.SHUTDOWN]>

如何拿到所有的controller类：检查所有bean，看有没有被controoler或restcontroller注解。

springboot-xxljob。

## 技术问题

### 方法缓存



