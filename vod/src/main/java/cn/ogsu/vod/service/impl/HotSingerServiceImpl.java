package cn.ogsu.vod.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import cn.ogsu.vod.service.IHotSingerService;
import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;
/**
 * 热门歌手的service接口实现层
 * @author albert
 *
 */
@Service
public class HotSingerServiceImpl extends ServiceBase implements IHotSingerService {

	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> hotSingers(Page page) throws Exception {
		return (List<PageData>) daoSupport.findForList("HotSingerMapper.listPageHotSinger",page);
	}

	@Override
	public boolean addHotSinger(PageData singer) throws Exception {
		return ((int)daoSupport.save("HotSingerMapper.addHotSinger",singer))>0;
	}

	@Override
	public boolean updateHotSinger(PageData singer) throws Exception {
		return ((int)daoSupport.update("HotSingerMapper.updateHotSinger",singer))>0;
	}

	@Override
	public boolean delHotSinger(PageData singer) throws Exception {
		String hotId=singer.get("hotId").toString();
		String[] hotIds=hotId.split(",");
		return ((int)daoSupport.delete("HotSingerMapper.delHotSingers",hotIds))>0;
	}

	@Override
	public PageData obtainHotSingerById(PageData singer) throws Exception {
		String hotId=singer.get("hotId").toString();
		return (PageData) daoSupport.findForObject("HotSingerMapper.findSingleHotSinger", hotId);
	}

}
