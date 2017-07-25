package cn.ogsu.api.util;

import org.lionsoul.ip2region.DataBlock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author leo_Yang【音特】
 * @Date 2017/4/5 0005 10:54
 */
public class IPUtils {

    private static final Logger logger = LoggerFactory.getLogger(IPUtils.class);
    /**
     * 获取ip地址
     * @param request
     * @return
     */
    public static String getRemoteIP(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        logger.info("访问者ip为：" + ip);
        return ip;
    }
    /**
     * 获取访问者IP
     *
     * 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
     *
     * 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)，
     * 如果还不存在则调用Request .getRemoteAddr()。
     *
     * @param request
     * @return
     */
//    public static String getIpAddr(HttpServletRequest request) {
//        String ip = request.getHeader("X-Real-IP");
//        if (ip != null && ip.length() > 0  && !"unknown".equalsIgnoreCase(ip)) {
//            return ip;
//        }
//        ip = request.getHeader("X-Forwarded-For");
//        if (ip != null && ip.length() > 0 && !"unknown".equalsIgnoreCase(ip)) {
//            // 多次反向代理后会有多个IP值，第一个为真实IP。
//            int index = ip.indexOf(',');
//            if (index != -1) {
//                return ip.substring(0, index);
//            } else {
//                return ip;
//            }
//        } else {
//            return request.getRemoteAddr();
//        }
//    }

    /**
     *
     * getAddresses:根据外网ip，判断该ip所在的地理位置 ,分配对应的ip<br/>
     *
     * @param request
     * @return 该ip所在的地理位置 <br/>
     */
    public static PageData getAddresses(HttpServletRequest request) {
        String ip = getRemoteIP(request);
        //地理位置
        DataBlock dataBlock = AddressUtilsOfline.getAddressById(ip);
        logger.info("地理位置：" + dataBlock.toString());
        PageData pd = new PageData();
        pd.put("city_id" , dataBlock.getCityId());
        pd.put("region" , dataBlock.getRegion());
        pd.put("dataPtr" , dataBlock.getDataPtr());
        return pd;
    }

}
