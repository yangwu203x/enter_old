package cn.ogsu.api.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ogsu.api.service.IProductService;
import cn.ogsu.api.util.Const;
import cn.ogsu.api.util.PageData;
import cn.ogsu.api.util.ReturnData;
import cn.ogsu.api.util.Tools;
import net.sf.json.JSONObject;

/**
 * 产品的控制器类
 * @author albert
 * @time 2016年10月8日
 */
@Controller
@RequestMapping("/product")
public class ProductController extends ControllerBase{

	@Resource
	private IProductService productService;
	
	/**
	 * 增加设备第一次使用时间
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addFirstTime")
	public Object addFirstTime(){
		//封装数据
		ReturnData<PageData> data=null;
		try {
			boolean result=productService.addFirstTime(getRequestMap());
			if(result){
				data=new ReturnData<>(Const.SUCCESS_FLAG,Const.SUCCESS_MSG);
			}else{
				data=new ReturnData<>(Const.FAILED_FLAG,null);
			}
			data.setData(new PageData());
		} catch (Exception e) {
			data=new ReturnData<>(Const.FAILED_FLAG,e.getMessage());
			data.setData(new PageData());
		}
		String result=JSONObject.fromObject(data).toString();
		return Tools.encode(result);
	}
	
	/**
	 * 获取设备从使用开始还能下载歌曲的有效天数(45天)
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/expireDay")
	public Object obtainExpireDay(){
		//封装数据
		ReturnData<PageData> data=null;
		try {
			int day=productService.findFirstTime(getRequestMap());
			if(day>0){
				data=new ReturnData<>(Const.SUCCESS_FLAG,Const.SUCCESS_MSG);
			}else{
				data=new ReturnData<>(Const.FAILED_FLAG,null);
			}
			PageData pd=new PageData();
			pd.put("expireDay", day);
			data.setData(pd);
		} catch (Exception e) {
			data=new ReturnData<>(Const.FAILED_FLAG,e.getMessage());
			data.setData(new PageData());
		}
		String result=JSONObject.fromObject(data).toString();
		return Tools.encode(result);
	}
	
	
	
}
