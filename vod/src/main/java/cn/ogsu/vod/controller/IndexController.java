package cn.ogsu.vod.controller;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import cn.ogsu.vod.entity.Menu;
import cn.ogsu.vod.service.IMenuService;
import cn.ogsu.vod.util.Const;
import cn.ogsu.vod.util.PageData;
/**
 * 负责首页逻辑的controller
 * @author albert
 * @time 2016年9月6日
 */
@Controller
@RequestMapping("/index")
public class IndexController extends ControllerBase{
	@Resource
	private IMenuService menuService;
	/**
	 * 前往首页
	 * @return
	 */
	@RequestMapping("/showIndex")
	public ModelAndView showIndex(HttpServletRequest request) throws Exception{
		List<Menu> menuList=menuService.obtainAllMenu();
		//将所有的菜单保存至session中
		request.getSession().setAttribute(Const.SESSION_MENU, menuList);
		PageData pd=(PageData) request.getSession().getAttribute(Const.SESSION_USER);
		String accountNo=pd.getString("accountNo");
		//获取当前账户有权限操作的菜单
		List<Menu> menuAccountList=menuService.obtainAccountMenu(accountNo);
		//准备视图和数据
		ModelAndView mv=new ModelAndView();
		mv.addObject("menuList",menuAccountList);
		mv.setViewName("index");
		return mv;
	}
	@RequestMapping("/showView")
	public String showView(HttpServletRequest request) throws Exception{
		//获取要访问的视图
		 String viewName=request.getParameter("viewName");
		return viewName;
	}
}