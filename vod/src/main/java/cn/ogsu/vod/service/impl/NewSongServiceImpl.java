package cn.ogsu.vod.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.ogsu.vod.service.INewSongService;
import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;
/**
 * 新歌service的接口实现层
 * @author albert
 *
 */
@Service
public class NewSongServiceImpl extends ServiceBase implements INewSongService {

	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> newSongs(Page page) throws Exception {
		return (List<PageData>) daoSupport.findForList("NewSongMapper.listPageNewSongs", page);
	}

	@Override
	public boolean addNewSong(PageData song) throws Exception {
		return ((int)daoSupport.save("NewSongMapper.addNewSong",song))>0;
	}

	@Override
	public boolean delNewSong(PageData song) throws Exception {
		String newId=song.get("newId").toString();
		String[] newIds=newId.split(",");
		return ((int)daoSupport.delete("NewSongMapper.delNewSongs",newIds))>0;
	}

	@Override
	public PageData singleNewSong(PageData song) throws Exception {
		String newId=song.get("newId").toString();
		return (PageData) daoSupport.findForObject("NewSongMapper.findSingleNewSong",newId);
	}

	@Override
	public boolean updateNewSong(PageData song) throws Exception {
		return ((int)daoSupport.update("NewSongMapper.updateNewSong",song))>0;
	}
}
