package com.qf.j1902.controller;

import com.qf.j1902.pojo.*;
import com.qf.j1902.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2019/5/31.
 */
@Controller
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;
    @Autowired
    private TagTypeService tagTypeService;
    @Autowired
    private ZhongChouService zhongChouService;
    @Autowired
    private RepayService repayService;
    //展示所有项目分类
    @GetMapping("/project_type")
    public String project_type(Model model){
        ArrayList<ProjectType> projectTypes = projectService.findAll();
        model.addAttribute("projectTypes",projectTypes);
        return "project_type";
    }
    //新增项目分类
    @GetMapping("/form")
    public String addProjectType(){
        return "form";
    }
    //新增
    @PostMapping("/form")
    public String form(@RequestParam(value = "projectType",defaultValue = "")String projectType,
                       @RequestParam(value = "jianjie",defaultValue = "")String jianjie,
                       Model model
                       ){
        if ("".equals(projectType)||"".equals(jianjie)){
            model.addAttribute("message","请输入完整的项目类型和简介");
            return "form";
        }else {
            boolean addProjectType = projectService.addProjectType(projectType, jianjie);
            System.out.println("增加项目分类成功?"+addProjectType);
            if(addProjectType==true){
                return "redirect:project_type";
            }else{
                model.addAttribute("message","添加失败,请重新操作");
                return "form";
            }
        }
    }
    //修改项目分类
    @GetMapping("/project_type_edit")
    public String project_type_edit(HttpServletRequest request,Model model){
        int id=Integer.parseInt(request.getParameter("id"));
        System.out.println("项目类型id---"+id);
        ProjectType projectTypeById = projectService.getProjectTypeById(id);
        model.addAttribute("projectType",projectTypeById);
        return "project_type_edit";
    }
    //修改
    @PostMapping("/project_type_update")
    public String project_type_update(HttpServletRequest request,Model model,
                                      @RequestParam(value = "id",defaultValue = "")int id,
                                      @RequestParam(value = "projectType",defaultValue = "")String projectType,
                                      @RequestParam(value = "jianjie",defaultValue = "")String jianjie){
        boolean updateProjectType = projectService.updateProjectType(id, projectType, jianjie);
        if(updateProjectType==true){
            return "redirect:project_type";
        }
        return "project_type_edit";
    }
    //删除项目类型
    @GetMapping("projecttype_delete")
    @ResponseBody
    public String projecttype_delete(HttpServletRequest request){
        int id=Integer.parseInt(request.getParameter("id"));
        boolean delProjectType = projectService.delProjectType(id);
        if (delProjectType==true){
            return "111";
        }
        return "000";
    }
    //我的众筹
    @GetMapping("/minecrowdfunding")
    public String minecrowdfunding(){

        return "minecrowdfunding";
    }
    //众筹
    @GetMapping("/project")
    public String project(){
        return "project";
    }
    //众筹项目
    @GetMapping("/projects")
    public String projects(){
        return "projects";
    }
    //发起众筹
    @GetMapping("/start")
    public String start(){
        return "start";
    }
    //项目及发起人信息
    @GetMapping("/start-step-1")
    public String start1(Model model){
        //查询所有项目分类
        ArrayList<ProjectType> projectTypes = projectService.findAll();
        model.addAttribute("projectTypes",projectTypes);
        //查询所有标签以及标签内容
        ArrayList<TagType> tagTypes = tagTypeService.fingAllTagType();
        System.out.println(tagTypes);
        model.addAttribute("tagTypes",tagTypes);
        return "start-step-1";
    }
    //众筹页面内容上传
    @PostMapping("/shangchan")
    public String start2(@RequestParam(value = "projectType",defaultValue = "")String projectType,
                         @RequestParam(value = "tagContent",defaultValue = "")String tagContent,
                         @RequestParam(value = "projectName",defaultValue = "")String projectName,
                         @RequestParam(value = "jianjie",defaultValue = "")String jianjie,
                         @RequestParam(value = "money",defaultValue = "")String money,
                         @RequestParam(value = "days",defaultValue = "")String days,
                         MultipartFile titleImg,
                         MultipartFile xiangqingImg,
                         @RequestParam(value = "introduce",defaultValue = "")String introduce,
                         @RequestParam(value = "introduces",defaultValue = "")String introduces,
                         @RequestParam(value = "phonenumber",defaultValue = "")String phonenumber,
                         @RequestParam(value = "telephone",defaultValue = "")String telephone,
                         HttpServletRequest request,
                         HttpSession session){
        //判断筹资金额
        String regex = "[1-9]\\d{2,9}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(money);
        if(m.matches()){
            int len = money.length();
            System.out.println(len);
            if(len==10){
                String first = money.substring(0,1);
                int i = Integer.parseInt(first);
                if(i>1){
                    return "redirect:start-step-1";
                }
            }
            int jin_i = Integer.parseInt(money);
            System.out.println(jin_i);
            if(jin_i<100||jin_i>1000000000){
                return "redirect:start-step-1";
            }
        }else{
            return "redirect:start-step-1";
        }
        //判断筹资天数
        String regex2 = "[1-9]\\d{1}";
        Pattern pattern  = Pattern.compile(regex2);
        Matcher matcher = pattern.matcher(days);
        if(!matcher.matches()){
            return "redirect:start-step-1";
        }
        //判断手机号
        String regex3 = "1[3,4,5,7,8][0-9]{9}";
        Pattern p3 = Pattern.compile(regex3);
        Matcher m3= p3.matcher(phonenumber);
        if(!m3.matches()){
            return "redirect:start-step-1";
        }
        //上传图片
        String filePath1 = request.getRealPath("/titleImg");
        String filePath2 = request.getRealPath("/xiangqingImg");
        String originalFilename1 = titleImg.getOriginalFilename();//得到上传时的文件名
        String originalFilename2 = xiangqingImg.getOriginalFilename();
        /*if("".equals(titleImg)||"".equals(xiangqingImg)){
            return "redirect:start-step-1";
        }*/
        String newFileName1=null;
        String newFileName2=null;
        if (titleImg != null && originalFilename1 != null && originalFilename1.length() > 0) {
            newFileName1 = UUID.randomUUID() + originalFilename1.substring(originalFilename1.lastIndexOf("."));
            File newFile1 = new File(filePath1,newFileName1);
            try {
                newFile1.mkdirs();
                titleImg.transferTo(newFile1);
            } catch (Exception e) {
            }
        }
        if (xiangqingImg != null && originalFilename2 != null && originalFilename2.length() > 0) {
            newFileName2 = UUID.randomUUID() + originalFilename2.substring(originalFilename2.lastIndexOf("."));
            File newFile2 = new File(filePath2,newFileName2);
            try {
                newFile2.mkdirs();
                xiangqingImg.transferTo(newFile2);
            } catch (Exception e) {
            }
        }
        //将状态默认为0
        String statement="0";
        //根据账户查找会员id
        String zhanghu = (String) session.getAttribute("zhanghu");
        System.out.println("账户==="+zhanghu);
        User userByZh = userService.findUserByZh(zhanghu);
        int id=userByZh.getId();
        session.setAttribute("id",id);
        //获取开始日期以及结束日期
        int days_i=Integer.parseInt(days);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Calendar calendar = Calendar.getInstance();
        Date time1 = calendar.getTime();
        String starttime = sdf.format(time1);
        calendar.add(Calendar.DAY_OF_MONTH,days_i);
        Date time = calendar.getTime();
        String endtime = sdf.format(time);
        //图片路径
        String titleImgxx="titleImg/"+newFileName1;
        String xiangqingImgxx="xiangqingImg/"+newFileName2;
        /*System.out.println(projectType);
        System.out.println(tagContent);
        System.out.println(projectName);
        System.out.println(jianjie);
        System.out.println(money);
        System.out.println(days);
        System.out.println(introduce);
        System.out.println(introduces);
        System.out.println(phonenumber);
        System.out.println(telephone);
        System.out.println(titleImgxx);
        System.out.println(xiangqingImgxx);
        System.out.println("用户id=="+id);
        System.out.println(starttime);
        System.out.println(endtime);*/
        //查找当前用户有没有状态为0的众筹,有--根据用户id修改==没有--添加到数据库
        ZhongChou zhongChou = zhongChouService.getZhongChou(id, "0");
        System.out.println("状态为0的众筹?"+zhongChou);
        if(zhongChou==null){
            //将获取到的内容添加到数据库
            if("".equals(projectType)||"".equals(tagContent)||"".equals(projectName)||"".equals(jianjie)||"".equals(money)||"".equals(days)||"".equals(introduce)||"".equals(introduces)||"".equals(titleImg)||"".equals(xiangqingImg)||"".equals(phonenumber)||"".equals(telephone)){
                return "redirect:start-step-1";
            }else{
                boolean addZhongChou = zhongChouService.addZhongChou(projectType, tagContent, projectName, jianjie, money, days, titleImgxx, xiangqingImgxx, introduce, introduces, phonenumber, telephone, id, statement, starttime, endtime);
                System.out.println("添加数据库成功?"+addZhongChou);
                return "start-step-2";
            }
        }else{
            //对他之前发起的众筹项目进行修改--根据id
            if("".equals(projectType)||"".equals(tagContent)||"".equals(projectName)||"".equals(jianjie)||"".equals(money)||"".equals(days)||"".equals(introduce)||"".equals(introduces)||"".equals(titleImg)||"".equals(xiangqingImg)||"".equals(phonenumber)||"".equals(telephone)){
                return "redirect:start-step-1";
            }else {
                boolean updateZhongChou = zhongChouService.updateZhongChou(id, projectType, tagContent, projectName, jianjie, money, days, titleImgxx, xiangqingImgxx, introduce, introduces, phonenumber, telephone, statement, starttime, endtime);
                return "start-step-2";
            }
        }
    }
    //回报设置
    @GetMapping("start-step-2")
    public String start22(){
        return "start-step-2";
    }
    @PostMapping("/huibao")
    public String start2(@RequestParam(value = "repaytype",defaultValue = "")String repaytype,
                         @RequestParam(value = "jine",defaultValue = "")String jine,
                         @RequestParam(value = "neirong",defaultValue = "")String neirong,
                         @RequestParam(value = "repaycount",defaultValue = "")String repaycount,
                         @RequestParam(value = "xiangou",defaultValue = "")String xiangou,
                         @RequestParam(value = "xiangoucount",defaultValue = "")String xiangoucount,
                         @RequestParam(value = "yunfei",defaultValue = "")String yunfei,
                         @RequestParam(value = "fapiao",defaultValue = "")String fapiao,
                         @RequestParam(value = "repaytime",defaultValue = "")String repaytime,
                         MultipartFile picture, HttpServletRequest request,
                         HttpSession session,Model model){
        //上传图片
        String filePath = request.getRealPath("/repayImg");
        String originalFilename = picture.getOriginalFilename();//得到上传时的文件名
        String newFileName=null;
        if (picture != null && originalFilename != null && originalFilename.length() > 0) {
            newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
            File newFile = new File(filePath,newFileName);
            try {
                newFile.mkdirs();
                picture.transferTo(newFile);
            } catch (Exception e) {
            }
        }
        String repayImg="repayImg/"+newFileName;
        String zhanghu=(String)session.getAttribute("zhanghu");
        User userByZh = userService.findUserByZh(zhanghu);
        int id=userByZh.getId();
        //根据用户id,项目statement查询众筹
        ZhongChou zhongChou = zhongChouService.getZhongChou(id, "0");
        int projectid=zhongChou.getProjectid();
        //添加到数据库
        /*System.out.println(projectid);
        System.out.println(repaytype);
        System.out.println(jine);
        System.out.println(neirong);
        System.out.println(repaycount);
        System.out.println(xiangou);
        System.out.println(yunfei);
        System.out.println(fapiao);
        System.out.println(repaytime);
        System.out.println(repayImg);*/
        if(xiangou=="限购"){
            model.addAttribute("xiangoucount",xiangoucount);
        }else {
            model.addAttribute("xiangoucount","");
        }
        boolean addRepay = repayService.addRepay(repaytype, jine, neirong, repayImg, repaycount, xiangou, yunfei, fapiao, repaytime, projectid);
        System.out.println("添加回报成功?"+addRepay);
        /*ArrayList<Repay> repays=new ArrayList<>();
        Repay repay=new Repay(repaytype,jine,neirong,repayImg,repaycount,xiangou,yunfei,fapiao,repaytime,projectid);
        repays.add(repay);*/
        //查询所有回报
        ArrayList<Repay> repayById = repayService.getRepayById(projectid);
        model.addAttribute("repayxx",repayById);

        return "start-step-2";
    }
    //确认信息
    @GetMapping("/start-step-3")
    public String start3(){
        return "start-step-3";
    }
    //将易付宝及法人身份证存入数据库
    @PostMapping("/confirm")
    public String start33(@RequestParam(value = "yifubao",defaultValue = "")String yifubao,
                          @RequestParam(value = "farenidcard",defaultValue = "")String farenidcard,
                          HttpSession session){
        System.out.println(yifubao+"---"+farenidcard);
        String zhanghu=(String)session.getAttribute("zhanghu");
        User userByZh = userService.findUserByZh(zhanghu);
        int id=userByZh.getId();
        boolean updatexx = zhongChouService.updatexx(id,"1", yifubao, farenidcard);
        System.out.println("xiugaichenggong?"+updatexx);
        return "start-step-4";
    }
    //提交
    @GetMapping("/start-step-4")
    public String start4(){
        return "start-step-4";
    }
    //支付
    @GetMapping("/pay-step-1")
    public String pay1(){
        return "pay-step-1";
    }
    @GetMapping("/pay-step-2")
    public String pay2(){
        return "pay-step-2";
    }
    @GetMapping("/tag")
    public String tag(){
        return "tag";
    }
    //项目审核
    @GetMapping("/auth_project")
    public String auth_project(Model model){
        ArrayList<ZhongChou> zhongChouBySta = zhongChouService.getZhongChouBySta("1");
        model.addAttribute("zhongchous",zhongChouBySta);
        return "auth_project";
    }
    @GetMapping("/xiangmushenhe")
    public String auth_project2(Model model,HttpServletRequest request){
        int projectid=Integer.parseInt(request.getParameter("projectid"));
        System.out.println("projectid===="+projectid);
        ZhongChou zhongChouById = zhongChouService.getZhongChouById(projectid);
        model.addAttribute("zhongchou",zhongChouById);
        return "xiangmushenhe";
    }
    //项目审核
    @GetMapping("/process")
    public String process(){
        return "process";
    }

}
