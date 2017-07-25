package cn.ogsu.vod.service.impl;
import java.util.List;
import org.springframework.stereotype.Service;
import cn.ogsu.vod.service.ISingerService;
import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;
/**
 * 歌手相关任务的service实现层
 * @author albert
 * @time 2016年9月14日
 */
@Service
public class SingerServiceImpl extends ServiceBase implements ISingerService{
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> obtainSingers(Page page) throws Exception {
		return (List<PageData>) daoSupport.findForList("SingerMapper.listPageSinger",page);
	}
	@Override
	public PageData obtainSinger(String singerId) throws Exception {
		return (PageData) daoSupport.findForObject("SingerMapper.singleSinger",singerId);
	}
	@Override
	public boolean addSinger(PageData pd) throws Exception {
		return ((int)daoSupport.save("SingerMapper.addSinger",pd))>0;
	}
	@Override
	public boolean updateSinger(PageData pd) throws Exception {
		pd.put("status","0");
		return ((int)daoSupport.update("SingerMapper.updateSinger",pd))>0;
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> obtainAllSinger() throws Exception {
		return (List<PageData>) daoSupport.findForList("SingerMapper.allSinger", null);
	}
	@Override
	public boolean deleteSinger(String singerId) throws Exception {
		return ((int)daoSupport.delete("SingerMapper.deleteSingers", singerId))>0;
	}
	@Override
	public boolean updateStatus(PageData pd) throws Exception {
		pd.put("status","1");
		return ((int)daoSupport.update("SingerMapper.updateSinger", pd))>0;
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<String> autoSearch(PageData pd) throws Exception {
		return (List<String>) daoSupport.findForList("SingerMapper.autoSingerName",pd);
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<Integer> findIdByName(String singerName) throws Exception {
		PageData pd=new PageData();
		pd.put("singerName", singerName);
		return (List<Integer>) daoSupport.findForList("SingerMapper.findIdByName", pd);
	}
}