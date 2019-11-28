package com.course.server;

import com.course.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.management.ValueExp;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value="/",description = "这是我的全部post请求")
@RequestMapping("/v1")
public class MyPostMethod {
    //这个变量是用来装我们cookies信息的
    private static Cookie cookie;

    //用户登录成功获取到cookies，然后再访问其他接口获取到列表

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value="登陆接口，成功后获取cookies信息",httpMethod = "POST")
    public String login(HttpServletResponse response,
                        @RequestParam(value = "username",required = true) String username,
                        @RequestParam(value = "password",required = true) String password){
        if("zhangsan".equals(username) && "123456".equals(password)){
            cookie=new Cookie("login","true");
            response.addCookie(cookie);
            return "恭喜你登陆成功了！";
        }
        return "用户名或者密码错误！";
    }

    @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
    @ApiOperation(value="获取用例列表",httpMethod = "POST")
    public String getUserList(HttpServletRequest request,
                            @RequestBody User u){
        User user;
        Cookie[] cookies=request.getCookies();
        for(Cookie c:cookies){
            if("login".equals(c.getName())
                     && "true".equals(c.getValue())
                     && "zhangsan".equals(u.getUserName())
                     &&  "123456".equals(u.getPassword())
              ){
                user=new User();
                user.setName("lisi");
                user.setAge("18");
                user.setSex("man");
                return user.toString();
            }
        }

        return "参数不合法";

    }
}
