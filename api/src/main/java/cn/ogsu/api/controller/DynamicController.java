package cn.ogsu.api.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ogsu.api.requestBean.RequestUserDynamic;
import cn.ogsu.api.responseBean.ResponseDynamic;
import cn.ogsu.api.service.IDynamicService;
import cn.ogsu.api.util.Const;
import cn.ogsu.api.util.GsonUtil;
import cn.ogsu.api.util.ReturnData;
import cn.ogsu.api.util.Tools;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/dynamic")
public class DynamicController extends ControllerBase {

	@Resource
	private IDynamicService dynamicService;
	
	
	/**
	 * 获取动态信息
	 */
	@ResponseBody
	@RequestMapping(value="/obtainApkInfo",method=RequestMethod.POST)
	public Object obtainApkInfo(){
		String result = "";
		ReturnData<String> data = null;
		try {
			RequestUserDynamic query = GsonUtil.getInstance().fromJson(getJson(), RequestUserDynamic.class);
			ResponseDynamic responseImage = dynamicService.queryDynamic(query);
			result = Tools.jsonResovle(responseImage);
		} catch (Exception e) {
			e.printStackTrace();
			data = new ReturnData<>(Const.FAILED_FLAG, e.getMessage());
			data.setData(Const.FAILED_MSG);
			result = JSONObject.fromObject(data).toString();
		}
		return Tools.encode(result);
	}
	
}
