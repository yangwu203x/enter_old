package cn.ogsu.api.service;

import cn.ogsu.api.util.PageData;
import org.lionsoul.ip2region.DataBlock;

/**
 * @Author leo_Yang【音特】
 * @Date 2017/4/8 0008 9:51
 */
public interface IIpService {
    public PageData getIpByDataBlock(PageData pd) throws Exception;
}
