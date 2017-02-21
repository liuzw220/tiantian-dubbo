package com.tiantian.member.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiantian.member.bo.UserBo;
import com.tiantian.member.business.UserBusiness;
import com.tiantian.member.vo.UserVo;

@RequestMapping("user")
@Controller
public class UserController {

    @Autowired
    private UserBusiness userService;

    /**
     * 提供验证用户是否可用的接口
     * @param param 用户名或者是邮箱或者手机号
     * @param type 类型 1,username 2,phone 3,email
     * @return 布尔类型
     */
    @RequestMapping(value="check/{param}/{type}",method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Boolean> checkUser(@PathVariable("param")String param,
            @PathVariable("type")Integer type){
        try {
            return ResponseEntity.ok(userService.checkUser( param,type));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * 注册用户接口
     * @param user 用户对象
     * @param result 验证是否通过
     * @return
     */
    @RequestMapping(value="register",method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> register(@Valid UserBo user, BindingResult result){
    	user.setEmail("572361523@qq.com");
    	user.setPassword("123456");
    	user.setId(51);
    	user.setUsername("lzw221");
    	user.setPhone("1588702359");
        try {
            if(result.hasErrors()){
                // 错误处理
                List<String> msgs=new ArrayList<String>();
                List<ObjectError> allErrors = result.getAllErrors();
                for (ObjectError error :allErrors) 
                    msgs.add(error.toString());
                //400(参数错误)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(StringUtils.join(msgs, " | "));
            }
            if(userService.register(user)){
                return ResponseEntity.ok("ok");
            }
            else{
                return ResponseEntity.ok("注册失败");
            }
        } catch (Exception e) {
            // TODO 写日志
            e.printStackTrace();
            //500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    /**
     * 用户登录
     * @param username 用户名
     * @param passwd 密码
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> login(@RequestParam("u") String username,
            @RequestParam("p") String password){
        try {
            UserVo userVo= this.userService.findByUserName(username);
            Map<String, Object> result = new HashMap<String, Object>();
            if(userVo==null) {
                result.put("mgs", "用户名不存在");
                //301(资源已经被移除)
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(result);
            }
            if(!userVo.getPassword().equals(password)){ 
                result.put("mgs", "密码错误");
                //301(资源已经被移除)
                return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).body(result);
            }
            //登录成功
            UserBo user=new UserBo();
            result.put("mgs", "ok");
            // 将用户数据保存到redis中 返回数据的键
            String ticket =this.userService.saveRedis(user);
            result.put("ticket", ticket);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            //500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @RequestMapping(value="{ticket}",method=RequestMethod.GET)
    public ResponseEntity<UserVo> queryUserByTicket(@PathVariable("ticket")String ticket){
        try {
            UserVo user= this.userService.queryUserByTicket(ticket);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            ///500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
