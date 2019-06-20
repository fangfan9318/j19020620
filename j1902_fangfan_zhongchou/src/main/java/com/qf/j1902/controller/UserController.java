package com.qf.j1902.controller;

import com.qf.j1902.pojo.User;
import com.qf.j1902.service.UserService;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2019/5/27.
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }
    @RequestMapping(value = "/reg",method = RequestMethod.GET)
    public String reg(){
        return "reg";
    }
    //登录
    @PostMapping("/denglu")
    public String denglu(HttpServletRequest request,
                         @RequestParam("jsType")String jsType,
                         @RequestParam("zhanghu")String zhanghu,
                         @RequestParam("password")String password,
                         Model model){
        //System.out.println(jsType);
        ArrayList<User> members = userService.findUserByJs("member");
        User user=new User(zhanghu,password,jsType);
        //System.out.println(user);
        ArrayList<User> users = userService.findUserByJs("user");
        //System.out.println(zhanghu);
        request.getSession().setAttribute("zhanghu",zhanghu);
        model.addAttribute("zhanghu",zhanghu);
        if(members.contains(user) || users.contains(user)){
            //System.out.println("11111");
            if("member".equals(jsType)){
                //System.out.println("22222");
                User userByZh = userService.findUserByZh(zhanghu);
                String status = userByZh.getStatus();
                if (status.equals("0")) {
                    model.addAttribute("message", "未实名认证");
                } else if (status.equals("1")) {
                    model.addAttribute("message", "实名认证审核中");
                }else if (status.equals("2")) {
                    model.addAttribute("message", "审核认证通过");
                } else if (status.equals("3") ){
                    model.addAttribute("message", "审核驳回,请重新认证");
                }
                return "member";
            }else if("user".equals(jsType)){
                //System.out.println("33333");
                return "main";
            }
        }else{
            model.addAttribute("message","账户或密码错误,请重新输入!");
            return "login";
        }
        return "login";
    }
    //member
    @GetMapping("/member")
    public String member(Model model,HttpSession session) {
        String zhanghu = (String) session.getAttribute("zhanghu");
        //System.out.println(zhanghu);
        User userByZh = userService.findUserByZh(zhanghu);
        String status = userByZh.getStatus();
        System.out.println(status+"-----11-----");
        if (status.equals("0")) {
            model.addAttribute("message", "未实名认证");
        } else if (status.equals("1")) {
            model.addAttribute("message", "实名认证审核中");
        }else if (status.equals("3")) {
            model.addAttribute("message", "审核驳回,请重新认证");
        } else if (status.equals("2")){
            model.addAttribute("message", "审核认证通过");
        }
        return "member";
    }

    //main
    @GetMapping("/main")
    public String main(){
        return "main";
    }
    //注册新用户
    @PostMapping("/zhuce")
    public String zhuce(@RequestParam("jsType")String jsType,
                        @RequestParam("zhanghu")String zhanghu,
                        @RequestParam("password")String password,
                        @RequestParam("email")String email,
                        Model model) {
        /*System.out.println(jsType);
        System.out.println(zhanghu);
        System.out.println(password);
        System.out.println(email);*/
        if(zhanghu != "" && password != "" && email != ""){
            if ("member".equals(jsType)){
                boolean addUser = userService.addUser(zhanghu, password, email, jsType);
                //System.out.println(addUser);
                if(addUser==true){
                    //System.out.println("3333");
                    model.addAttribute("massage","");
                    return "login";
                }else {
                    //System.out.println("44444");
                    model.addAttribute("massage","注册失败,请重新操作");
                    return "reg";
                }
            }else if ("user".equals(jsType)){
               // System.out.println("6666");
                model.addAttribute("massage","sorry,您没有注册管理员的权限");
                return "reg";
            }
        }else {
            //System.out.println("2222");
            model.addAttribute("massage","请输入完整的注册信息");
            return "reg";
        }
        return "login";
    }
    //用户维护
    @GetMapping("/user")
    public String user(HttpServletRequest request, Model model){
        ArrayList<User> users = userService.findAll();
        request.getSession().setAttribute("users",users);
        model.addAttribute("users",users);
        return "user";
    }
    //assignRole
    @GetMapping("/assignRole")
    public String assignRole(){
        return "assignRole";
    }

    //增加用户
    @GetMapping("/add")
    public String add1(){
        return "add";
    }
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(@RequestParam(value = "zhanghu",defaultValue = "")String zhanghu,
                      @RequestParam(value = "username",defaultValue = "")String username,
                      @RequestParam(value = "email",defaultValue = "")String email,
                      Model model){
        if("".equals(zhanghu)||"".equals(email)) {
            //model.addAttribute("massage","请输入完整的信息");
            return "add";
        }else{
            String password="666666";
            boolean adduser = userService.add(zhanghu,password,username,email);
            if (adduser == true) {
                //System.out.println("添加用户成功");
                return "redirect:user";
            }else {
                model.addAttribute("massage","添加失败,请重新操作");
                return "add";
            }
        }
    }
    //用户修改
    @GetMapping("/edit")
    public String edit(HttpServletRequest request,
                       Model model){
        int id=Integer.parseInt(request.getParameter("id"));
        //System.out.println(id+"======");
        request.getSession().setAttribute("id",id);
        User userById = userService.getUserById(id);
        model.addAttribute("user",userById);
        return "edit";
    }
    @PostMapping("/update")
    public String update(HttpSession session,
                         @RequestParam(value = "zhanghu",defaultValue = "")String zhanghu,
                         @RequestParam(value = "username",defaultValue = "")String username,
                         @RequestParam(value = "email",defaultValue = "")String email
                         ){
        int id = (Integer) session.getAttribute("id");
        //System.out.println(id+"======");
        boolean updateUser = userService.updateUser(id, zhanghu, username, email);
        if (updateUser == true) {
            //System.out.println("修改用户成功");
            return "redirect:user";
        }
        return "edit";
    }
    //用户删除
    @PostMapping("/delete")
    @ResponseBody
    public String delUser(HttpServletRequest request,Model model){
        int id=Integer.parseInt(request.getParameter("id"));
        //System.out.println(id+"======");
        boolean delUser = userService.delUser(id);
        //model.addAttribute("del",delUser);
        if (delUser == true) {
            //System.out.println("删除用户成功");
            return "111";
        }
        return "000";
    }
    //模糊查询功能
    @PostMapping("/query")
    @ResponseBody
    public String query(HttpServletRequest request,
                        Model model){
        String message=request.getParameter("message");
        System.out.println(message+"==========");
        ArrayList<User> users = userService.findUserMh(message);
        System.out.println(users);
        model.addAttribute("users",users);
        return "users";
    }
    //角色维护
    @GetMapping("/role")
    public String role(){
        return "role";
    }
    //许可维护
    @GetMapping("/permission")
    public String permission(){
        return "permission";
    }
    //参数管理
    @GetMapping("/param")
    public String param(){
        return "param";
    }
    //广告审核
    @GetMapping("/auth_adv")
    public String auth_adv(){
        return "auth_adv";
    }
    //实名认证--账户类型选择
    @GetMapping("/accttype")
    public String accttype(HttpSession session,Model model){
       String zhanghu = (String) session.getAttribute("zhanghu");
        //System.out.println(zhanghu);
        User userByZh = userService.findUserByZh(zhanghu);
        String status = userByZh.getStatus();
        System.out.println(status+"-----22-----");
        if (status.equals("0")) {
            model.addAttribute("message", "未实名认证");
        } else if (status.equals("1")) {
            model.addAttribute("message", "实名认证审核中");
        }else if (status.equals("2")) {
            model.addAttribute("message", "审核认证通过");
        } else if (status.equals("3")){
            model.addAttribute("message", "审核驳回,请重新认证");
        }
        return "accttype";
    }
    @GetMapping("/userType")
    @ResponseBody
    public String userType(HttpServletRequest request){
        String userType=request.getParameter("userType");
        request.getSession().setAttribute("userType",userType);
        System.out.println(userType);
        return "userType";
    }
    //认证申请
    @GetMapping("/apply")
    public String apply(){
        return "apply";
    }
    @PostMapping("/apply-1")
    public String shenqing(@RequestParam(value = "realname",defaultValue = "")String realname,
                        @RequestParam(value = "idcard",defaultValue = "")String idcard,
                        @RequestParam(value = "phonenum",defaultValue = "")String phonenum,
                        HttpServletRequest request){
        System.out.println(realname+"=="+idcard+"==="+phonenum);
        request.getSession().setAttribute("realname",realname);
        request.getSession().setAttribute("idcard",idcard);
        request.getSession().setAttribute("phonenum",phonenum);
        return "apply-1";
    }
    @GetMapping("/apply-1")
    public String apply1(){
        return "apply-1";
    }
    //文件上传
    @PostMapping("/imgonload")
    public String load(MultipartFile pic_src, HttpServletRequest request) {
        //MultipartFile是spring类型，代表HTML中form data方式上传的文件，包含二进制数据+文件名称
        String filePath = request.getRealPath("/upload");
        String originalFilename = pic_src.getOriginalFilename();//得到上传时的文件名
        System.out.println(originalFilename);
        //System.out.println(pic_src);
        String newFileName=null;
        if (pic_src != null && originalFilename != null && originalFilename.length() > 0) {
            newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
            //substring() 方法用于提取字符串中介于两个指定下标之间的字符。
            File newFile = new File(filePath,newFileName);
            try {
                //mkdirs():创建多层目录。
                newFile.mkdirs();
                pic_src.transferTo(newFile);//移动到
            } catch (Exception e) {
            }
        }
        System.out.println(newFileName);
        request.getSession().setAttribute("imgonload","upload/"+newFileName);
        return "apply-2";
    }
    @GetMapping("/apply-2")
    public String apply2(){
        return "apply-2";
    }
    @GetMapping("/apply-3")
    public String apply3(){
        return "apply-3";
    }
    //邮箱发送验证码
    @RequestMapping(value = "sendyzm",method = RequestMethod.POST)
    @ResponseBody
    public int yxyz(HttpServletRequest request,@RequestParam(defaultValue ="a" ) String exam){
        request.getSession().setAttribute("exam",exam);
        //System.out.println(exam+"888888888888");
        String regEx1 ="^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(exam);
        if(m.matches()) {
            try {
                HtmlEmail htmlEmail = new HtmlEmail();
                htmlEmail.setHostName("smtp.qq.com");
                htmlEmail.setCharset("utf-8");
                htmlEmail.addTo(exam);
                htmlEmail.setFrom("937630962@qq.com", "众筹系统");
                htmlEmail.setAuthentication("937630962@qq.com", "elgqkwbsauvxbdab");
                htmlEmail.setSubject("实名认证验证码");
                int a = (int) ((Math.random() * 9 + 1) * 100000);
                String aa = String.valueOf(a);
                HttpSession session = request.getSession();
                session.setAttribute("SessionKey", aa);
                htmlEmail.setMsg("尊贵的会员：您的验证码为" + "<h3>" + aa + "</h3>");

                htmlEmail.send();
                System.out.println("已发送");
                return 200;
            } catch (EmailException e) {
                e.printStackTrace();
            }
        }
        return 400;
    }
    //重新发送验证码
    @RequestMapping(value = "sendyzm2",method = RequestMethod.POST)
    @ResponseBody
    public int yxyz2(HttpServletRequest request){
        String exam = (String) request.getSession().getAttribute("exam");
        //request.getSession().setAttribute("exam",exam);
        String regEx1 ="^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(exam);
        if(m.matches()) {
            try {
                HtmlEmail htmlEmail = new HtmlEmail();
                htmlEmail.setHostName("smtp.qq.com");
                htmlEmail.setCharset("utf-8");
                htmlEmail.addTo(exam);
                htmlEmail.setFrom("937630962@qq.com", "众筹系统");
                htmlEmail.setAuthentication("937630962@qq.com", "elgqkwbsauvxbdab");
                htmlEmail.setSubject("实名认证验证码");
                int a = (int) ((Math.random() * 9 + 1) * 100000);
                String aa = String.valueOf(a);
                HttpSession session = request.getSession();
                session.setAttribute("SessionKey", aa);
                htmlEmail.setMsg("尊贵的会员：您的验证码为" + "<h3>" + aa + "</h3>");

                htmlEmail.send();
                System.out.println("已发送");
                return 200;
            } catch (EmailException e) {
                e.printStackTrace();
            }
        }
        return 400;
    }
    //申请完成,提交存在session中的数据--到数据库中
    @PostMapping("/member")
    public String complete(@RequestParam(value = "yanzhengma",defaultValue = "")String yanzhengma,
                           HttpSession session,Model model){
        //System.out.println(yanzhengma+"===");
        String sessionKey =(String)session.getAttribute("SessionKey");
        //System.out.println(sessionKey);
        if("".equals(yanzhengma) || !yanzhengma.equals(sessionKey)){
           model.addAttribute("message","验证码错误,请重新发送!") ;
           return "apply-3";
        }else if(yanzhengma.equals(sessionKey)){
            String zhanghu=(String) session.getAttribute("zhanghu");
            String userType=(String) session.getAttribute("userType");
            String realname=(String) session.getAttribute("realname");
            String idcard=(String) session.getAttribute("idcard");
            String phonenum=(String) session.getAttribute("phonenum");
            String exam=(String) session.getAttribute("exam");
            String imgonload=(String) session.getAttribute("imgonload");
            //查询实名认证状态
            User userByZh = userService.findUserByZh(zhanghu);
            String status=userByZh.getStatus();
            System.out.println(status+"-----33-----");
            System.out.println(zhanghu+"=="+userType+"--"+realname+"--"+idcard+"--"+phonenum+"--"+exam+"--"+imgonload);
            //model.addAttribute("message","实名认证审核中");
            status="1";
            //存到数据库
            boolean b = userService.updateByZh(zhanghu, exam, realname, idcard, phonenum, imgonload, userType, status);
            System.out.println("存入数据库成功?====="+b);
            return "redirect:member";
        }
        return "member";
    }
    //实名认证审核
    @GetMapping("/auth_cert")
    public String auth_cert(Model model){
        ArrayList<User> users = userService.getUserByStatus("1");
        model.addAttribute("users",users);
        return "auth_cert";
    }
    //实名认证审核意见
    @GetMapping("/check")
    public String check(HttpServletRequest request,Model model){
        int id=Integer.parseInt(request.getParameter("id"));
        User user = userService.getUserById(id);
        String zhType=user.getZhType();
        model.addAttribute("user",user);
        if(zhType.equals("business")){
            model.addAttribute("userType","商业公司");
        }else if(zhType.equals("privated")){
            model.addAttribute("userType","个体工商户");
        }else if(zhType.equals("personal")){
            model.addAttribute("userType","个人经营");
        }else if(zhType.equals("zhengfu")){
            model.addAttribute("userType","政府及非营利组织");
        }

        return "check";
    }
    //实名认证审核结果----同意或驳回
    @PostMapping("/auth_cert2")
    public String auth_cert2(Model model,
                             @RequestParam("opinionxx")String opinionxx,
                             @RequestParam("zhanghu")String zhanghu,
                             @RequestParam("reason")String reason){
        System.out.println(opinionxx);
        if("yes".equals(opinionxx)){
            userService.updateOpByZh(zhanghu,"2","审核通过");
            return "redirect:auth_cert";

        }else if("no".equals(opinionxx)){
            userService.updateOpByZh(zhanghu,"3",reason);
            System.out.println(reason+"-----------------");
            //session.setAttribute("reason",reason);
            return "redirect:auth_cert";
        }
        return "auth_cert";
    }
    //查看驳回意见
    @GetMapping("/suggesttion")
    public String suggesttion(Model model,HttpSession session){
        //从数据库中查询驳回的原因
        String zhanghu=(String)session.getAttribute("zhanghu");
        //System.out.println("zhanghu==="+zhanghu);
        User userByZh = userService.findUserByZh(zhanghu);
        String opinion = userByZh.getOpinion();
        //System.out.println("驳回原因=="+opinion);
        model.addAttribute("reason",opinion);
        return "suggesttion";
    }
    @PostMapping("/suggesttion")
    public String suggesttoin(){
        return "accttype";
    }
    //资质维护
    @GetMapping("/cert")
    public String cert(){
        return "cert";
    }
    //分类管理
    @GetMapping("/type")
    public String type(){
        return "type";
    }
    //广告管理
    @GetMapping("/advertisement")
    public String advertisement(){
        return "advertisement";
    }
    //消息模板
    @GetMapping("/message")
    public String message(){
        return "message";
    }


}
