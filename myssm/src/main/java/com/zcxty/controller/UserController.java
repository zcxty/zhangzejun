package com.zcxty.controller;
 
import com.zcxty.model.User;
import com.zcxty.service.UserService;
import com.zcxty.utils.Tool;
import com.zcxty.utils.PageNumber;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.InetAddress;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    private HttpServletRequest request;
    /*private HttpServletRequest request;*/
 
    @RequestMapping("/allUser")
    public String list(@RequestParam(value="page", defaultValue="1") int page,String username,Model model) {
        List<User> list = userService.queryAllUser();
        List<String> ids=new ArrayList<String>();
        List<User> result=null;
        Map<String,String> param=new HashMap<String,String>();
        if(username!=null) {
        	ids.add(username);
          param.put(username, username);
        //System.out.println(username);
        // 存放过滤结果的列表
      
        // 使用lambda表达式过滤出结果并放到result列表里，written by zhangchao
        result = list.stream()
                .filter((User u) -> ids.contains(u.getUsername()))
                .collect(Collectors.toList());

        // 打印结果列表
        /*if (result != null && !result.isEmpty()) {
            result.forEach((User u) -> System.out.println(u.getId() + "  " + u.getUsername()));
         }*/
        }else {
        	result=list;
        	param.remove(username);
        }
        int count=result.size();
        int pageSize=2;
        PageNumber pn=new PageNumber(count,pageSize=2,page, param);
        //从第几条数据开始
        if(page>pn.pageNum) {
        	page=pn.pageNum;
        }else if(page<=0) {
            page=1;
        }
         int firstIndex = (page - 1) * pageSize;
         //到第几条数据结束
         int lastIndex = page * pageSize;
         model.addAttribute("list", result.subList(firstIndex, lastIndex));
         model.addAttribute("page",pn.getPage());
        return "allUser";
    }
    @RequestMapping("/searchUser")
    public String searchUser(String username,Model model) {
    	System.out.println(username);
    	return "redirect:/user/searchUser";
    }
	@RequestMapping("/toAddUser")
    public String toAddPaper() {
        return "addUser";
    }
 
    @RequestMapping("/addUser")
    public String addUser(String username,String password,Model model) throws ParseException, UnknownHostException{
         User user=new User();
         user.setUsername(username);
         Tool md5=new Tool();
         SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         String date=sdf.format(new Date());
         user.setPassword(md5.complie(password));
         user.setCreateTime(sdf.parse(date));
         //String ip=getIpAddr(request);
         InetAddress address = InetAddress.getLocalHost();//返回IP地址
         user.setIp(address.getHostAddress());
         user.setLoginTime(sdf.parse(date));
         userService.addUser(user);
    	//System.out.println(username);
    	//System.out.println(md5.complie(password));
       return "redirect:/user/allUser";
    	//return null;
    }
     @RequestMapping("/")
    private String md5(String password) {
		// TODO Auto-generated method stub
		return null;
	}
    
	@RequestMapping("/del/{paperId}")
    public String deletePaper(@PathVariable("paperId") Long id) {
        userService.deleteUserById(id);
        return "redirect:/user/allUser";
    }
 
    @RequestMapping("toUpdateUser")
    public String toUpdatePaper(Model model, Long id) {
        model.addAttribute("user", userService.queryById(id));
        return "updateUser";
    }
 
    @RequestMapping("/updateUser")
    public String updatePaper(Model model, User user) {
        userService.updateUser(user);
        user = userService.queryById(user.getId());
        model.addAttribute("user", user);
        return "redirect:/user/allUser";
    }
}