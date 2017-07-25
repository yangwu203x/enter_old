package cn.ogsu.vod.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 新歌的controller层
 * @author albert
 */
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.ogsu.vod.service.INewSongService;
import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;
@Controller
@RequestMapping("/newSong")
public class NewSongController extends ControllerBase{

	@Resource
	private INewSongService newSongService;
	
	/**
	 *显示新歌列表
	 */
	@RequestMapping("/showNewSongs")
	public ModelAndView hotNewSongList(Page page)throws Exception{
		//准备视图数据对象
		ModelAndView mv=new ModelAndView();
		//获取请求数据
		PageData pd=getPageData();
		page.setPd(pd);
		//访问数据库操作
		List<PageData> newSongList=newSongService.newSongs(page);
		//封装数据
		mv.addObject("newSongs",newSongList);
		mv.setViewName("/newSong_list");
		return mv;
	}
	
	/**
	 * 显示新歌编辑页面
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/newSongEdit")
	public ModelAndView newSongEdit(HttpServletRequest request) throws Exception{
		//准备视图数据对象
		ModelAndView mv=new ModelAndView();
		//获取要访问的视图
		String viewName=request.getParameter("viewName");
		//如果请求是前往编辑页面
		if(("2").equals(request.getParameter("operateType"))){
			 //获取修改歌曲类型的编号
			 String newId=request.getParameter("newId");
			 PageData song=new PageData();
			 song.put("newId", newId);
			 //执行数据库操作
			 PageData newSong=newSongService.singleNewSong(song);
			 mv.addObject("newSong", newSong);
			 //增加请求路径
			 mv.addObject("requestUrl","updateNewSong");
		 }else{
			 mv.addObject("requestUrl","addNewSong");
		 }
		//设置视图
		mv.setViewName(viewName);
		return mv;
	}
	/**
	 * 增加新歌
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/addNewSong")
	public Object addNewSong() throws Exception{
		PageData song=getPageData();
		return newSongService.addNewSong(song);
	}
	/**
	 * 修改新歌
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/updateNewSong")
	public Object updateNewSong() throws Exception{
		return newSongService.updateNewSong(getPageData());
	}
	/**
	 * 删除新歌
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/delNewSongs")
	public Object delNewSongs() throws Exception{
		return newSongService.delNewSong(getPageData());
	}
}
