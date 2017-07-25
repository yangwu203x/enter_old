package cn.ogsu.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cn.ogsu.api.requestBean.RequestUserDynamic;
import cn.ogsu.api.responseBean.ResponseDynamic;
import cn.ogsu.api.responseBean.ResponseDynamic.Dynamic;
import cn.ogsu.api.service.IDynamicService;
import cn.ogsu.api.util.PageData;
import cn.ogsu.api.util.Tools;
@Service
public class DynamicServiceImpl extends ServiceBase implements IDynamicService {

	@SuppressWarnings("unchecked")
	@Override
	public ResponseDynamic queryDynamic(RequestUserDynamic request) throws Exception {
		List<PageData> list = null;
		list = (List<PageData>) daoSupport.findForList("UserDynamicMapper.queryDynamic", request);
		ResponseDynamic response = new ResponseDynamic();
		List<Dynamic> dynamicList = new ArrayList<>();
		Dynamic dynamic = null;
		for (int i = 0; i < list.size(); i++) {
			dynamic = new Dynamic();
			dynamic.setUserId(Tools.isInteger(list.get(i).getString("userId"))
					? Integer.parseInt(list.get(i).getString("userId")) : -1);
			dynamic.setUserName(list.get(i).getString("userName"));
			dynamic.setIsVip(Tools.isInteger(list.get(i).getString("isVip"))
					? Integer.parseInt(list.get(i).getString("isVip")) : -1);
			dynamic.setIsMaster(Tools.isInteger(list.get(i).getString("isMaster"))
					? Integer.parseInt(list.get(i).getString("isMaster")) : -1);
			dynamic.setIsInstruments(Tools.isInteger(list.get(i).getString("isInstruments"))
					? Integer.parseInt(list.get(i).getString("isInstruments")) : -1);
			dynamic.setUserImgPath(list.get(i).getString("userImgPath"));
			dynamic.setDynamicId(Tools.isInteger(list.get(i).getString("dynamicId"))
					? Integer.parseInt(list.get(i).getString("dynamicId")) : -1);
			dynamic.setDynamicWorld(list.get(i).getString("dynamicWorld"));
			dynamic.setDynamicFilePath(list.get(i).getString("dynamicFilePath"));
			dynamic.setDynamicType(Tools.isInteger(list.get(i).getString("dynamicType"))
					? Integer.parseInt(list.get(i).getString("dynamicType")) : -1);
			dynamic.setDynamicImgPath(list.get(i).getString("dynamicImgPath"));
			dynamic.setDynamicDate(list.get(i).getString("dynamicDate"));
			dynamicList.add(dynamic);
		}
		response.setDynamicList(dynamicList);
		return response;
	}

}
