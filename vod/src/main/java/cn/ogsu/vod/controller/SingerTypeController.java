package cn.ogsu.vod.controller;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import cn.ogsu.vod.service.ISingerTypeService;
import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;
/**
 * 歌手类型的相关任务的controller
 * @author albert
 * @time 2016年9月14日
 */
@Controller
@RequestMapping("/singerType")
public class SingerTypeController extends ControllerBase{
	@Resource
	private ISingerTypeService singerTypeService;
	/**
	 * 分页显示所有的歌手类型信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/showSingerType")
	public ModelAndView showSingerType(Page page) throws Exception{
		//准备视图数据对象
		ModelAndView mv=new ModelAndView();
		//获取请求数据
		PageData pd=getPageData();
		page.setPd(pd);
		//访问数据库操作
		List<PageData> singerTypeList=singerTypeService.singerTypeList(page);
		mv.addObject("singerType",pd);
		//封装数据
		mv.addObject("singerTypeList",singerTypeList);
		mv.setViewName("/singerType_list");
		return mv;
	}
	/**
	 * 显示增加或修改页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/showSingerTypeEdit")
	public ModelAndView showSingerTypeEdit(HttpServletRequest request) throws Exception{
		//准备视图数据对象
		ModelAndView mv=new ModelAndView();
		//获取要访问的视图
		 String viewName=request.getParameter("viewName");
		//如果请求是前往编辑歌曲页面
		 if(("2").equals(request.getParameter("operateType"))){
			 //获取修改歌手类型的编号
			 String singerTypeId=request.getParameter("singerTypeId");
			 //执行数据库操作
			 PageData singerType=singerTypeService.singerType(singerTypeId);
			 mv.addObject("singerType", singerType);
			 //增加请求路径
			 mv.addObject("requestUrl","updateSingerType");
		 }else{
			 mv.addObject("requestUrl","addSingerType");
		 }
		//设置视图
		mv.setViewName(viewName);
		return mv;
	}
	/**
	 * 增加歌手类型数据
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/addSingerType")
	public Object addSingerType() throws Exception{
		//获取请求数据
		PageData pd=getPageData();
		//执行增加操作
		return singerTypeService.addSingerType(pd);
	}
	/**
	 * 修改歌手类型数据
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/updateSingerType")
	public Object updateSingerType() throws Exception{
		//获取歌手类型数据
		PageData pd=getPageData();
		//执行修改操作
		return singerTypeService.updateSingerType(pd);
	}
	/**
	 * 修改显示状态
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/passCheck")
	public Object passCheck() throws Exception{
		PageData pd=getPageData();
		return singerTypeService.updateStatus(pd);
	}
}