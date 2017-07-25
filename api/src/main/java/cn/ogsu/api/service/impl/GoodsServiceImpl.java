package cn.ogsu.api.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.ogsu.api.service.IGoodsService;
import cn.ogsu.api.util.PageData;
/**
 * 商品信息的service实现层
 * @author 孙要
 * 2016-12-10
 */
@Service
public class GoodsServiceImpl extends ServiceBase implements IGoodsService{

	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> obtainAllGoods() throws Exception{
		return (List<PageData>) daoSupport.findForList("GoodsMapper.allGoods",null);
	}
}
