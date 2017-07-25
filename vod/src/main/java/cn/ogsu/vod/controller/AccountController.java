package cn.ogsu.vod.controller;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import cn.ogsu.vod.service.IAccountService;
import cn.ogsu.vod.service.IMenuService;
import cn.ogsu.vod.service.IRoleService;
import cn.ogsu.vod.util.Const;
import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;
import net.sf.json.JSONArray;
/**
 * 首次访问和登陆时的controller
 * @author albert
 * @time 2016年9月6日
 */
@Controller
@RequestMapping("/account")
public class AccountController extends ControllerBase{
	@Resource
	private IAccountService accountService;
	@Resource
	private IMenuService menuService;
	@Resource
	private IRoleService roleService;
	/**
	 *首次访问跳转到登陆页面
	 * @return 待访问的页面
	 */
	@RequestMapping("/forwardLogin")
	public String directLoginPage(){
		//重定向至登陆页面
		return "redirect:/page/login.jsp"; 
	}
	/**
	 * 显示所有的账户信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/accounts")
	public ModelAndView accountList(Page page,HttpServletRequest request) throws Exception{
		//准备模型视图数据
		ModelAndView mv=new ModelAndView();
		PageData user=getPageData();
		page.setPd(user);
		//分页获取菜单信息
		List<PageData> accounts=accountService.accountList(page);
		mv.addObject("accountList",accounts);
		mv.addObject("account", user);
		mv.setViewName("account_list");
		return mv;
	}
	/**
	 * 显示添加或修改账户页面
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/showAccountEdit")
	public ModelAndView showAccountEdit(HttpServletRequest request) throws Exception{
		ModelAndView mv=new ModelAndView();
		//获取所有角色信息
		List<PageData> roles=roleService.allRole();
		mv.addObject("roles",roles);
		//往模型视图中添加数据
		if(request.getParameter("operateType").equals("2")){
			mv.addObject("requestUrl","modifyAccount");
			PageData pd=accountService.getAccountByNo(request.getParameter("account"));
			pd.remove("password");
			mv.addObject("account",pd);
		}else{
			mv.addObject("requestUrl","regist");
		}
		mv.setViewName(request.getParameter("viewName"));
		return mv;
	}
	/**
	 * 注册账户信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/regist",method=RequestMethod.POST)
	public Object registAccount() throws Exception{
		return accountService.addAccount(getPageData());
	}
	
	/**
	 * 账户登陆
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView accountLogin(HttpServletRequest request) throws Exception{
		//执行数据库操作
		PageData data=accountService.getAccount(getPageDataConIp());
		//实例化页面视图对象并填充数据
		ModelAndView mv=new ModelAndView();
		//登陆成功后保存用户信息
		request.getSession().setAttribute(Const.SESSION_USER, data);
		//当输入的用户名和密码匹配不上时重定向至登陆页面
		if(data==null) mv.setViewName("redirect:/page/login.jsp");
		else{
			mv.addObject("account",data);
			mv.setViewName("redirect:/index/showIndex.do");
		}
		//获取用户角色权限
		String userRole=data.getString("userRole");
		PageData rolePrivilege=roleService.findSingleRole(userRole);
		request.getSession().setAttribute(Const.SESSION_ROLE, rolePrivilege);
		return mv;
	}
	//修改账户信息
	@ResponseBody
	@RequestMapping("/modifyAccount")
	public Object modifyAccount() throws Exception{
		return accountService.updateAccount(getPageData());
	}
	/**
	 * 账户注销或者切换账户
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cancelAccount")
	public ModelAndView destroyAccount(HttpServletRequest request) throws Exception{
		request.getSession().removeAttribute(Const.SESSION_USER);
		ModelAndView mv=new ModelAndView();
		mv.setViewName("redirect:/page/login.jsp");
		return mv;
	}
	/**
	 * 删除账户
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/delAccount")
	public Object delAccount() throws Exception{
		boolean flag=false;
		PageData pd=getPageData();
		flag=accountService.deleteAccount(pd);
		return flag;
	}
	/**
	 * 获取账户拥有权限的菜单编号
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/privilegeMenu")
	public Object givePrivilegeMenu(HttpServletRequest request) throws Exception{
		String account=request.getParameter("account");
		List<String> prilegeMenu=menuService.obtainComplexMenu(account);
		return prilegeMenu;
	}
	/**
	 * 给账户分配权限
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/givePrivilege")
	public Object giveAccountPrivilege()throws Exception{
		return accountService.addPrivilege(getPageData());
	}
	/**
	 * 取消账户权限
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/cancelPrivilege")
	public Object cancelAccountPrivilege()throws Exception{
		return accountService.delPrivilege(getPageData());
	}
	/**
	 * 自动补全功能
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/autoSearch")
	public Object autoSearch()throws Exception{
		List<String> accounts=accountService.autoSearch(getPageData());
		JSONArray json=JSONArray.fromObject(accounts);
		return json.toString();
	}
}