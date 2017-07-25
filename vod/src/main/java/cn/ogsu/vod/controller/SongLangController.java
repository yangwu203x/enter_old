package cn.ogsu.vod.controller;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import cn.ogsu.vod.service.ISongLangService;
import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;
/**
 * 歌曲语种的controller
 * @author albert
 * @time 2016年9月20日
 */
@Controller
@RequestMapping("/songLang")
public class SongLangController extends ControllerBase{
	@Resource
	private ISongLangService songLangService;
	/**
	 * 显示歌曲语种信息
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/showSongLang")
	public ModelAndView showSongLang(Page page)throws Exception{
		//准备视图数据对象
		ModelAndView mv=new ModelAndView();
		//获取请求数据
		PageData pd=getPageData();
		page.setPd(pd);
		//访问数据库操作
		List<PageData> songLangList=songLangService.pageSongLangs(page);
		mv.addObject("songLang", pd);
		//封装数据
		mv.addObject("songLangList",songLangList);
		mv.setViewName("/songLang_list");
		return mv;
	}
	@RequestMapping("/showSongLangEdit")
	public ModelAndView showSongLangEdit(HttpServletRequest request) throws Exception{
		//准备视图数据对象
		ModelAndView mv=new ModelAndView();
		//获取要访问的视图
		 String viewName=request.getParameter("viewName");
		//如果请求是前往编辑歌曲语种页面
		 if(("2").equals(request.getParameter("operateType"))){
			 //获取修改歌曲语种的编号
			 String langId=request.getParameter("langId");
			 //执行数据库操作
			 PageData songLang=songLangService.singleSongLang(langId);
			 mv.addObject("songLang", songLang);
			 //增加请求路径
			 mv.addObject("requestUrl","updateSongLang");
		 }else{
			 mv.addObject("requestUrl","addSongLang");
		 }
		//设置视图
		mv.setViewName(viewName);
		return mv;
	}
	/**
	 * 增加歌曲语种信息
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/addSongLang")
	public Object addSongLang() throws Exception{
		return songLangService.addSongLang(getPageData());
	}
	/**
	 * 修改歌曲语种信息
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/updateSongLang")
	public Object updateSongLang() throws Exception{
		return songLangService.updateSongLang(getPageData());
	}
	/**
	 * 审核
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/passCheck")
	public Object passCheck() throws Exception{
		PageData pd=getPageData();
		return songLangService.updateStatus(pd);
	}
}