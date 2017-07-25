package cn.ogsu.vod.service;
import java.util.List;
import cn.ogsu.vod.entity.Menu;
import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;
/**
 * 用于菜单操作的servic接口
 * @author albert
 * @time 2016年9月6日
 */
public interface IMenuService {
	/**
	 * 增加菜单
	 * @param menu
	 * @return 菜单增加是否成功
	 */
	public boolean addMenu(Menu menu) throws Exception;
	/**
	 * 修改菜单
	 */
	public boolean updateMenu(Menu menu) throws Exception;
	/**
	 * 删除菜单
	 */
	public boolean deleteMenu(PageData paramData) throws Exception;
	/**
	 * 获取所有的菜单
	 */
	public List<Menu> obtainAllMenu() throws Exception;	
	/**
	 * 获取当前账户的菜单
	 */
	public List<Menu> obtainAccountMenu(String accountNo) throws Exception;
	/**
	 * 获取分页数据
	 */
	public List<Menu> obtainPageMenu(Page page) throws Exception;
	/**
	 * 获取单个菜单数据
	 */
	public Menu obtainSingleMenu(String menuNo) throws Exception;
	/**
	 * 获取账户拥有操作权限的菜单
	 */
	public List<String> obtainComplexMenu(String accountNo) throws Exception;
}