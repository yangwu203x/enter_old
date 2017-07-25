package cn.ogsu.vod.service;

import java.util.List;

import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;
/**
 * 热门歌星service接口
 * @author Administrator
 */
public interface IHotSingerService {
	
	/**
	 * 分页获取热门歌手信息
	 * @param page
	 * @return
	 * @throws Exception
	 */
	List<PageData> hotSingers(Page page) throws Exception;
	
	/**
	 * 增加热门歌手信息
	 * @param singer
	 * @return
	 * @throws Exception
	 */
	boolean addHotSinger(PageData singer) throws Exception;
	
	/**
	 * 修改热门歌手信息
	 * @param singer
	 * @return
	 * @throws Exception
	 */
	boolean updateHotSinger(PageData singer) throws Exception;
	
	/**
	 * 删除热门歌手信息
	 * @param singer
	 * @return
	 * @throws Exception
	 */
	boolean delHotSinger(PageData singer) throws Exception;
	
	/**
	 * 获取单个热门歌手信息
	 * @param singer
	 * @return
	 * @throws Exception
	 */
	PageData obtainHotSingerById(PageData singer) throws Exception;

}
