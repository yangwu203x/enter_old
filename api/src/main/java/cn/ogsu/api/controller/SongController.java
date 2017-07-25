package cn.ogsu.api.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import cn.ogsu.api.service.IIpService;
import cn.ogsu.api.util.*;
import org.lionsoul.ip2region.DataBlock;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ogsu.api.requestBean.RequestHotSearch;
import cn.ogsu.api.requestBean.RequestQueryRecommendImage;
import cn.ogsu.api.requestBean.RequestSearchSongsAndSinger;
import cn.ogsu.api.requestBean.RequestSinger;
import cn.ogsu.api.requestBean.RequestSingerSongs;
import cn.ogsu.api.requestBean.RequestSongs;
import cn.ogsu.api.requestBean.RequestSongsHot;
import cn.ogsu.api.requestBean.RequestSongsA_Z;
import cn.ogsu.api.requestBean.RequestSongsFeedBack;
import cn.ogsu.api.requestBean.RequestSongsForType;
import cn.ogsu.api.requestBean.RequestUserCollect;
import cn.ogsu.api.responseBean.ResponseHotSearch;
import cn.ogsu.api.responseBean.ResponseRecommendImage;
import cn.ogsu.api.responseBean.ResponseSearchSongsAndSinger;
import cn.ogsu.api.responseBean.ResponseSinger;
import cn.ogsu.api.responseBean.ResponseSongType;
import cn.ogsu.api.responseBean.ResponseSongs;
import cn.ogsu.api.service.ISongService;
import net.sf.json.JSONObject;

/**
 * 歌曲相关操作的controller
 * 
 * @author albert
 * @time 2016年9月12日
 */
@Controller
@RequestMapping("/song")
public class SongController extends ControllerBase {

	@Resource
	private ISongService songService;
	@Resource
	private IIpService iIpService;

