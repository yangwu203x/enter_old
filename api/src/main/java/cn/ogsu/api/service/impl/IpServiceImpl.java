package cn.ogsu.api.service.impl;

import cn.ogsu.api.service.IIpService;
import cn.ogsu.api.util.PageData;
import org.springframework.stereotype.Service;

/**
 * @Author leo_Yang【音特】
 * @Date 2017/4/8 0008 9:52
 */
@Service
public class IpServiceImpl extends ServiceBase implements IIpService{

    @Override
    public PageData getIpByDataBlock(PageData pd) throws Exception{
        return (PageData) daoSupport.findForObject("IpMapper.findIp", pd);
    }
}
