package cn.ogsu.api.service;

import java.util.List;

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
import cn.ogsu.api.util.PageData;

/**
 * 歌曲的相关处理的service接口
 * 
 * @author albert
 * @time 2016年9月13日
 */
public interface ISongService {

	/**
	 * 获取同步歌曲数据
	 * 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> obtainAsyncSongs(PageData pd) throws Exception;

	/**
	 * 获取热门歌曲
	 * 
	 * @return
	 * @throws Exception
	 */
	List<PageData> obtainHotSongs() throws Exception;

	/**
	 * 获取新歌
	 * 
	 * @return
	 * @throws Exception
	 */
	List<PageData> obtainNewSongs() throws Exception;

	/**
	 * 获取同步歌曲类别数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<PageData> obtainAsyncSongType() throws Exception;

	/**
	 * 同步歌曲主题
	 * 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	List<PageData> asyncSongTheme() throws Exception;

	/**
	 * 获取歌曲语种的同步
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<PageData> obtainAsyncSongLang() throws Exception;

	/**
	 * 获取歌曲下载权限
	 * 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public int obtainSongPrivilege(PageData pd) throws Exception;

	/**
	 * 查询K歌首页顶部图片
	 */
	public ResponseRecommendImage queryRecommendImage(RequestQueryRecommendImage pd) throws Exception;
	
	/**
	 * 查询用户收藏歌曲
	 */
	public ResponseSongs queryUserCollect(RequestUserCollect pd) throws Exception;
	
	/**
	 * 查询最热（最近）歌曲 推荐
	 */
	public ResponseSongs queryHotSongsForRecommend(RequestSongsHot pd) throws Exception;
	
	/**
	 * 查询最热（最近）歌曲 新歌
	 */
	public ResponseSongs queryHotSongsForNewSong(RequestSongsHot pd) throws Exception;
	
	/**
	 * 查询最热（最近）歌曲 经典老歌
	 */
	public ResponseSongs queryHotSongsForClassicSong(RequestSongsHot pd) throws Exception;
	
	/**
	 * 查询最热（最近）歌曲 热门金曲
	 */
	public ResponseSongs queryHotSongsForHot(RequestSongsHot pd) throws Exception;
	
	/**
	 * 查询最热（最近）歌曲 戏曲
	 */
	public ResponseSongs queryHotSongsForOpera(RequestSongsHot pd) throws Exception;
	
	/**
	 * 查询最热（最近）歌曲 戏曲
	 */
	public ResponseSongs queryHotSongsForChild(RequestSongsHot pd) throws Exception;
	
	/**
	 * 查询最热（最近）歌曲 K-POP  韩国
	 */
	public ResponseSongs queryHotSongsForK_POP(RequestSongsHot pd) throws Exception;
	
	/**
	 * 查询最热（最近）歌曲 日本
	 */
	public ResponseSongs queryHotSongsForJapan(RequestSongsHot pd) throws Exception;
	
	/**
	 * 查询最热（最近）歌曲 欧美
	 */
	public ResponseSongs queryHotSongsForAmerica(RequestSongsHot pd) throws Exception;
	
	/**
	 * 查询最热（最近）歌曲 菲律宾
	 */
	public ResponseSongs queryHotSongsForFilipino(RequestSongsHot pd) throws Exception;
	
	/**
	 * 查询最热（最近）歌曲 越南
	 */
	public ResponseSongs queryHotSongsForVietnam(RequestSongsHot pd) throws Exception;
	
	/**
	 * 查询最热（最近）歌曲 A_Z
	 */
	public ResponseSongs querySongsForA_Z(RequestSongsA_Z pd) throws Exception;
	
	/**
	 * 搜索歌曲、歌星
	 */
	public ResponseSearchSongsAndSinger searchSongsAndSinger(RequestSearchSongsAndSinger pd) throws Exception;
	
	/**
	 * 搜索歌星
	 */
	public ResponseSinger searchSinger(RequestSinger pd) throws Exception;
	
	/**
	 * 搜索歌曲
	 */
	public ResponseSongs searchSongs(RequestSongs pd) throws Exception;
	
	/**
	 * 搜索热门搜索
	 */
	public ResponseHotSearch searchHotSearch(RequestHotSearch pd) throws Exception;
	
	/**
	 * 返回歌曲类别
	 */
	public ResponseSongType searchSongType() throws Exception;
	
	/**
	 * 返回某一类别歌曲
	 */
	public ResponseSongs searchSongsForType(RequestSongsForType pd) throws Exception;
	
	/**
	 * 搜集用户反馈
	 */
	public String userFeedBackForSongs(RequestSongsFeedBack pd) throws Exception;
	
	/**
	 * 搜索歌星歌曲
	 */
	public ResponseSongs searchSingerSongs(RequestSingerSongs pd) throws Exception;
}
