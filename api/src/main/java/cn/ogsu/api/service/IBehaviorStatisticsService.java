package cn.ogsu.api.service;

import java.util.List;
import cn.ogsu.api.util.PageData;

/**
 * 用户行为统计的service层接口
 * @author albert
 *
 * @time 2016年9月5日
 */
public interface IBehaviorStatisticsService {
	
	/**
	 * 根据mac地址和序列号获取用户编号
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	String findUserIdByFlag(PageData pd) throws Exception;
	
	/**
	 * 同步客户端的歌曲下载和点播到服务端
	 * @param statistics
	 * @return
	 * @throws Exception
	 */
	boolean syncDataFromClient(List<PageData> statistics) throws Exception;
	
	/**
	 * 根据用户编号同步歌曲的下载，点播，收藏到客户端
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	List<PageData> syncDataFromServer(PageData pd)throws Exception;
	
	/**
	 * 修改收藏
	 * @param statistics
	 * @return
	 * @throws Exception
	 */
	boolean updateCollect(List<PageData> statistics)throws Exception;
	
	/**
	 * 修改App收藏
	 * @param statistics
	 * @return
	 * @throws Exception
	 */
	boolean updateAppCollect(List<PageData> statistics)throws Exception;
	
	/**
	 * 歌手关注统计数据同步
	 * @param pdList
	 * @return
	 * @throws Exception
	 */
	boolean addSingerAttention(List<PageData> pdList) throws Exception;
	
	/**
	 * 获取所有的歌星关注的数据
	 * @return
	 * @throws Exception
	 */
	List<PageData> singerAttentionList(PageData pd) throws Exception;
	
	/**
	 * 获取热门搜索关键字
	 * @return
	 * @throws Exception
	 */
	List<PageData> findHotKey(PageData pd) throws Exception;
}
