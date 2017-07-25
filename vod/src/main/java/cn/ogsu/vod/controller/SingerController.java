package cn.ogsu.vod.controller;
import java.io.File;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import cn.ogsu.vod.service.ISingerService;
import cn.ogsu.vod.service.ISingerTypeService;
import cn.ogsu.vod.util.Const;
import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;
import cn.ogsu.vod.util.Tools;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
/**
 * 歌手相关任务的controller
 * @author albert
 * @time 2016年9月14日
 */
@Controller
@RequestMapping("/singer")
public class SingerController extends ControllerBase{
	@Resource
	private ISingerService singerService;
	@Resource
	private ISingerTypeService singerTypeService;
	/**
	 * 获取所有歌手的所有信息
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/showSingerList")
	public ModelAndView showSingerList(Page page) throws Exception{
		//获取请求数据
		PageData pd=getPageData();
		String singerName=pd.getString("singerName");
		pd.put("singerName",singerName.replace("?"," "));
		page.setPd(pd);
		//执行数据获取操作
		List<PageData> singerList=singerService.obtainSingers(page);
		//准备数据视图对象
		ModelAndView mv=new ModelAndView();
		//获取歌手类型的所有数据
		 List<PageData> singerTypeList=singerTypeService.allSingerType();
		 mv.addObject("singerTypeList", singerTypeList);
		mv.addObject("singerList",singerList);
		mv.addObject("singer",pd);
		mv.setViewName("singer_list");
		return mv;
	}
	/**
	 * 显示增加或修改歌手信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/showSingerEdit")
	public ModelAndView showSingerEdit(HttpServletRequest request) throws Exception{
		//准备数据视图对象
		ModelAndView mv=new ModelAndView();
		//获取要访问的视图
		 String viewName=request.getParameter("viewName");
		 //获取歌手类型的所有数据
		 List<PageData> singerTypeList=singerTypeService.allSingerType();
		 mv.addObject("singerTypeList", singerTypeList);
		//如果请求是前往编辑歌曲页面
		 if(("2").equals(request.getParameter("operateType"))){
			 //获取修改歌手的编号
			 String singerId=request.getParameter("singerId");
			 //执行数据库操作
			 PageData singer=singerService.obtainSinger(singerId);
			 mv.addObject("singer", singer);
			 mv.addObject("requestUrl","updateSinger");
		 }else{
			 mv.addObject("requestUrl","addSinger");
		 }
		//设置视图
		mv.setViewName(viewName);
		return mv;
	}
	private  boolean validateFile(String fileName) throws Exception{
		if(!fileName.endsWith("png")&&!fileName.endsWith("jpg")){
			return false;
		}
		return true;
	}
	/**
	 * 增加歌手信息
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/addSinger")
	public Object addSinger(@RequestParam MultipartFile headerFile,HttpServletRequest request) throws Exception{
		if(headerFile!=null){
			if(!validateFile(headerFile.getOriginalFilename())){
				return false;
			}
		}
		PageData pd=new PageData();
		pd.put("singerId", request.getParameter("singerId"));
		String singerName=request.getParameter("singerName");
		pd.put("singerName",singerName);
		pd.put("singerType",request.getParameter("singerType"));
		pd.put("singerPinyin",request.getParameter("singerPinyin"));
		pd.put("singerVersion", request.getParameter("singerVersion"));
		//获取歌手字数及歌手的名字字符串长度
		pd.put("singerZs",singerName.length());
		//获取项目所在的绝对路径
		String absolutePath=request.getServletContext().getRealPath("/");
		//获取头像文件保存的路径
		String filePath=Tools.propertiesFileResolve(Const.HEADERFILELOCATION);
		//对歌手头像文件重命名
		String headerFileName=Tools.fileRename(headerFile.getOriginalFilename());
		//获取图片的完整文件名
		pd.put("headerPath",  Const.HEADERLOCATION+"/"+headerFileName);
		//获取文件保存的绝对位置
		String pathName=absolutePath+filePath;
		//处理文件上传的路径
		File directory=new File(pathName);
		if(!directory.exists()){
			directory.mkdirs();
		}
		pathName+=headerFileName;
		//复制文件
		headerFile.transferTo(new File(pathName));	
		//增加歌手
		return singerService.addSinger(pd);
	}
	/**
	 * 修改歌手信息和头像
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/updateSinger")
	public Object updateSinger(@RequestParam MultipartFile headerFile,HttpServletRequest request) throws Exception{
		if(headerFile!=null){
			if(!validateFile(headerFile.getOriginalFilename())){
				return false;
			}
		}
		//准备封装数据的对象
		PageData pd=new PageData();
		pd.put("singerId", request.getParameter("singerId"));
		String singerName=request.getParameter("singerName");
		pd.put("singerName",singerName);
		pd.put("singerType",request.getParameter("singerType"));
		pd.put("singerPinyin",request.getParameter("singerPinyin"));
		pd.put("singerVersion", request.getParameter("singerVersion"));
		//获取歌手字数及歌手的名字字符串长度
		pd.put("singerZs",singerName.length());
		//获取项目所在的绝对路径
		String absolutePath=request.getServletContext().getRealPath("/");
		//获取头像文件保存的路径
		String filePath=Tools.propertiesFileResolve(Const.HEADERFILELOCATION);
		String headerFileName=Tools.fileRename(headerFile.getOriginalFilename());
		//获取图片的完整文件名
		pd.put("headerPath", Const.HEADERLOCATION+"/"+headerFileName);
		//获取文件保存的绝对位置
		String pathName=absolutePath+filePath;
		//处理文件上传的路径
		File directory=new File(pathName);
		if(!directory.exists()){
			directory.mkdirs();
		}
		pathName+=headerFileName;
		//复制文件
		headerFile.transferTo(new File(pathName));		
		//修改歌手
		return singerService.updateSinger(pd);
	}
	/**
	 * 修改歌手信息和头像
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/modifySinger")
	public Object updateSinger(HttpServletRequest request) throws Exception{
		//准备封装数据的对象
		PageData pd=new PageData();
		pd.put("singerId", request.getParameter("singerId"));
		String singerName=request.getParameter("singerName");
		pd.put("singerVersion", request.getParameter("singerVersion"));
		pd.put("singerName",singerName);
		pd.put("singerType",request.getParameter("singerType"));
		pd.put("singerPinyin",request.getParameter("singerPinyin"));
		//获取歌手字数及歌手的名字字符串长度
		pd.put("singerZs",singerName.length());
		//修改歌手
		return singerService.updateSinger(pd);
	}
	/**
	 * 删除歌手操作
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("deleteSinger")
	public Object deleteSinger(HttpServletRequest request) throws Exception{
		String singerId=request.getParameter("singerId");
		return singerService.deleteSinger(singerId);
	} 
	/**
	 * 审核
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("passCheck")
	public Object passCheck() throws Exception{
		PageData pd=getPageData();
		return singerService.updateStatus(pd);
	}
	/**
	 * 自动补全功能
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/autoSearch")
	public Object autoSearch()throws Exception{
		List<String> results=singerService.autoSearch(getPageData());
		JSONArray json=JSONArray.fromObject(results);
		return json.toString();
	}
	
	/**
	 * 根据歌星名去获取可能的歌星编号
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/obtainIdByName")
	public Object obtainIdByName(HttpServletRequest request)throws Exception{
		String singerName=request.getParameter("singerName");
		//获取可能的编号
		List<Integer> singerIds=singerService.findIdByName(singerName);
		return singerIds;
	}
}