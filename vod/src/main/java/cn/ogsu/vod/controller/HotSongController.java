package cn.ogsu.vod.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.ogsu.vod.service.IHotSongService;
import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;

/**
 * 热门歌曲的controller层
 * @author albert
 */
@Controller
@RequestMapping("/hotSong")
public class HotSongController extends ControllerBase{

	@Resource
	private IHotSongService hotSongService;
	/**
	 *显示热门歌曲列表
	 */
	@RequestMapping("/showHotSongs")
	public ModelAndView hotSongList(Page page)throws Exception{
		//准备视图数据对象
		ModelAndView mv=new ModelAndView();
		//获取请求数据
		PageData pd=getPageData();
		page.setPd(pd);
		//访问数据库操作
		List<PageData> hotSongList=hotSongService.hotSongs(page);
		//封装数据
		mv.addObject("hotSongs",hotSongList);
		mv.setViewName("/hotSong_list");
		return mv;
	}
	
	/**
	 * 显示热们歌曲编辑页面
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/hotSongEdit")
	public ModelAndView hotSongEdit(HttpServletRequest request) throws Exception{
		//准备视图数据对象
		ModelAndView mv=new ModelAndView();
		//获取要访问的视图
		 String viewName=request.getParameter("viewName");
		//如果请求是前往编辑页面
		 if(("2").equals(request.getParameter("operateType"))){
			 //获取修改歌曲类型的编号
			 String hotId=request.getParameter("hotId");
			 PageData song=new PageData();
			 song.put("hotId", hotId);
			 //执行数据库操作
			 PageData hotSong=hotSongService.obtainHotSongById(song);
			 mv.addObject("hotSong", hotSong);
			 //增加请求路径
			 mv.addObject("requestUrl","updateHotSong");
		 }else{
			 mv.addObject("requestUrl","addHotSong");
		 }
		//设置视图
		mv.setViewName(viewName);
		return mv;
	}
	/**
	 * 增加热门歌曲
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/addHotSong")
	public Object addHotSong() throws Exception{
		return hotSongService.addHotSong(getPageData());
	}
	/**
	 * 修改热门歌曲
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/updateHotSong")
	public Object updateHotSong() throws Exception{
		return hotSongService.updateHotSong(getPageData());
	}
	/**
	 * 删除热门歌曲
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/delHotSongs")
	public Object delHotSongs() throws Exception{
		return hotSongService.delHotSong(getPageData());
	}
	
	
}
