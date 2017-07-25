package cn.ogsu.api.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.ogsu.api.service.ISingerService;
import cn.ogsu.api.util.Const;
import cn.ogsu.api.util.PageData;
import cn.ogsu.api.util.ReturnData;
import cn.ogsu.api.util.Tools;
import net.sf.json.JSONObject;

/**
 * 歌星相关操作的controller
 * @author albert
 * @time 2016年9月13日
 */
@Controller
@RequestMapping("/singer")
public class SingerController extends ControllerBase{

	@Resource
	private ISingerService singerService;
	private static Logger log=Logger.getLogger(SingerController.class);
	/**
	 *歌星类型的数据同步 由移动端->web端
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/asyncSingerType", method=RequestMethod.POST)
	public Object asyncSingerType(){
		//准备返回数据
		ReturnData<List<PageData>> data=null;
		try {
			//访问数据库操作
			List<PageData> songList=singerService.obtainAsyncSingerType();
			
			data=new ReturnData<>(Const.SUCCESS_FLAG,Const.SUCCESS_MSG);
			//封装数据
			data.setData(songList);
		} catch (Exception e) {
			log.error("同步歌星类型数据异常:"+e.getLocalizedMessage());
			//封装异常返回数据
			data=new ReturnData<>(Const.FAILED_FLAG,e.getMessage());
			List<PageData> pdList=new ArrayList<>();
			data.setData(pdList);
		}
		String result=JSONObject.fromObject(data).toString();
		return Tools.encode(result);
	}
	
	/**
	 * 歌星的数据同步  由移动端->web端
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/asyncSinger", method=RequestMethod.POST)
	public Object asyncSinger(){
		//准备返回数据
		ReturnData<List<PageData>> data=null;
		try {
			//获取请求数据
			PageData pd=getRequestMap();
			//访问数据库操作
			List<PageData> songList=singerService.obtainAsyncSinger(pd);
			
			data=new ReturnData<>(Const.SUCCESS_FLAG,Const.SUCCESS_MSG);
			//封装数据
			data.setData(songList);
		} catch (Exception e) {
			log.error("同步歌星数据异常:"+e.getLocalizedMessage());
			//封装异常返回数据
			data=new ReturnData<>(Const.FAILED_FLAG,e.getMessage());
			List<PageData> pdList=new ArrayList<>();
			data.setData(pdList);
		}
		String result=JSONObject.fromObject(data).toString();
		return Tools.encode(result);
	}
	
	/**
	 * 热门歌星的数据同步
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/hotSinger", method=RequestMethod.POST)
	public Object obtainHotSingers(){
		//准备返回数据
		ReturnData<List<PageData>> data=null;
		try {
			//访问数据库操作
			List<PageData> songList=singerService.obtainHotSingers();
			data=new ReturnData<>(Const.SUCCESS_FLAG,Const.SUCCESS_MSG);
			//封装数据
			data.setData(songList);
		} catch (Exception e) {
			log.error("同步热门歌手数据异常:"+e.getLocalizedMessage());
			//封装异常返回数据
			data=new ReturnData<>(Const.FAILED_FLAG,e.getMessage());
			List<PageData> pdList=new ArrayList<>();
			data.setData(pdList);
		}
		String result=JSONObject.fromObject(data).toString();
		return Tools.encode(result);
	}
	
	
	
	/**
	 * 下载歌星头像
	 * @param response
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ResponseBody
	@RequestMapping("/downloadHeader.do")
	public ResponseEntity<byte[]> apkDownload(HttpServletRequest request,HttpServletResponse response){
		String filePath=request.getServletContext().getRealPath("/")+"../vodfile/";
		String headPath=request.getParameter("headPath");
		filePath+=headPath;
		InputStream inputStream = null;  
	    ServletOutputStream out = null;
	    try {  
	        inputStream=new FileInputStream(filePath);  
	        out = response.getOutputStream();    
	        byte[] buffer = new byte[1024];  
	        int length = 0;    
	        while ((length = inputStream.read(buffer, 0, buffer.length)) != -1) {    
	            out.write(buffer, 0, length);  
	        }  
	    } catch (Exception e) {  
	    	log.error("下载头像异常:"+e.getLocalizedMessage());
	    }finally{  
	        try {  
	             if(null != out) out.flush();  
	             if(null != out) out.close();  
	             if(null != inputStream) inputStream.close();   
	        } catch (IOException e) {  
	        }  
	    }  
	    return new ResponseEntity(null,HttpStatus.OK);  
	}
	
	
	/**
	 * 热门歌星的数据同步
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/initSingerCount")
	public Object initSingerCount(){
		//准备返回数据
		ReturnData<List<PageData>> data=null;
		try {
			//访问数据库操作
			singerService.initSingerCount();
			data=new ReturnData<>(Const.SUCCESS_FLAG,Const.SUCCESS_MSG);
			//封装数据
		} catch (Exception e) {
			log.error("同步热门歌手数据异常:"+e.getLocalizedMessage());
			//封装异常返回数据
			data=new ReturnData<>(Const.FAILED_FLAG,e.getMessage());
			List<PageData> pdList=new ArrayList<>();
			data.setData(pdList);
		}
		String result=JSONObject.fromObject(data).toString();
		return Tools.encode(result);
	}
	
}
