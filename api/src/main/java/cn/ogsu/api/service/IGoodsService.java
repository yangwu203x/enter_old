package cn.ogsu.api.service;

import java.util.List;
import cn.ogsu.api.util.PageData;
/**
 * 商品信息的service接口层
 * @author 孙要
 * 2016-12-10
 */
public interface IGoodsService {

	/**
	 * 获取所有的商品
	 * @return
	 */
	List<PageData> obtainAllGoods() throws Exception;
}
