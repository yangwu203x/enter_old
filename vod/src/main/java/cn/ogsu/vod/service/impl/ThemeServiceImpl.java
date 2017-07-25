package cn.ogsu.vod.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.ogsu.vod.service.IThemeService;
import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;

/**
 * 歌曲主题的service实现层
 * @author Administrator
 */
@Service
public class ThemeServiceImpl extends ServiceBase implements IThemeService{

	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> themeList(Page page) throws Exception {
		return (List<PageData>) daoSupport.findForList("ThemeMapper.listPageTheme",page);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> allTheme() throws Exception {
		return (List<PageData>) daoSupport.findForList("ThemeMapper.allTheme",null);
	}

	@Override
	public PageData singerTheme(String themeId) throws Exception {
		return (PageData) daoSupport.findForObject("ThemeMapper.findThemeById",themeId);
	}

	@Override
	public boolean addTheme(PageData pd) throws Exception {
		return ((int)daoSupport.save("ThemeMapper.addTheme",pd))>0;
	}

	@Override
	public boolean updateTheme(PageData pd) throws Exception {
		pd.put("status","0");
		return ((int)daoSupport.update("ThemeMapper.updateTheme",pd))>0;
	}

	@Override
	public boolean updateStatus(PageData pd) throws Exception {
		pd.put("status", 1);
		return ((int)daoSupport.update("ThemeMapper.updateTheme",pd))>0;
	}
}
