package cn.ogsu.api.util;

import org.lionsoul.ip2region.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;

/**
 * 离线查询ip归属
 * @Author leo_Yang【音特】
 * @Date 2017/4/6 0006 15:00
 */
public class AddressUtilsOfline {
    private static final Logger logger = LoggerFactory.getLogger(AddressUtilsOfline.class);
    //ip2region.db数据库所在目录
    private static String dbPath = AppUtils.AppPath + File.separator + "data" + File.separator + "ip2region.db";

    public static void main(String[] args){
        System.out.println(getAddressById("183.39.197.158").getCityId() + "<>" + getAddressById("183.39.197.158").getRegion() + "<>" + getAddressById("183.39.197.158").getDataPtr());
        System.out.println(getAddressById("123.57.139.101").getCityId() + "<>" + getAddressById("123.57.139.101").getRegion()+ "<>" + getAddressById("123.57.139.101").getDataPtr());
        System.out.println(getAddressById("113.112.253.157").getCityId() + "<>" + getAddressById("113.112.253.157").getRegion()+ "<>" + getAddressById("113.112.253.157").getDataPtr());
    }
    /**
     * 根据ip获取地址
     * @param ip
     * @return
     */
    public static DataBlock getAddressById(String ip)
    {
        try {
            DbConfig config = new DbConfig();
            DbSearcher searcher = new DbSearcher(config, dbPath);

            //define the method
            Method method = searcher.getClass().getMethod("binarySearch", String.class);


            double sTime = 0, cTime = 0;
            DataBlock dataBlock = null;
            sTime = System.nanoTime();
            dataBlock = (DataBlock) method.invoke(searcher, ip);
            cTime = (System.nanoTime() - sTime) / 1000000;
            logger.info(dataBlock.toString());
            logger.info(cTime + "");

            searcher.close();
            logger.info("+--Bye");
            logger.info(dataBlock.getRegion() + "<>" + dataBlock.getCityId() + "<>" + dataBlock.getDataPtr());
            return dataBlock;
        }  catch (Exception e) {
            logger.info(e.getMessage());
            return null;
        }
    }
}
