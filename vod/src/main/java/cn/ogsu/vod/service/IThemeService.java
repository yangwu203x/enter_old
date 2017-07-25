package cn.ogsu.vod.service;

import java.util.List;

import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;

/**
 * 主题的service接口
 * @author albert
 *2016/11/03
 */
public interface IThemeService {
	/**
	 * 分页获取主题列表
	 */
	public List<PageData> themeList(Page page) throws Exception;
	/**
	 * 获取所有的主题
	 */
	public List<PageData> allTheme() throws Exception;
	/**
	 * 根据主题编号获取主题信息
	 */
	public PageData singerTheme(String themeId) throws Exception;
	/**
	 * 增加主题
	 */
	public boolean addTheme(PageData pd) throws Exception;
	/**
	 * 修改主题
	 */
	public boolean updateTheme(PageData pd) throws Exception;
	/**
	 * 修改审核状态
	 */
	boolean updateStatus(PageData pd) throws Exception;
}
