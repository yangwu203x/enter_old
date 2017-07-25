package cn.ogsu.vod.service.impl;
import java.util.List;
import org.springframework.stereotype.Service;
import cn.ogsu.vod.service.IRoleService;
import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;
/**
 * 角色的service层实现类
 * @author albert
 * @time 2016年10月8日
 */
@Service
public class RoleServiceImpl extends ServiceBase implements IRoleService{
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> pageRole(Page page) throws Exception {
		List<PageData> pdList=(List<PageData>) daoSupport.findForList("RoleMapper.listPageRole",page);
		for (PageData pd : pdList) {
			String privilege=pd.getString("role_privilege");
			if(privilege.indexOf("1")>-1) pd.put("add", "1");
			if(privilege.indexOf("2")>-1) pd.put("del", "2");
			if(privilege.indexOf("3")>-1) pd.put("update", "3");
			if(privilege.indexOf("4")>-1) pd.put("check", "4");
		}
		return pdList;
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> allRole() throws Exception {
		return (List<PageData>) daoSupport.findForList("RoleMapper.findAllRole",null);
	}
	@Override
	public boolean addRole(PageData pd) throws Exception {
		pd.put("role_privilege",pd.getString("role_privilege")+"0");
		return ((int)daoSupport.save("RoleMapper.addRole", pd))>0;
	}
	@Override
	public boolean updateRole(PageData pd) throws Exception {
		pd.put("role_privilege",pd.getString("role_privilege")+"0");
		return ((int)daoSupport.update("RoleMapper.updateRole",pd))>0;
	}
	@Override
	public boolean deleteRoles(PageData pd) throws Exception {
		String[] roleNos=pd.getString("roleNo").split(",");
		//获取在账户表中是否有对要删除角色的引用
		int count=(int) daoSupport.findForObject("AccountMapper.findCountByRole",roleNos);
		if(count>0) return false;
		return ((int)daoSupport.delete("RoleMapper.deleteRoles",roleNos))>0;
	}
	@Override
	public PageData findSingleRole(String roleNo) throws Exception {
		PageData role=(PageData) daoSupport.findForObject("RoleMapper.findSingleRole", roleNo);
		String privilege=role.getString("role_privilege");
		if(privilege.indexOf("1")>-1) role.put("add", "1");
		if(privilege.indexOf("2")>-1) role.put("del", "2");
		if(privilege.indexOf("3")>-1) role.put("update", "3");
		if(privilege.indexOf("4")>-1) role.put("check", "4");
		return role;
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<String> autoSearch(PageData pd) throws Exception {
		return (List<String>) daoSupport.findForList("RoleMapper.autoRoleName", pd);
	}
}