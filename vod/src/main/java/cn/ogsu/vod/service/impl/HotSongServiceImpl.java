package cn.ogsu.vod.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import cn.ogsu.vod.service.IHotSongService;
import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;
/**
 * 热门歌曲的service接口实现层
 * @author albert
 */
@Service
public class HotSongServiceImpl extends ServiceBase implements IHotSongService {

	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> hotSongs(Page page) throws Exception {
		return (List<PageData>) daoSupport.findForList("HotSongMapper.listPageHotSongs",page);
	}

	@Override
	public boolean addHotSong(PageData song) throws Exception {
		return ((int)daoSupport.save("HotSongMapper.addHotSong", song))>0;
	}

	@Override
	public boolean updateHotSong(PageData song) throws Exception {
		return ((int)daoSupport.update("HotSongMapper.updateHotSong",song))>0;
	}

	@Override
	public boolean delHotSong(PageData song) throws Exception {
		String hotId=song.get("hotId").toString();
		String[] hotIds=hotId.split(",");
		return ((int)daoSupport.delete("HotSongMapper.delHotSongs",hotIds))>0;
	}

	@Override
	public PageData obtainHotSongById(PageData song) throws Exception {
		String hotId=song.get("hotId").toString();
		return (PageData) daoSupport.findForObject("HotSongMapper.findSingleHotSong",hotId);
	}

}
