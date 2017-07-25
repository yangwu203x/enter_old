package cn.ogsu.api.service;

import java.util.List;

import cn.ogsu.api.util.PageData;

/**
 * 歌星的相关处理的service接口
 * @author albert
 * @time 2016年9月13日
 */
public interface ISingerService {

	/**
	 * 获取歌星类型的数据同步
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> obtainAsyncSingerType() throws Exception;
	
	
	/**
	 * 获取歌星的同步数据
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> obtainAsyncSinger(PageData pd) throws Exception;
	
	/**
	 * 获取歌星的同步数据
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> obtainHotSingers() throws Exception;
	
	
	public void initSingerCount();
	
	
}
