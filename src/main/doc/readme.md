# 名词解释、含义
1.bss:bussiness support system业务支撑系统。一个系统所必须的，但是又非这个系统的核心模块。典型比如用户登陆，用户权限。 
2.ng:核心模块。对于post service来讲，就是post,answer,comment三个模块。 
# ng
# bss 
## 1.user module
### 1.怎样用户提交密码的安全性：
1.网站开启HTTPS。没开呢？很多时候都是先http，而后才开HTTPS：前端md5(user_password_input)，后端md5(salt+md5(user_password_input))     
2.每个用户一个自己的salt，作为自己的主属性存在user表里。

### 2.哪些用户是post system的用户，即哪些人是pser

1.通过username/password登陆的用户；需要用户自己先在页面注册。

2.通过短信/验证码登陆的用户；不需要先注册，在登录Authenticate成功之后，如果发现系统没有该用户，自动注册一个新用户。

3.OAtuh登陆的用户,同2。

4.Appkey/Appsecret登陆的用户,同2。