package cn.ogsu.vod.service.impl;
import java.util.List;
import org.springframework.stereotype.Service;
import cn.ogsu.vod.service.ISingerTypeService;
import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;
/**
 * 歌手类型的任务处理的service实现层
 * @author albert
 * @time 2016年9月14日
 */
@Service
public class SingerTypeServiceImpl extends ServiceBase implements ISingerTypeService{
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> singerTypeList(Page page) throws Exception {
		return (List<PageData>)daoSupport.findForList("SingerTypeMapper.listPageSingerType",page);
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> allSingerType() throws Exception {
		return (List<PageData>) daoSupport.findForList("SingerTypeMapper.allSingerType",null);
	}
	@Override
	public PageData singerType(String singerTypeId) throws Exception {
		return (PageData) daoSupport.findForObject("SingerTypeMapper.singleSingerType", singerTypeId);
	}
	@Override
	public boolean addSingerType(PageData pd) throws Exception {
		return ((int)daoSupport.save("SingerTypeMapper.addSingerType",pd))>0;
	}
	@Override
	public boolean updateSingerType(PageData pd) throws Exception {
		pd.put("status","0");
		return ((int)daoSupport.update("SingerTypeMapper.updateSingerType",pd))>0;
	}
	@Override
	public boolean updateStatus(PageData pd) throws Exception {
		pd.put("status","1");
		return ((int)daoSupport.update("SingerTypeMapper.updateSingerType", pd))>0;
	}
}
