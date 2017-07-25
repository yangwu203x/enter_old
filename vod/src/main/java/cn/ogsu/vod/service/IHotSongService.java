package cn.ogsu.vod.service;

import java.util.List;

import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;
/**
 * 热门歌曲
 * @author albert
 */


public interface IHotSongService {

	/**
	 * 获取热门歌曲
	 * @param page
	 * @return
	 * @throws Exception
	 */
	List<PageData> hotSongs(Page page) throws Exception;
	
	/**
	 * 获取单个热门歌曲
	 * @param song
	 * @return
	 * @throws Exception
	 */
	PageData obtainHotSongById(PageData song) throws Exception;
	
	/**
	 * 增加热门歌曲
	 * @param song
	 * @return
	 * @throws Exception
	 */
	boolean addHotSong(PageData song) throws Exception;
	
	/**
	 * 修改热门歌曲
	 * @param song
	 * @return
	 * @throws Exception
	 */
	boolean updateHotSong(PageData song)throws Exception;
	
	/**
	 * 删除热门歌曲
	 */
	boolean delHotSong(PageData song) throws Exception;
	
}
