package cn.ogsu.vod.service;

import java.util.List;

import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;
/**
 * 新歌业务的service接口
 * @author albert
 *
 */
public interface INewSongService {

	/**
	 * 分页获取新歌列表
	 * @param page
	 * @return
	 * @throws Exception
	 */
	List<PageData> newSongs(Page page)throws Exception;
	
	/**
	 * 增加新歌曲
	 * @param song
	 * @return
	 * @throws Exception
	 */
	boolean addNewSong(PageData song)throws Exception;
	
	/**
	 * 删除新歌曲
	 * @param song
	 * @return
	 * @throws Exception
	 */
	boolean delNewSong(PageData song)throws Exception;
	
	/**
	 * 修改新歌信息
	 * @param song
	 * @return
	 * @throws Exception
	 */
	boolean updateNewSong(PageData song) throws Exception;
	
	/**
	 * 获取单曲新歌
	 * @param song
	 * @return
	 * @throws Exception
	 */
	PageData singleNewSong(PageData song)throws Exception;
}
