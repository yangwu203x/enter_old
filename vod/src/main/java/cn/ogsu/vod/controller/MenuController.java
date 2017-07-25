package cn.ogsu.vod.controller;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import cn.ogsu.vod.entity.Menu;
import cn.ogsu.vod.service.IMenuService;
import cn.ogsu.vod.util.Const;
import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;
/**
 * 负责菜单的controller
 * @author albert
 * @time 2016年9月6日
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends ControllerBase{
	@Resource
	private IMenuService menuService;
	/**
	 * 控制layer弹出层的视图显示
	 * @return
	 */
	@RequestMapping("/showAddMenu")
	public ModelAndView showView(HttpServletRequest request) throws Exception{
		ModelAndView mv=new ModelAndView();
		//获取要访问的视图
		 String viewName=request.getParameter("viewName");
		 if(("2").equals(request.getParameter("operateType"))){
			 String menuNo=request.getParameter("menuNo");
			 Menu menu=menuService.obtainSingleMenu(menuNo);
			 mv.addObject("editMenu", menu);
			 mv.addObject("requestUrl", "modifyMenu");
		 }else{
			 mv.addObject("requestUrl", "addMenu");
		 }
		//设置视图
		mv.setViewName(viewName);
		return mv;
	}
	/**
	 * 获取父级菜单
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/parentMenu")
	@SuppressWarnings("unchecked")
	public Object obtainParentMenu(HttpServletRequest request) throws Exception{
		Object obj=request.getSession().getAttribute(Const.SESSION_MENU);
		List<Menu> menuList=null;
		if(obj!=null){
			menuList=(List<Menu>) obj;
			for (int i = 0; i <menuList.size(); i++) {
				menuList.get(i).setSubMenuList(null);
			}
		}
		return menuList;
	}
	/**
	 * 增加菜单
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/addMenu")
	public Object addMenu() throws Exception{
		//准备菜单对象
		PageData pd=getPageData();
		Menu menu=new Menu();
		menu.setMenuName(pd.getString("menuName"));
		String menuIcon=pd.getString("menuIcon");
		if(!("").equals(menuIcon))	menuIcon="&#"+menuIcon+";";
		menu.setMenuIcon(menuIcon);
		String menuUrl=pd.getString("menuUrl");
		if(("").equals(menuUrl)) menuUrl="#";
		menu.setMenuUrl(menuUrl);
		String parentMenuNo=pd.getString("parentMenuNo");
		if(("").equals(parentMenuNo))menu.setParentMenuNo(0);
		else menu.setParentMenuNo(Integer.valueOf(parentMenuNo));
		//执行增加操作
		return menuService.addMenu(menu);
	}
	/**
	 * 修改菜单
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/modifyMenu")
	public Object modifyMenu() throws Exception{
		//准备菜单对象
		PageData pd=getPageData();
		Menu menu=new Menu();
		menu.setMenuNo((int)pd.get("menuNo"));
		menu.setMenuName(pd.getString("menuName"));
		menu.setMenuIcon(pd.getString("menuIcon"));
		menu.setMenuUrl(pd.getString("menuUrl"));
		menu.setParentMenuNo((int)pd.get("parentMenuNo"));
		//执行修改操作
		return menuService.updateMenu(menu);
	}
	/**
	 * 删除菜单
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/delMenu")
	public Object delMenu() throws Exception{
		boolean flag=false;
		PageData pd=getPageData();
		flag=menuService.deleteMenu(pd);
		return flag;
	}
	/**
	 * 获取所有的菜单
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/allMenu")
	public ModelAndView obainAllMenu(Page page) throws Exception{
		PageData pd=getPageData();
		page.setPd(pd);
		//获取分页数据
		List<Menu> menuList=menuService.obtainPageMenu(page);
		//准备返回视图数据
		ModelAndView mv=new ModelAndView();
		mv.addObject("menuList",menuList);
		mv.addObject("menu", pd);
		mv.setViewName("menu_list");
		return mv;
	}
	/**
	 * 获取菜单数据
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@SuppressWarnings("unchecked")
	@RequestMapping("/obtainMenuList")
	public Object obtainMenuList(HttpSession session) throws Exception{
		//在查看所有账户信息时将菜单信息放入session中方便权限分配
		List<Menu> menuList=(List<Menu>) session.getAttribute(Const.SESSION_MENU);
		return menuList;
	}
}