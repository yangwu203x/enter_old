package cn.ogsu.vod.controller;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import cn.ogsu.vod.service.IUserInfoService;
import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;
import net.sf.json.JSONArray;
/**
 *用户信息的controller层
 * @author albert
 * @time 2016年9月22日
 */
@Controller
@RequestMapping("userInfo")
public class UserInfoController extends ControllerBase{
	@Resource
	private IUserInfoService userInfoService;
	/**
	 * 分页显示所有的用户信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/showUser")
	public ModelAndView showUserList(Page page) throws Exception{
		//准备视图数据对象
		ModelAndView mv=new ModelAndView();
		//获取请求数据
		PageData pd=getPageData();
		page.setPd(pd);
		//访问数据库操作
		List<PageData> userInfoList=userInfoService.obtainPageUser(page);
		//封装数据
		mv.addObject("userList",userInfoList);
		mv.addObject("userInfo",pd);
		mv.setViewName("/user_list");
		return mv;
	}
	/**
	 * 显示增加或修改页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/showUserEdit")
	public ModelAndView showUserEdit(HttpServletRequest request) throws Exception{
		//准备视图数据对象
		ModelAndView mv=new ModelAndView();
		//获取要访问的视图
		 String viewName=request.getParameter("viewName");
		//如果请求是前往编辑歌曲页面
		 if(("2").equals(request.getParameter("operateType"))){
			 //获取用户的编号
			 String user_id=request.getParameter("user_id");
			 //执行数据库操作
			 PageData userInfo=userInfoService.obtainSingleUser(user_id);
			 mv.addObject("userInfo", userInfo);
			 //增加请求路径
			 mv.addObject("requestUrl","updateUser");
		 }else{
			 mv.addObject("requestUrl","addUser");
		 }
		//设置视图
		mv.setViewName(viewName);
		return mv;
	}
	/**
	 * 增加用户信息
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/addUser")
	public Object addUser() throws Exception{
		//获取请求数据
		PageData pd=getPageData();
		//执行增加操作
		return userInfoService.addUser(pd);
	}
	/**
	 * 修改用户信息
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/updateUser")
	public Object updateUser() throws Exception{
		//获取歌手类型数据
		PageData pd=getPageData();
		//执行修改操作
		return userInfoService.updateUser(pd);
	}
	/**
	 * 删除用户操作
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("deleteUser")
	public Object deleteUser() throws Exception{
		PageData pd=getPageData();
		return userInfoService.delUsers(pd);
	}
	/**
	 * 自动补全功能
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/autoSearch")
	public Object autoSearch()throws Exception{
		List<String> results=userInfoService.autoSearch(getPageData());
		JSONArray json=JSONArray.fromObject(results);
		return json.toString();
	}
}