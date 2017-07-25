package cn.ogsu.vod.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import cn.ogsu.vod.service.IThemeService;
import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;

/**
 * 歌曲主题的controller
 * @author albert
 * 2016/11/03
 */
@Controller
@RequestMapping("/theme")
public class ThemeController extends ControllerBase{

	@Resource
	private IThemeService themeService;
	
	/**
	 *显示歌曲主题列表页面
	 */
	@RequestMapping("/showTheme")
	public ModelAndView themeList(Page page)throws Exception{
		//准备视图数据对象
		ModelAndView mv=new ModelAndView();
		//获取请求数据
		PageData pd=getPageData();
		page.setPd(pd);
		//访问数据库操作
		List<PageData> themeList=themeService.themeList(page);
		//封装数据
		mv.addObject("themeList",themeList);
		mv.setViewName("/theme_list");
		return mv;
	}
	/**
	 * 显示歌曲主题编辑页面
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/showThemeEdit")
	public ModelAndView showThemeEdit(HttpServletRequest request) throws Exception{
		//准备视图数据对象
		ModelAndView mv=new ModelAndView();
		//获取要访问的视图
		 String viewName=request.getParameter("viewName");
		//如果请求是前往编辑页面
		 if(("2").equals(request.getParameter("operateType"))){
			 //获取修改歌曲类型的编号
			 String themeId=request.getParameter("themeId");
			 //执行数据库操作
			 PageData theme=themeService.singerTheme(themeId);
			 theme.put("themeId", themeId);
			 mv.addObject("theme", theme);
			 //增加请求路径
			 mv.addObject("requestUrl","updateTheme");
		 }else{
			 mv.addObject("requestUrl","addTheme");
		 }
		//设置视图
		mv.setViewName(viewName);
		return mv;
	}
	/**
	 * 增加歌曲主题
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/addTheme")
	public Object addTheme() throws Exception{
		return themeService.addTheme(getPageData());
	}
	/**
	 * 修改歌曲主题
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/updateTheme")
	public Object updateTheme() throws Exception{
		return themeService.updateTheme(getPageData());
	}
	/**
	 * 审核
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/passCheck")
	public Object passCheck() throws Exception{
		PageData pd=getPageData();
		//设置状态为通过审核
		return themeService.updateStatus(pd);
	}
}
