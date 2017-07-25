package cn.ogsu.vod.service.impl;
import java.util.List;
import org.springframework.stereotype.Service;
import cn.ogsu.vod.service.ISongLangService;
import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;
/**
 *歌曲语种的service实现层
 * @author albert
 * @time 2016年9月20日
 */
@Service
public class SongLangServiceImpl extends ServiceBase implements ISongLangService{
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> pageSongLangs(Page page) throws Exception {
		return (List<PageData>) daoSupport.findForList("SongLangMapper.listPageSongLang", page);
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> allSongLangs() throws Exception {
		return (List<PageData>) daoSupport.findForList("SongLangMapper.allSongLang", null);
	}
	@Override
	public PageData singleSongLang(String lang_id) throws Exception {
		return (PageData) daoSupport.findForObject("SongLangMapper.singleSongLang",lang_id);
	}
	@Override
	public boolean addSongLang(PageData songLang) throws Exception {
		return ((int)daoSupport.save("SongLangMapper.addSongLang",songLang))>0;
	}
	@Override
	public boolean updateSongLang(PageData songLang) throws Exception {
		songLang.put("status","0");
		return ((int)daoSupport.update("SongLangMapper.updateSongLang", songLang))>0;
	}
	@Override
	public boolean updateStatus(PageData pd) throws Exception {
		pd.put("status","1");
		return ((int)daoSupport.update("SongLangMapper.updateSongLang", pd))>0;
	}
}