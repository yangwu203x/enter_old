package cn.ogsu.api.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ogsu.api.service.IGoodsService;
import cn.ogsu.api.util.Const;
import cn.ogsu.api.util.PageData;
import cn.ogsu.api.util.ReturnData;
import cn.ogsu.api.util.Tools;
import net.sf.json.JSONObject;

/**
 * 商品信息的controller
 * @author 孙要
 * 2016-12-10
 */
@Controller
@RequestMapping("/goods")
public class GoodsController extends ControllerBase{
	
	@Resource
	private IGoodsService goodsService;
	
	/**
	 * 获取所有的商品信息
	 */
	@ResponseBody
	@RequestMapping(value="/allGoods",method=RequestMethod.POST)
	public Object obtainAllGoods(){
		//准备封装数据
		ReturnData<List<PageData>> data=null;
		try {
			//执行获取数据操作
			List<PageData> allGoods=goodsService.obtainAllGoods();
			//封装数据
			data=new ReturnData<>(Const.SUCCESS_FLAG, Const.SUCCESS_MSG);
			data.setData(allGoods);
		} catch (Exception e) {
			//封装数据
			data=new ReturnData<>(Const.FAILED_FLAG,e.getMessage());
			List<PageData> d=new ArrayList<>();
			data.setData(d);
		}
		String result=JSONObject.fromObject(data).toString();
		return Tools.encode(result);
	}
	
}
