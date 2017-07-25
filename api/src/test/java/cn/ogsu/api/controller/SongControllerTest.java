package cn.ogsu.api.controller;

import cn.ogsu.api.requestBean.RequestSongs;
import cn.ogsu.api.responseBean.ResponseSongs;
import cn.ogsu.api.service.ISongService;
import cn.ogsu.api.util.PageData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * @Author leo_Yang【音特】
 * @Date 2017/5/18 0018 13:24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/ApplicationContext.xml"})
public class SongControllerTest {
//    private static final Logger logger = LoggerFactory.getLogger(SongControllerTest.class);
    @Autowired
    private ISongService songService;
    @Test
    public void searchSongs() throws Exception {
        long start = System.currentTimeMillis();
        RequestSongs pd = new RequestSongs();
        RequestSongs.RequestParams rp = new RequestSongs.RequestParams();
        rp.setDataNum(10000000);
        rp.setPage(1);
        rp.setContent("hehe");
        pd.setRequestParams(rp);
        ResponseSongs responseImage = songService.searchSongs(pd);
        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println("time={"+time+"}");
//        System.out.println(responseImage.toString());
    }


}
