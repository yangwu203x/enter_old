package cn.ogsu.vod.service.impl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import cn.ogsu.vod.entity.Menu;
import cn.ogsu.vod.service.IMenuService;
import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;
/**
 * 用于操作菜单的service实现层
 * @author albert
 * @time 2016年9月6日
 */
@Service
public class MenuServiceImpl extends ServiceBase implements IMenuService {
	@Override
	public boolean addMenu(Menu menu) throws Exception {
		return ((int)daoSupport.save("MenuMapper.addMenu",menu))>0;
	}
	@Override
	public boolean updateMenu(Menu menu) throws Exception {
		return ((int)daoSupport.update("MenuMapper.updateMenu", menu))>0;
	}
	@Override
	public boolean deleteMenu(PageData paramData) throws Exception {
		Object obj=paramData.get("menuNos");
		String[] menuNos=null;
		//将从界面得到的菜单编号字符串转换为数组
		if(obj!=null) menuNos=obj.toString().split(",");
		else return false;//如果得不到菜单编号的字符串则不存在删除操作
		//检查删除的菜单是否存在子菜单
		int subMenuCount=(int) daoSupport.findForObject("MenuMapper.findSubMenuCount",menuNos);
		if(subMenuCount>0) return false;
		return ((int)daoSupport.delete("MenuMapper.delMenu",menuNos))>0;
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<Menu> obtainAllMenu() throws Exception {
		List<Menu> menuList=(List<Menu>) daoSupport.findForList("MenuMapper.allMenu", null);
		//通过递归解析所有菜单,形成父子级关系
		menuList=resolveMenu(menuList, 0);
		return menuList;
	}
	/**
	 * 递归解析菜单
	 */
	private List<Menu> resolveMenu(List<Menu> menuList,int parentMenuNo){
		List<Menu> subMenuList=new ArrayList<Menu>();
		for (int i = 0; i < menuList.size(); i++) {
			Menu subMenu=menuList.get(i);
			if(subMenu.getParentMenuNo()==parentMenuNo){
				//递归解析菜单
				List<Menu> menus=resolveMenu(menuList, subMenu.getMenuNo());
				//保存已经解析的子菜单
				subMenu.setSubMenuList(menus);
				//保存父菜单
				subMenuList.add(subMenu);
			}
		}
		return subMenuList;
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<Menu> obtainAccountMenu(String accountNo) throws Exception {
		List<Menu> menuList=(List<Menu>) daoSupport.findForList("MenuMapper.obtainMenuByAccount",accountNo);
		//通过递归解析所有菜单,形成父子级关系,如果对其父级菜单没有操作权限子级将同样没有操作权限
		menuList=resolveMenu(menuList, 0);
		return menuList;
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<String> obtainComplexMenu(String accountNo) throws Exception {
		List<String> menuNos=(List<String>) daoSupport.findForList("MenuMapper.findPrivilegeMenu", accountNo) ;
		return menuNos;
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<Menu> obtainPageMenu(Page page) throws Exception {
		return (List<Menu>) daoSupport.findForList("MenuMapper.listPageMenu",page);
	}
	@Override
	public Menu obtainSingleMenu(String menuNo) throws Exception {
		return (Menu) daoSupport.findForObject("MenuMapper.findSingleMenu",menuNo);
	}
}