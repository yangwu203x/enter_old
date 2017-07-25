package cn.ogsu.api.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.ogsu.api.service.IApkService;
import cn.ogsu.api.util.Const;
import cn.ogsu.api.util.PageData;
import cn.ogsu.api.util.ReturnData;
import cn.ogsu.api.util.Tools;
/**
 * apk相关任务的controller
 * @author albert
 * @time 2016年9月13日
 */
@Controller
@RequestMapping("/apk")
public class ApkController {
	
	@Resource
	private IApkService apkService;
	
	private static final Logger logger = Logger.getLogger(ApkController.class);
	/**
	 * 获取最新的apk信息
	 */
	@ResponseBody
	@RequestMapping(value="/obtainApkInfo",method=RequestMethod.POST)
	public Object obtainApkInfo(){
		//封装数据
		ReturnData<PageData> data=null;
		try {
			PageData pd=apkService.findLastApk();
			data=new ReturnData<>(Const.SUCCESS_FLAG,Const.SUCCESS_MSG);
			if(pd!=null){
				pd.remove("apk_name");
				pd.remove("apk_server_path");
				data.setData(pd);
			}else data.setData(new PageData());
		} catch (Exception e) {
			data=new ReturnData<>(Const.FAILED_FLAG,e.getMessage());
			data.setData(new PageData());
		}
		String result= JSONObject.fromObject(data).toString();
		return Tools.encode(result);
	}
	
	

	/**
	 * 下载更新最新的apk文件
	 * @param response
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ResponseBody
	@RequestMapping("/updateApk.do")
	public ResponseEntity<byte[]> apkDownload(HttpServletRequest request,HttpServletResponse response){
		InputStream inputStream = null;  
	    ServletOutputStream out = null;
	    //建立文件对象
		File file=null;
	    try {  
	    	//获取最新的apk数据
			PageData pd=apkService.findLastApk();
			//获取apk存放的相对路径
			String relativePath=pd.getString("apk_server_path");
			//获取文件的绝对路径
			String pathName=request.getServletContext().getRealPath(relativePath);
			file=new File(pathName);  
	        //File file = new File(OdexManage.odexFileBasePath + "\\" + odexName); 
	        int fSize = Integer.parseInt(String.valueOf(file.length()));    
	        response.setCharacterEncoding("utf-8");  
	        response.setContentType("application/x-download");    
	        response.setHeader("Accept-Ranges", "bytes");    
	        response.setHeader("Content-Length", String.valueOf(fSize));    
	        response.setHeader("Content-Disposition", "attachment;fileName=" +pd.getString("apk_name") );  
	        inputStream=new FileInputStream(pathName);  
	        long pos = 0;    
	        if (null != request.getHeader("Range")) {  
	            // 断点续传  
	            response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);    
	            try {    
	                pos = Long.parseLong(request.getHeader("Range").replaceAll("bytes=", "").replaceAll("-", ""));    
	            } catch (NumberFormatException e) {  
	                pos = 0;    
	            }    
	        }    
	        out = response.getOutputStream();    
	        String contentRange = new StringBuffer("bytes ").append(pos+"").append("-").append((fSize - 1)+"").append("/").append(fSize+"").toString();  
	        response.setHeader("Content-Range", contentRange);    
	        inputStream.skip(pos);    
	        byte[] buffer = new byte[1024*10];  
	        int length = 0;    
	        while ((length = inputStream.read(buffer, 0, buffer.length)) != -1) {    
	            out.write(buffer, 0, length);  
	        }  
	    } catch (Exception e) {  
	        logger.error("apk下载异常："+e);  
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
	
	
	
}
