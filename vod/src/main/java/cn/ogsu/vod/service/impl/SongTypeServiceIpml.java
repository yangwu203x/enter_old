package cn.ogsu.vod.service.impl;
import java.util.List;
import org.springframework.stereotype.Service;
import cn.ogsu.vod.service.ISongTypeService;
import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;
/**
 *歌曲类型的service实现层
 * @author albert
 * @time 2016年9月20日
 */
@Service
public class SongTypeServiceIpml extends ServiceBase implements ISongTypeService{

	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> songTypeList(Page page) throws Exception {
		return (List<PageData>) daoSupport.findForList("SongTypeMapper.listPageSongType", page);
	}

	@Override
	public PageData singleSongType(String typeId) throws Exception {
		return (PageData) daoSupport.findForObject("SongTypeMapper.singleSongType", typeId);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> allType() throws Exception {
		return (List<PageData>) daoSupport.findForList("SongTypeMapper.allSongType", null);
	}

	@Override
	public boolean updateSongType(PageData songType) throws Exception {
		songType.put("status","0");
		return ((int)daoSupport.update("SongTypeMapper.updateSongType",songType))>0;
	}

	@Override
	public boolean addSongType(PageData songType) throws Exception {
		return ((int)daoSupport.save("SongTypeMapper.addSongType", songType))>0;
	}

	@Override
	public boolean updateStatus(PageData pd) throws Exception {
		pd.put("status","1");
		return ((int)daoSupport.update("SongTypeMapper.updateSongType",pd))>0;
	}
	
}