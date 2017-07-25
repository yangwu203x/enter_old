package cn.ogsu.vod.controller;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import cn.ogsu.vod.service.ISongTypeService;
import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;
/**
 * 歌曲类别的controller
 * @author albert
 * @time 2016年9月20日
 */
@Controller
@RequestMapping("/songType")
public class SongTypeController extends ControllerBase{
	@Resource
	private ISongTypeService songTypeService;
	/**
	 * 显示歌曲类型列表
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/showSongType")
	public ModelAndView showSongType(Page page)throws Exception{
		//准备视图数据对象
		ModelAndView mv=new ModelAndView();
		//获取请求数据
		PageData pd=getPageData();
		page.setPd(pd);
		//访问数据库操作
		List<PageData> songTypeList=songTypeService.songTypeList(page);
		mv.addObject("songType",pd);
		//封装数据
		mv.addObject("songTypeList",songTypeList);
		mv.setViewName("/songType_list");
		return mv;
	}
	@RequestMapping("/showSongTypeEdit")
	public ModelAndView showSongTypeEdit(HttpServletRequest request) throws Exception{
		//准备视图数据对象
		ModelAndView mv=new ModelAndView();
		//获取要访问的视图
		 String viewName=request.getParameter("viewName");
		//如果请求是前往编辑歌曲页面
		 if(("2").equals(request.getParameter("operateType"))){
			 //获取修改歌曲类型的编号
			 String typeId=request.getParameter("typeId");
			 //执行数据库操作
			 PageData songType=songTypeService.singleSongType(typeId);
			 mv.addObject("songType", songType);
			 //增加请求路径
			 mv.addObject("requestUrl","updateSongType");
		 }else{
			 mv.addObject("requestUrl","addSongType");
		 }
		//设置视图
		mv.setViewName(viewName);
		return mv;
	}
	/**
	 * 增加歌曲类型
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/addSongType")
	public Object addSongType() throws Exception{
		return songTypeService.addSongType(getPageData());
	}
	/**
	 * 修改歌曲类型
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/updateSongType")
	public Object updateSongType() throws Exception{
		return songTypeService.updateSongType(getPageData());
	}
	/**
	 * 审核
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/passCheck")
	public Object passCheck() throws Exception{
		PageData pd=getPageData();
		return songTypeService.updateStatus(pd);
	}
}