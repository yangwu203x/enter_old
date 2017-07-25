package cn.ogsu.api.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.ogsu.api.service.IBehaviorStatisticsService;
import net.sf.json.JSONObject;
import cn.ogsu.api.util.*;

/**
 * @author albert
 *用户行为统计的controller
 * @time 2016年9月5日
 */
@Controller
@RequestMapping("/behaviorStatistics")
public class BehaviorStatisticsController extends ControllerBase{
	@Resource
	private IBehaviorStatisticsService behaviorStatisticsService;
	
	/**
	 * 关注歌星数据同步统计 由移动端->web端
	 */
	@ResponseBody
	@RequestMapping(value="/singerAttention",method=RequestMethod.POST)
	public Object singerAttentionStatistics(){
		//封装数据
		ReturnData<String> data=null;
		try {
			//获取请求数据
			List<PageData> requestData=getRequestListMap();
			//执行数据库操作
			boolean status=behaviorStatisticsService.addSingerAttention(requestData);
			if(status){
				data=new ReturnData<>(Const.SUCCESS_FLAG,Const.SUCCESS_MSG);
			}else{
				data=new ReturnData<>(Const.FAILED_FLAG,"系统内部异常!");
			}
		} catch (Exception e) {
			data=new ReturnData<>(Const.FAILED_FLAG,e.getMessage());
		}
		data.setData("");
		String result=JSONObject.fromObject(data).toString();
		return Tools.encode(result);
	}
	
	/**
	 * 同步关注歌星的统计数据 由web->移动端
	 */
	@ResponseBody
	@RequestMapping(value="/asyncAttentionSinger",method=RequestMethod.POST)
	public Object asyncAttentionSinger(){
		//准备封装数据
		ReturnData<List<PageData>> data=null;
		try {
			PageData pd=getRequestListMap().get(0);
			//执行获取数据操作
			List<PageData> songCollents=behaviorStatisticsService.singerAttentionList(pd);
			//封装数据
			data=new ReturnData<>(Const.SUCCESS_FLAG, Const.SUCCESS_MSG);
			data.setData(songCollents);
		} catch (Exception e) {
			//封装数据
			data=new ReturnData<>(Const.FAILED_FLAG,e.getMessage());
			List<PageData> d=new ArrayList<>();
			data.setData(d);
		}
		String result=JSONObject.fromObject(data).toString();
		return Tools.encode(result);
	}
	
	
	/**
	 * 返回热门搜索关键字
	 */
	@ResponseBody
	@RequestMapping(value="/asyncHotKey")
	public Object asyncHotKey(){
		//准备封装数据
		ReturnData<List<PageData>> data=null;
		try {
//			PageData pd=getRequestListMap().get(0);
			//执行获取数据操作
			List<PageData> songCollents=behaviorStatisticsService.findHotKey(null);
//			System.err.println("eeeeeeeeeeeeeeeeeeeeeeeee");
//			for(int i = 0;i < songCollents.size();i++){
//				System.out.println("---------------------");
//				System.out.println(i + ":" + songCollents.get(i));
//			}
			//封装数据
			data=new ReturnData<>(Const.SUCCESS_FLAG, Const.SUCCESS_MSG);
			data.setData(songCollents);
		} catch (Exception e) {
			//封装数据
			e.printStackTrace();
			data=new ReturnData<>(Const.FAILED_FLAG,e.getMessage());
			List<PageData> d=new ArrayList<>();
			data.setData(d);
		}
		String result=JSONObject.fromObject(data).toString();
		return Tools.encode(result);
	}
	
