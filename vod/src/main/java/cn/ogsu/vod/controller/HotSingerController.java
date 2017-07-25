package cn.ogsu.vod.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import cn.ogsu.vod.service.IHotSingerService;
import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;

/**
 * 热门歌手的controller层
 * @author albert
 *
 */
@Controller
@RequestMapping("/hotSinger")
public class HotSingerController extends ControllerBase{

	@Resource
	private IHotSingerService hotSingerService;
	
	/**
	 *显示热门歌手列表
	 */
	@RequestMapping("/showHotSingers")
	public ModelAndView hotSingerList(Page page)throws Exception{
		//准备视图数据对象
		ModelAndView mv=new ModelAndView();
		//获取请求数据
		PageData pd=getPageData();
		page.setPd(pd);
		//访问数据库操作
		List<PageData> hotSingerList=hotSingerService.hotSingers(page);
		//封装数据
		mv.addObject("hotSingers",hotSingerList);
		mv.setViewName("/hotSinger_list");
		return mv;
	}
	
	/**
	 * 显示歌曲主题编辑页面
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/hotSingerEdit")
	public ModelAndView hotSingerEdit(HttpServletRequest request) throws Exception{
		//准备视图数据对象
		ModelAndView mv=new ModelAndView();
		//获取要访问的视图
		 String viewName=request.getParameter("viewName");
		//如果请求是前往编辑页面
		 if(("2").equals(request.getParameter("operateType"))){
			 //获取修改歌曲类型的编号
			 String hotId=request.getParameter("hotId");
			 PageData singer=new PageData();
			 singer.put("hotId", hotId);
			 //执行数据库操作
			 PageData hotSinger=hotSingerService.obtainHotSingerById(singer);
			 mv.addObject("hotSinger", hotSinger);
			 //增加请求路径
			 mv.addObject("requestUrl","updateHotSinger");
		 }else{
			 mv.addObject("requestUrl","addHotSinger");
		 }
		//设置视图
		mv.setViewName(viewName);
		return mv;
	}
	/**
	 * 增加热门歌手
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/addHotSinger")
	public Object addHotSinger() throws Exception{
		return hotSingerService.addHotSinger(getPageData());
	}
	/**
	 * 修改热门歌手
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/updateHotSinger")
	public Object updateHotSinger() throws Exception{
		return hotSingerService.updateHotSinger(getPageData());
	}
	/**
	 * 删除热门歌手
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/delHotSingers")
	public Object delHotSingers() throws Exception{
		return hotSingerService.delHotSinger(getPageData());
	}
}
