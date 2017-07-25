package cn.ogsu.vod.service.impl;
import java.util.List;
import org.springframework.stereotype.Service;
import cn.ogsu.vod.service.IUserInfoService;
import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;
/**
 * 用户信息的service实现层
 * @author albert
 * @time 2016年9月22日
 */
@Service
public class UserInfoServiceImpl extends ServiceBase implements IUserInfoService{
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> obtainPageUser(Page page) throws Exception {
		return (List<PageData>) daoSupport.findForList(
				"UserInfoMapper.listPageUserInfo",page);
	}
	@Override
	public PageData obtainSingleUser(String user_id) throws Exception {
		return (PageData) daoSupport.findForObject("UserInfoMapper.singleUserInfo",user_id);
	}
	@Override
	public boolean addUser(PageData userInfo) throws Exception {
		return ((int)daoSupport.save("UserInfoMapper.addUserInfo",userInfo))>0;
	}
	@Override
	public boolean updateUser(PageData userInfo) throws Exception {
		return ((int)daoSupport.update("UserInfoMapper.updateUserInfo",userInfo))>0;
	}
	@Override
	public boolean delUsers(PageData pd) throws Exception {
		String[] userNos=pd.getString("userNo").split(",");
		return ((int)daoSupport.delete("UserInfoMapper.delUsers",userNos))>0;
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<String> autoSearch(PageData pd) throws Exception {
		String searchType=pd.getString("searchType");
		List<String> results=null;
		if(searchType.equals("m")){
			results=(List<String>) daoSupport.findForList("UserInfoMapper.autoMacAddress",pd);
		}else{
			results=(List<String>) daoSupport.findForList("UserInfoMapper.autoSerialNumber",pd);
		}
		return results;
	}
}