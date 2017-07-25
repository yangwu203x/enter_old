package cn.ogsu.vod.controller;
import java.io.File;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import cn.ogsu.vod.service.IApkService;
import cn.ogsu.vod.util.Const;
import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;
import cn.ogsu.vod.util.Tools;
/**
 * 用于对apk的管理
 * @author albert
 * @time 2016年9月10日
 */
@Controller
@RequestMapping("/apk")
public class ApkController extends ControllerBase{
	@Resource
	private IApkService apkService;
	/**
	 * 显示增加apk文件的信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/showApkEdit")
	public ModelAndView showAddApk(HttpServletRequest request) throws Exception{
		ModelAndView mv=new ModelAndView();
		mv.addObject("requestUrl","uploadApk");
		//获取即将跳转的视图名
		String viewName=request.getParameter("viewName");
		mv.setViewName(viewName);
		return mv;
	}
	/**
	 * 分页获取apk历史信息
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/apkList")
	public ModelAndView obtainPageApk(Page page) throws Exception{
		//获取搜索条件
		PageData pd=getPageData();
		page.setPd(pd);
		//获取apk历史信息
		List<PageData> apks=apkService.findPageApk(page);
		//封装视图数据
		ModelAndView mv=new ModelAndView();
		mv.addObject("apkList",apks);
		mv.setViewName("apk_list");
		return mv;
	}
	/**
	 * 上传apk文件以及保存apk文件信息
	 * @param apkFile
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/uploadApk")
	public Object uploadApkFile(@RequestParam MultipartFile apkFile, HttpServletRequest request) throws Exception{
		if(apkFile!=null){
			if(!apkFile.getOriginalFilename().endsWith("apk")){
				return false;
			}
		}
		//获取携带其它参数
		PageData pd=new PageData();
		//apk版本
		String apkVersion=request.getParameter("apkVersion");
		pd.put("apkVersion", apkVersion);
		//获取项目所在的绝对路径
		String absolutePath=request.getServletContext().getRealPath("/");
		//获取apk文件保存的路径
		String filePath=Tools.propertiesFileResolve(Const.APKFILELOCATION);
		//保存路径
		pd.put("filePath", filePath);
		//获取文件的原文件名
		String fileName=apkFile.getOriginalFilename();
		//获取文件保存的绝对位置
		String pathName=absolutePath+filePath;
		//处理文件上传的路径
		File directory=new File(pathName);
		if(!directory.exists()){
			directory.mkdirs();
		}
		//获取需要保存的文件名
		String saveFileName=Tools.fileRename(fileName);
		pd.put("apkName",saveFileName);
		pathName+=saveFileName;
		//复制文件
		apkFile.transferTo(new File(pathName));
		//增加apk版本信息
		boolean status=apkService.addApkHistory(pd);
		return status;
	}
	/**
	 * 删除角色操作
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/deleteApk")
	public Object deleteApk() throws Exception{
		PageData pd=getPageData();
		return apkService.deleteApk(pd);
	}
}