	/**
	 *根据用户mac地址和序列号获取用户编号
	 */
	@ResponseBody
	@RequestMapping(value="/obtainUserId",method=RequestMethod.POST)
	public Object obtainUserId(){
		//封装数据
		ReturnData<String> data=null;
		try {
			PageData pd=getRequestMap();
			String userId=behaviorStatisticsService.findUserIdByFlag(pd);
			data=new ReturnData<>(Const.SUCCESS_FLAG,Const.SUCCESS_MSG);
			if(userId!=null){
				data.setData(userId);
			}else data.setData("");
		} catch (Exception e) {
			data=new ReturnData<>(Const.FAILED_FLAG,e.getMessage());
			data.setData("");
		}
		String result=JSONObject.fromObject(data).toString();
		return Tools.encode(result);
	}
	
	
	/**
	 * 根据用户同步歌曲的下载，点播，收藏的数据到客户端
	 */
	@ResponseBody
	@RequestMapping(value="/syncDataFromServer",method=RequestMethod.POST)
	public Object syncDataFromServer(){
		//封装数据
		ReturnData<List<PageData>> data=null;
		try {
			//获取请求数据
			PageData pd=getRequestMap();
			System.err.println("request:" + pd.toString());
			//执行数据库操作
			List<PageData> statistics=behaviorStatisticsService.syncDataFromServer(pd);
			//封装数据
			data=new ReturnData<>(Const.SUCCESS_FLAG, Const.SUCCESS_MSG);
			data.setData(statistics);
		} catch (Exception e) {
			//封装数据
			data=new ReturnData<>(Const.FAILED_FLAG,e.getMessage());
			List<PageData> d=new ArrayList<>();
			data.setData(d);
			e.printStackTrace();
		}
		String result=JSONObject.fromObject(data).toString();
		return Tools.encode(result);
	}
	
	/**
	 * 同步歌曲的下载，点击至服务端
	 */
	@ResponseBody
	@RequestMapping(value="/syncDataFromClient",method=RequestMethod.POST)
	public Object syncDataFromClient(){
		//封装数据
		ReturnData<String> data=null;
		try {
			//获取请求数据
			List<PageData> requestData=getRequestListMap();
			//执行数据库操作
			boolean status=behaviorStatisticsService.syncDataFromClient(requestData);
			if(status){
				data=new ReturnData<>(Const.SUCCESS_FLAG,Const.SUCCESS_MSG);
			}else{
				data=new ReturnData<>(Const.FAILED_FLAG,"系统内部异常!");
			}
		} catch (Exception e) {
			data=new ReturnData<>(Const.FAILED_FLAG,e.getMessage());
		}
		data.setData("");
		String result=JSONObject.fromObject(data).toString();
		return Tools.encode(result);
	}
	
	/**
	 * 修改收藏
	 */
	@ResponseBody
	@RequestMapping(value="/updateCollect",method=RequestMethod.POST)
	public Object updateCollect(){
		//封装数据
		ReturnData<String> data=null;
		try {
			//获取请求数据
			List<PageData> requestData=getRequestListMap();
			System.err.println("request:" + requestData.toString());
			//执行数据库操作
			boolean status=behaviorStatisticsService.updateCollect(requestData);
			if(status){
				data=new ReturnData<>(Const.SUCCESS_FLAG,Const.SUCCESS_MSG);
			}else{
				data=new ReturnData<>(Const.FAILED_FLAG,"系统内部异常!");
			}
		} catch (Exception e) {
			data=new ReturnData<>(Const.FAILED_FLAG,e.getMessage());
		}
		data.setData("");
		System.err.println("data:" + data.toString());
		String result=JSONObject.fromObject(data).toString();
		return Tools.encode(result);
	}
	
	/**
	 * 修改App收藏
	 */
	@ResponseBody
	@RequestMapping(value="/updateAppCollect",method=RequestMethod.POST)
	public Object updateAppCollect(){
		//封装数据
		ReturnData<String> data=null;
		try {
			//获取请求数据
			List<PageData> requestData=getRequestListMap();
			//执行数据库操作
			boolean status=behaviorStatisticsService.updateAppCollect(requestData);
			if(status){
				data=new ReturnData<>(Const.SUCCESS_FLAG,Const.SUCCESS_MSG);
			}else{
				data=new ReturnData<>(Const.FAILED_FLAG,"系统内部异常!");
			}
		} catch (Exception e) {
			data=new ReturnData<>(Const.FAILED_FLAG,e.getMessage());
		}
		data.setData("");
		System.err.println("data.toString():" + data.toString());
		String result=JSONObject.fromObject(data).toString();
		return Tools.encode(result);
	}
	
}