	/**
	 * 同步歌曲数据至移动端 由web->移动端
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/asyncSong", method = RequestMethod.POST)
	public Object asyncSongData() {
		// 准备返回数据
		ReturnData<List<PageData>> data = null;
		try {
			// 获取请求数据
			PageData pd = getRequestMap();
			// 访问数据库操作
			List<PageData> songList = songService.obtainAsyncSongs(pd);
			data = new ReturnData<>(Const.SUCCESS_FLAG, Const.SUCCESS_MSG);
			// 封装数据
			data.setData(songList);
		} catch (Exception e) {
			// 封装异常返回数据
			e.printStackTrace();
			data = new ReturnData<>(Const.FAILED_FLAG, e.getMessage());
			List<PageData> pdList = new ArrayList<>();
			data.setData(pdList);
		}
		String result = JSONObject.fromObject(data).toString();
		return Tools.encode(result);
	}

	/**
	 * 获取所有的热门歌曲信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/hotSongs", method = RequestMethod.POST)
	public Object obtainHotSongs() {
		// 准备返回数据
		ReturnData<List<PageData>> data = null;
		try {
			// 访问数据库操作
			List<PageData> songSingerList = songService.obtainHotSongs();

			data = new ReturnData<>(Const.SUCCESS_FLAG, Const.SUCCESS_MSG);
			// 封装数据
			data.setData(songSingerList);
		} catch (Exception e) {
			// 封装异常返回数据
			data = new ReturnData<>(Const.FAILED_FLAG, e.getMessage());
			List<PageData> pdList = new ArrayList<>();
			data.setData(pdList);
		}
		String result = JSONObject.fromObject(data).toString();
		return Tools.encode(result);
	}

	/**
	 * 获取所有的热门歌曲信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/newSongs", method = RequestMethod.POST)
	public Object obtainNewSongs() {
		// 准备返回数据
		ReturnData<List<PageData>> data = null;
		try {
			// 访问数据库操作
			List<PageData> songSingerList = songService.obtainNewSongs();

			data = new ReturnData<>(Const.SUCCESS_FLAG, Const.SUCCESS_MSG);
			// 封装数据
			data.setData(songSingerList);
		} catch (Exception e) {
			// 封装异常返回数据
			data = new ReturnData<>(Const.FAILED_FLAG, e.getMessage());
			List<PageData> pdList = new ArrayList<>();
			data.setData(pdList);
		}
		String result = JSONObject.fromObject(data).toString();
		return Tools.encode(result);
	}

	/**
	 * 歌曲类别数据的同步 由web->移动端
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/asyncSongType", method = RequestMethod.POST)
	public Object asyncSongType() {
		// 准备返回数据
		ReturnData<List<PageData>> data = null;
		try {
			// 访问数据库操作
			List<PageData> songTypeList = songService.obtainAsyncSongType();

			data = new ReturnData<>(Const.SUCCESS_FLAG, Const.SUCCESS_MSG);
			// 封装数据
			data.setData(songTypeList);
		} catch (Exception e) {
			// 封装异常返回数据
			data = new ReturnData<>(Const.FAILED_FLAG, e.getMessage());
			List<PageData> pdList = new ArrayList<>();
			data.setData(pdList);
		}
		String result = JSONObject.fromObject(data).toString();
		return Tools.encode(result);
	}

	/**
	 * 歌曲类别数据的同步 由web->移动端
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/asyncSongTheme", method = RequestMethod.POST)
	public Object asyncSongTheme() {
		// 准备返回数据
		ReturnData<List<PageData>> data = null;
		try {
			// 访问数据库操作
			List<PageData> songThemeList = songService.asyncSongTheme();

			data = new ReturnData<>(Const.SUCCESS_FLAG, Const.SUCCESS_MSG);
			// 封装数据
			data.setData(songThemeList);
		} catch (Exception e) {
			// 封装异常返回数据
			data = new ReturnData<>(Const.FAILED_FLAG, e.getMessage());
			List<PageData> pdList = new ArrayList<>();
			data.setData(pdList);
		}
		String result = JSONObject.fromObject(data).toString();
		return Tools.encode(result);
	}

	/**
	 * 歌曲语种数据的同步 由web->移动端
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/asyncSongLang", method = RequestMethod.POST)
	public Object asyncSongLang() {
		// 准备返回数据
		ReturnData<List<PageData>> data = null;
		try {
			// 访问数据库操作
			List<PageData> songLangList = songService.obtainAsyncSongLang();

			data = new ReturnData<>(Const.SUCCESS_FLAG, Const.SUCCESS_MSG);
			// 封装数据
			data.setData(songLangList);
		} catch (Exception e) {
			// 封装异常返回数据
			data = new ReturnData<>(Const.FAILED_FLAG, e.getMessage());
			List<PageData> pdList = new ArrayList<>();
			data.setData(pdList);
		}
		String result = JSONObject.fromObject(data).toString();
		return Tools.encode(result);
	}

	/**
	 * 获取下载权限
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/obtainDownPri")
	public Object obtainDownPrivilege() {
		ReturnData<Integer> data = null;
		try {
			PageData pd = getRequestMap();
			int count = songService.obtainSongPrivilege(pd);
			if (count > 0) {
				data = new ReturnData<>(Const.SUCCESS_FLAG, Const.SUCCESS_MSG);
			} else {
				data = new ReturnData<>(Const.SUCCESS_FLAG, "你没有下载该歌曲的权限!");
			}
			count = count > 0 ? 1 : 0;
			data.setData(count);
		} catch (Exception e) {
			data = new ReturnData<>(Const.FAILED_FLAG, e.getMessage());
			data.setData(0);
		}
		String result = JSONObject.fromObject(data).toString();
		return Tools.encode(result);
	}

	/**
	 * K歌首页顶部大图请求接口
	 */
	@ResponseBody
	@RequestMapping("/queryRecommendImage")
	public Object queryRecommendImage() {
		ReturnData<String> data = null;
		try {
			RequestQueryRecommendImage query = GsonUtil.getInstance().fromJson(getJson(),
					RequestQueryRecommendImage.class);
			ResponseRecommendImage responseImage = songService.queryRecommendImage(query);
			String jsonStr = GsonUtil.getInstance().toJson(responseImage);
			data = new ReturnData<>(Const.FAILED_FLAG, Const.SUCCESS_MSG);
			data.setData(jsonStr);
			System.err.println("jsonStr:" + jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
			data = new ReturnData<>(Const.FAILED_FLAG, e.getMessage());
			data.setData(Const.FAILED_MSG);
		}
		String result = JSONObject.fromObject(data).toString();
		return Tools.encode(result);
	}

	/**********************************************************************
	 * 歌曲筛选1.0 *
	 **********************************************************************/
	/*
	 * 
	 */
	/**
	 * 请求用户收藏歌曲
	 */
	@ResponseBody
	@RequestMapping("/queryUserCollect")
	public Object queryUserCollect() {
		String result = "";
		ReturnData<String> data = null;
		try {
			RequestUserCollect query = GsonUtil.getInstance().fromJson(getJson(), RequestUserCollect.class);
			ResponseSongs responseImage = songService.queryUserCollect(query);
			result = Tools.jsonResovle(responseImage);
		} catch (Exception e) {
			e.printStackTrace();
			data = new ReturnData<>(Const.FAILED_FLAG, e.getMessage());
			data.setData(Const.FAILED_MSG);
			result = JSONObject.fromObject(data).toString();
		}
		return Tools.encode(result);
	}

	/**
	 * 请求最热（最近）歌曲；最多100首(推荐)
	 */
	@ResponseBody
	@RequestMapping("/queryHotSongsForRecommend")
	public Object queryHotSongsForRecommend() {
		String result = "";
		ReturnData<String> data = null;
		try {
			RequestSongsHot query = GsonUtil.getInstance().fromJson(getJson(), RequestSongsHot.class);
			ResponseSongs responseImage = songService.queryHotSongsForRecommend(query);
			result = Tools.jsonResovle(responseImage);
		} catch (Exception e) {
			e.printStackTrace();
			data = new ReturnData<>(Const.FAILED_FLAG, e.getMessage());
			data.setData(Const.FAILED_MSG);
			result = JSONObject.fromObject(data).toString();
		}
		return Tools.encode(result);
	}

	/**
	 * 请求最热（最近）歌曲；最多100首（新歌）
	 */
	@ResponseBody
	@RequestMapping("/queryHotSongsForNewSong")
	public Object queryHotSongsForNewSong() {
		String result = "";
		ReturnData<String> data = null;
		try {
			RequestSongsHot query = GsonUtil.getInstance().fromJson(getJson(), RequestSongsHot.class);
			ResponseSongs responseImage = songService.queryHotSongsForNewSong(query);
			result = Tools.jsonResovle(responseImage);
		} catch (Exception e) {
			e.printStackTrace();
			data = new ReturnData<>(Const.FAILED_FLAG, e.getMessage());
			data.setData(Const.FAILED_MSG);
			result = JSONObject.fromObject(data).toString();
		}
		return Tools.encode(result);
	}

	/**
	 * 请求最热（最近）歌曲；最多100首（经典老歌）
	 */
	@ResponseBody
	@RequestMapping("/queryHotSongsForClassicSong")
	public Object queryHotSongsForClassicSong() {
		String result = "";
		ReturnData<String> data = null;
		try {
			RequestSongsHot query = GsonUtil.getInstance().fromJson(getJson(), RequestSongsHot.class);
			ResponseSongs responseImage = songService.queryHotSongsForClassicSong(query);
			result = Tools.jsonResovle(responseImage);
		} catch (Exception e) {
			e.printStackTrace();
			data = new ReturnData<>(Const.FAILED_FLAG, e.getMessage());
			data.setData(Const.FAILED_MSG);
			result = JSONObject.fromObject(data).toString();
		}
		return Tools.encode(result);
	}

	/**
	 * 请求最热（最近）歌曲；最多100首（热门金曲）
	 */
	@ResponseBody
	@RequestMapping("/queryHotSongsForHot")
	public Object queryHotSongsForHot() {
		String result = "";
		ReturnData<String> data = null;
		try {
			RequestSongsHot query = GsonUtil.getInstance().fromJson(getJson(), RequestSongsHot.class);
			ResponseSongs responseImage = songService.queryHotSongsForHot(query);
			result = Tools.jsonResovle(responseImage);
		} catch (Exception e) {
			e.printStackTrace();
			data = new ReturnData<>(Const.FAILED_FLAG, e.getMessage());
			data.setData(Const.FAILED_MSG);
			result = JSONObject.fromObject(data).toString();
		}
		return Tools.encode(result);
	}

	/**
	 * 请求最热（最近）歌曲；最多100首（戏曲）
	 */
	@ResponseBody
	@RequestMapping("/queryHotSongsForOpera")
	public Object queryHotSongsForOpera() {
		String result = "";
		ReturnData<String> data = null;
		try {
			RequestSongsHot query = GsonUtil.getInstance().fromJson(getJson(), RequestSongsHot.class);
			ResponseSongs responseImage = songService.queryHotSongsForOpera(query);
			result = Tools.jsonResovle(responseImage);
		} catch (Exception e) {
			e.printStackTrace();
			data = new ReturnData<>(Const.FAILED_FLAG, e.getMessage());
			data.setData(Const.FAILED_MSG);
			result = JSONObject.fromObject(data).toString();
		}
		return Tools.encode(result);
	}

	/**
	 * 请求最热（最近）歌曲；最多100首（儿歌）
	 */
	@ResponseBody
	@RequestMapping("/queryHotSongsForChild")
	public Object queryHotSongsForChild() {
		String result = "";
		ReturnData<String> data = null;
		try {
			RequestSongsHot query = GsonUtil.getInstance().fromJson(getJson(), RequestSongsHot.class);
			ResponseSongs responseImage = songService.queryHotSongsForChild(query);
			result = Tools.jsonResovle(responseImage);
		} catch (Exception e) {
			e.printStackTrace();
			data = new ReturnData<>(Const.FAILED_FLAG, e.getMessage());
			data.setData(Const.FAILED_MSG);
			result = JSONObject.fromObject(data).toString();
		}
		return Tools.encode(result);
	}

	/**
	 * 请求最热（最近）歌曲；最多100首（K-POP 韩国）
	 */
	@ResponseBody
	@RequestMapping("/queryHotSongsForK_POP")
	public Object queryHotSongsForK_POP() {
		String result = "";
		ReturnData<String> data = null;
		try {
			RequestSongsHot query = GsonUtil.getInstance().fromJson(getJson(), RequestSongsHot.class);
			ResponseSongs responseImage = songService.queryHotSongsForK_POP(query);
			result = Tools.jsonResovle(responseImage);
		} catch (Exception e) {
			e.printStackTrace();
			data = new ReturnData<>(Const.FAILED_FLAG, e.getMessage());
			data.setData(Const.FAILED_MSG);
			result = JSONObject.fromObject(data).toString();
		}
		return Tools.encode(result);
	}

	/**
	 * 请求最热（最近）歌曲；最多100首（日本）
	 */
	@ResponseBody
	@RequestMapping("/queryHotSongsForJapan")
	public Object queryHotSongsForJapan() {
		String result = "";
		ReturnData<String> data = null;
		try {
			RequestSongsHot query = GsonUtil.getInstance().fromJson(getJson(), RequestSongsHot.class);
			ResponseSongs responseImage = songService.queryHotSongsForJapan(query);
			result = Tools.jsonResovle(responseImage);
		} catch (Exception e) {
			e.printStackTrace();
			data = new ReturnData<>(Const.FAILED_FLAG, e.getMessage());
			data.setData(Const.FAILED_MSG);
			result = JSONObject.fromObject(data).toString();
		}
		return Tools.encode(result);
	}

	/**
	 * 请求最热（最近）歌曲；最多100首（欧美）
	 */
	@ResponseBody
	@RequestMapping("/queryHotSongsForAmerica")
	public Object queryHotSongsForAmerica() {
		String result = "";
		ReturnData<String> data = null;
		try {
			RequestSongsHot query = GsonUtil.getInstance().fromJson(getJson(), RequestSongsHot.class);
			ResponseSongs responseImage = songService.queryHotSongsForAmerica(query);
			result = Tools.jsonResovle(responseImage);
		} catch (Exception e) {
			e.printStackTrace();
			data = new ReturnData<>(Const.FAILED_FLAG, e.getMessage());
			data.setData(Const.FAILED_MSG);
			result = JSONObject.fromObject(data).toString();
		}
		return Tools.encode(result);
	}

	/**
	 * 请求最热（最近）歌曲；最多100首（菲律宾）
	 */
	@ResponseBody
	@RequestMapping("/queryHotSongsForFilipino")
	public Object queryHotSongsForFilipino() {
		String result = "";
		ReturnData<String> data = null;
		try {
			RequestSongsHot query = GsonUtil.getInstance().fromJson(getJson(), RequestSongsHot.class);
			ResponseSongs responseImage = songService.queryHotSongsForFilipino(query);
			result = Tools.jsonResovle(responseImage);
		} catch (Exception e) {
			e.printStackTrace();
			data = new ReturnData<>(Const.FAILED_FLAG, e.getMessage());
			data.setData(Const.FAILED_MSG);
			result = JSONObject.fromObject(data).toString();
		}
		return Tools.encode(result);
	}

	/**
	 * 请求最热（最近）歌曲；最多100首（越南）
	 */
	@ResponseBody
	@RequestMapping("/queryHotSongsForVietnam")
	public Object queryHotSongsForVietnam() {
		String result = "";
		ReturnData<String> data = null;
		try {
			RequestSongsHot query = GsonUtil.getInstance().fromJson(getJson(), RequestSongsHot.class);
			ResponseSongs responseImage = songService.queryHotSongsForVietnam(query);
			result = Tools.jsonResovle(responseImage);
		} catch (Exception e) {
			e.printStackTrace();
			data = new ReturnData<>(Const.FAILED_FLAG, e.getMessage());
			data.setData(Const.FAILED_MSG);
			result = JSONObject.fromObject(data).toString();
		}
		return Tools.encode(result);
	}

	/**
	 * 请求歌曲A-Z
	 */
	@ResponseBody
	@RequestMapping("/querySongsForA_Z")
	public Object querySongsForA_Z() {
		String result = "";
		ReturnData<String> data = null;
		try {
			RequestSongsA_Z query = GsonUtil.getInstance().fromJson(getJson(), RequestSongsA_Z.class);
			ResponseSongs responseImage = songService.querySongsForA_Z(query);
			result = Tools.jsonResovle(responseImage);
		} catch (Exception e) {
			e.printStackTrace();
			data = new ReturnData<>(Const.FAILED_FLAG, e.getMessage());
			data.setData(Const.FAILED_MSG);
			result = JSONObject.fromObject(data).toString();
		}
		return Tools.encode(result);
	}

	/*
	 * 
	 */
	/************************************************************************************
	 * 歌曲筛选1.0 *
	 ************************************************************************************/

	/**
	 * 歌曲、歌星搜索
	 */
	@ResponseBody
	@RequestMapping("/searchSongsAndSinger")
	public Object searchSongsAndSinger() {
		String result = "";
		ReturnData<String> data = null;
		try {
			RequestSearchSongsAndSinger query = GsonUtil.getInstance().fromJson(getJson(),
					RequestSearchSongsAndSinger.class);
			ResponseSearchSongsAndSinger responseImage = songService.searchSongsAndSinger(query);
			result = Tools.jsonResovle(responseImage);
		} catch (Exception e) {
			e.printStackTrace();
			data = new ReturnData<>(Const.FAILED_FLAG, e.getMessage());
			data.setData(Const.FAILED_MSG);
			result = JSONObject.fromObject(data).toString();
		}
		return Tools.encode(result);
	}

	/**
	 * 歌星搜索--查看全部
	 */
	@ResponseBody
	@RequestMapping("/searchSinger")
	public Object searchSinger() {
		String result = "";
		ReturnData<String> data = null;
		try {
			RequestSinger query = GsonUtil.getInstance().fromJson(getJson(), RequestSinger.class);
			ResponseSinger responseImage = songService.searchSinger(query);
			result = Tools.jsonResovle(responseImage);
		} catch (Exception e) {
			e.printStackTrace();
			data = new ReturnData<>(Const.FAILED_FLAG, e.getMessage());
			data.setData(Const.FAILED_MSG);
			result = JSONObject.fromObject(data).toString();
		}
		return Tools.encode(result);
	}

	/**
	 * 歌曲搜索--以拼音或歌名
	 */
	@ResponseBody
	@RequestMapping("/searchSongs")
	public Object searchSongs() {
		String result = "";
		ReturnData<String> data = null;
		try {
			RequestSongs query = GsonUtil.getInstance().fromJson(getJson(), RequestSongs.class);
			ResponseSongs responseImage = songService.searchSongs(query);
			result = Tools.jsonResovle(responseImage);
		} catch (Exception e) {
			e.printStackTrace();
			data = new ReturnData<>(Const.FAILED_FLAG, e.getMessage());
			data.setData(Const.FAILED_MSG);
			result = JSONObject.fromObject(data).toString();
		}
		return Tools.encode(result);
	}

	/**
	 * 返回热搜字段
	 */
	@ResponseBody
	@RequestMapping("/searchHotSearch")
	public Object searchHotSearch() {
		String result = "";
		ReturnData<String> data = null;
		try {
			RequestHotSearch query = GsonUtil.getInstance().fromJson(getJson(), RequestHotSearch.class);
			ResponseHotSearch responseImage = songService.searchHotSearch(query);
			result = Tools.jsonResovle(responseImage);
		} catch (Exception e) {
			e.printStackTrace();
			data = new ReturnData<>(Const.FAILED_FLAG, e.getMessage());
			data.setData(Const.FAILED_MSG);
			result = JSONObject.fromObject(data).toString();
		}
		return Tools.encode(result);
	}
	
	/**
	 * 返回歌曲类别
	 */
	@ResponseBody
	@RequestMapping("/searchSongType")
	public Object searchSongType() {
		String result = "";
		ReturnData<String> data = null;
		try {
			ResponseSongType responseImage = songService.searchSongType();
			result = Tools.jsonResovle(responseImage);
		} catch (Exception e) {
			e.printStackTrace();
			data = new ReturnData<>(Const.FAILED_FLAG, e.getMessage());
			data.setData(Const.FAILED_MSG);
			result = JSONObject.fromObject(data).toString();
		}
		return Tools.encode(result);
	}
	
	/**
	 * 请求某一类别的歌曲
	 */
	@ResponseBody
	@RequestMapping("/searchSongsForType")
	public Object searchSongsForType() {
		String result = "";
		ReturnData<String> data = null;
		try {
			RequestSongsForType query = GsonUtil.getInstance().fromJson(getJson(), RequestSongsForType.class);
			ResponseSongs responseImage = songService.searchSongsForType(query);
			result = Tools.jsonResovle(responseImage);
		} catch (Exception e) {
			e.printStackTrace();
			data = new ReturnData<>(Const.FAILED_FLAG, e.getMessage());
			data.setData(Const.FAILED_MSG);
			result = JSONObject.fromObject(data).toString();
		}
		return Tools.encode(result);
	}
	
	/**
	 * 歌曲反馈
	 */
	@ResponseBody
	@RequestMapping("/userFeedBackForSongs")
	public Object userFeedBackForSongs() {
		String result = "";
		ReturnData<String> data = null;
		try {
			RequestSongsFeedBack query = GsonUtil.getInstance().fromJson(getJson(), RequestSongsFeedBack.class);
			String responseImage = songService.userFeedBackForSongs(query);
			result = Tools.jsonResovle(responseImage);
		} catch (Exception e) {
			e.printStackTrace();
			data = new ReturnData<>(Const.FAILED_FLAG, e.getMessage());
			data.setData(Const.FAILED_MSG);
			result = JSONObject.fromObject(data).toString();
		}
		return Tools.encode(result);
	}
	
	/**
	 * 歌星歌曲搜索
	 */
	@ResponseBody
	@RequestMapping("/searchSingerSongs")
	public Object searchSingerSongs() {
		String result = "";
		ReturnData<String> data = null;
		try {
			RequestSingerSongs query = GsonUtil.getInstance().fromJson(getJson(), RequestSingerSongs.class);
			ResponseSongs responseImage = songService.searchSingerSongs(query);
			result = Tools.jsonResovle(responseImage);
		} catch (Exception e) {
			e.printStackTrace();
			data = new ReturnData<>(Const.FAILED_FLAG, e.getMessage());
			data.setData(Const.FAILED_MSG);
			result = JSONObject.fromObject(data).toString();
		}
		return Tools.encode(result);
	}

	@ResponseBody
	@RequestMapping("/getFtpAddress")
	public Object getFtpAddress(HttpServletRequest request){
		// 准备返回数据
		ReturnData<PageData> data = null;
		try {
			//判断ip归属
			PageData pd = IPUtils.getAddresses(request);
			// 访问数据库操作
			PageData ipdata = iIpService.getIpByDataBlock(pd);

			data = new ReturnData<>(Const.SUCCESS_FLAG, Const.SUCCESS_MSG);
			// 封装数据
			data.setData(ipdata);
		} catch (Exception e) {
			// 封装异常返回数据
			data = new ReturnData<>(Const.FAILED_FLAG, e.getMessage());
			PageData pd = new PageData();
			data.setData(pd);
		}
		String result = JSONObject.fromObject(data).toString();
		return Tools.encode(result);
	}
}
