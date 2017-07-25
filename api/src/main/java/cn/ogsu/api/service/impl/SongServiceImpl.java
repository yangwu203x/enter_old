package cn.ogsu.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import cn.ogsu.api.requestBean.RequestHotSearch;
import cn.ogsu.api.requestBean.RequestQueryRecommendImage;
import cn.ogsu.api.requestBean.RequestSearchSongsAndSinger;
import cn.ogsu.api.requestBean.RequestSinger;
import cn.ogsu.api.requestBean.RequestSingerSongs;
import cn.ogsu.api.requestBean.RequestSongs;
import cn.ogsu.api.requestBean.RequestSongsHot;
import cn.ogsu.api.requestBean.RequestSongsA_Z;
import cn.ogsu.api.requestBean.RequestSongsFeedBack;
import cn.ogsu.api.requestBean.RequestSongsForType;
import cn.ogsu.api.requestBean.RequestUserCollect;
import cn.ogsu.api.responseBean.ResponseHotSearch;
import cn.ogsu.api.responseBean.ResponseHotSearch.ResponseData.SearchContent;
import cn.ogsu.api.responseBean.ResponseRecommendImage;
import cn.ogsu.api.responseBean.ResponseRecommendImage.ResponseData;
import cn.ogsu.api.responseBean.ResponseSearchSongsAndSinger;
import cn.ogsu.api.responseBean.ResponseSinger;
import cn.ogsu.api.responseBean.ResponseSongType;
import cn.ogsu.api.responseBean.ResponseSongType.ResponseData.SongType;
import cn.ogsu.api.responseBean.ResponseSongType.ResponseData.SongType.ScreenCondition;
import cn.ogsu.api.responseBean.ResponseSongs;
import cn.ogsu.api.service.ISongService;
import cn.ogsu.api.util.Const;
import cn.ogsu.api.util.PageData;
import cn.ogsu.api.util.Tools;

/**
 * 歌曲相关处理的service实现层
 * 
 * @author albert
 * @time 2016年9月13日
 */
@Service
public class SongServiceImpl extends ServiceBase implements ISongService {

	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> obtainAsyncSongs(PageData pd) throws Exception {
		return (List<PageData>) daoSupport.findForList("SongMapper.asyncSongs", pd);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> obtainAsyncSongType() throws Exception {
		return (List<PageData>) daoSupport.findForList("SongMapper.asyncSongTypes", null);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> obtainAsyncSongLang() throws Exception {
		return (List<PageData>) daoSupport.findForList("SongMapper.asyncSongLang", null);
	}

	@Override
	public int obtainSongPrivilege(PageData pd) throws Exception {
		return ((int) daoSupport.findForObject("SongMapper.songPrivilege", pd));
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> obtainHotSongs() throws Exception {
		return (List<PageData>) daoSupport.findForList("SongMapper.findHotSongs", null);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> obtainNewSongs() throws Exception {
		return (List<PageData>) daoSupport.findForList("SongMapper.findNewSongs", null);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> asyncSongTheme() throws Exception {
		return (List<PageData>) daoSupport.findForList("SongMapper.asyncSongTheme", null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResponseRecommendImage queryRecommendImage(RequestQueryRecommendImage pd) throws Exception {
		// TODO Auto-generated method stub
		List<Integer> v = (List<Integer>) daoSupport.findForList("SongMapper.queryRecommendImage", null);
		int version = v.get(0);
		System.err.println("version:" + version);
		ResponseRecommendImage recommendImage = new ResponseRecommendImage();
		ResponseData responseData = new ResponseData();
		recommendImage.setDataType(1);
		recommendImage.setResponseCode(1);
		recommendImage.setResponseData(responseData);

		if (version > pd.getRequestParams().getDate()) {
			responseData.setUpdate(1);
			responseData.setDate(version);
		} else {
			responseData.setUpdate(0);
		}
		return recommendImage;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResponseSongs queryUserCollect(RequestUserCollect pd) throws Exception {
		System.err.println("pd.toString():" + pd.toString());
		pd.getRequestParams().initPage();
		List<PageData> list = (List<PageData>) daoSupport.findForList("SongMapper.queryUserCollect", pd);
		return Tools.songResovleObject(daoSupport, list,
				(int) daoSupport.findForObject("SongMapper.queryUserCollectCount", pd));
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResponseSongs queryHotSongsForRecommend(RequestSongsHot pd) throws Exception {
		System.err.println("pd.toString():" + pd.toString());
		int count = pd.getRequestParams().getDataNum() * (pd.getRequestParams().getPage() + 1);
		if (count > 100)
			return new ResponseSongs();
		pd.getRequestParams().initPage();
		List<PageData> list = (List<PageData>) daoSupport.findForList("SongMapper.queryHotSongsForRecommend", pd);
		count = (int) daoSupport.findForObject("SongMapper.queryHotSongsForRecommendCount", pd);
		ResponseSongs songs = Tools.songResovleObject(daoSupport, list, count <= 100 ? count : 100);
		System.err.println("songs.toString():" + songs.toString());
		return songs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResponseSongs queryHotSongsForNewSong(RequestSongsHot pd) throws Exception {
		System.err.println("pd.toString():" + pd.toString());
		int count = pd.getRequestParams().getDataNum() * (pd.getRequestParams().getPage() + 1);
		if (count > 100)
			return new ResponseSongs();
		pd.getRequestParams().initPage();
		List<PageData> list = (List<PageData>) daoSupport.findForList("SongMapper.queryHotSongsForNewSong", pd);
		count = (int) daoSupport.findForObject("SongMapper.queryHotSongsForNewSongCount", pd);
		ResponseSongs songs = Tools.songResovleObject(daoSupport, list, count <= 100 ? count : 100);
		System.err.println("songs.toString():" + songs.toString());
		return songs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResponseSongs queryHotSongsForClassicSong(RequestSongsHot pd) throws Exception {
		System.err.println("pd.toString():" + pd.toString());
		int count = pd.getRequestParams().getDataNum() * (pd.getRequestParams().getPage() + 1);
		if (count > 100)
			return new ResponseSongs();
		pd.getRequestParams().initPage();
		List<PageData> list = (List<PageData>) daoSupport.findForList("SongMapper.queryHotSongsForClassicSong", pd);
		count = (int) daoSupport.findForObject("SongMapper.queryHotSongsForClassicSongCount", pd);
		ResponseSongs songs = Tools.songResovleObject(daoSupport, list, count <= 100 ? count : 100);
		System.err.println("songs.toString():" + songs.toString());
		return songs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResponseSongs queryHotSongsForHot(RequestSongsHot pd) throws Exception {
		System.err.println("pd.toString():" + pd.toString());
		int count = pd.getRequestParams().getDataNum() * (pd.getRequestParams().getPage() + 1);
		if (count > 100)
			return new ResponseSongs();
		pd.getRequestParams().initPage();
		List<PageData> list = (List<PageData>) daoSupport.findForList("SongMapper.queryHotSongsForHot", pd);
		count = (int) daoSupport.findForObject("SongMapper.queryHotSongsForHotCount", pd);
		ResponseSongs songs = Tools.songResovleObject(daoSupport, list, count <= 100 ? count : 100);
		System.err.println("songs.toString():" + songs.toString());
		return songs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResponseSongs queryHotSongsForOpera(RequestSongsHot pd) throws Exception {
		System.err.println("pd.toString():" + pd.toString());
		int count = pd.getRequestParams().getDataNum() * (pd.getRequestParams().getPage() + 1);
		if (count > 100)
			return new ResponseSongs();
		pd.getRequestParams().initPage();
		List<PageData> list = (List<PageData>) daoSupport.findForList("SongMapper.queryHotSongsForOpera", pd);
		count = (int) daoSupport.findForObject("SongMapper.queryHotSongsForOperaCount", pd);
		ResponseSongs songs = Tools.songResovleObject(daoSupport, list, count <= 100 ? count : 100);
		System.err.println("songs.toString():" + songs.toString());
		return songs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResponseSongs queryHotSongsForChild(RequestSongsHot pd) throws Exception {
		System.err.println("pd.toString():" + pd.toString());
		int count = pd.getRequestParams().getDataNum() * (pd.getRequestParams().getPage() + 1);
		if (count > 100)
			return new ResponseSongs();
		pd.getRequestParams().initPage();
		List<PageData> list = (List<PageData>) daoSupport.findForList("SongMapper.queryHotSongsForChild", pd);
		count = (int) daoSupport.findForObject("SongMapper.queryHotSongsForChildCount", pd);
		ResponseSongs songs = Tools.songResovleObject(daoSupport, list, count <= 100 ? count : 100);
		System.err.println("songs.toString():" + songs.toString());
		return songs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResponseSongs queryHotSongsForK_POP(RequestSongsHot pd) throws Exception {
		System.err.println("pd.toString():" + pd.toString());
		int count = pd.getRequestParams().getDataNum() * (pd.getRequestParams().getPage() + 1);
		if (count > 100)
			return new ResponseSongs();
		pd.getRequestParams().initPage();
		List<PageData> list = (List<PageData>) daoSupport.findForList("SongMapper.queryHotSongsForK_POP", pd);
		count = (int) daoSupport.findForObject("SongMapper.queryHotSongsForK_POPCount", pd);
		ResponseSongs songs = Tools.songResovleObject(daoSupport, list, count <= 100 ? count : 100);
		System.err.println("songs.toString():" + songs.toString());
		return songs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResponseSongs queryHotSongsForJapan(RequestSongsHot pd) throws Exception {
		System.err.println("pd.toString():" + pd.toString());
		int count = pd.getRequestParams().getDataNum() * (pd.getRequestParams().getPage() + 1);
		if (count > 100)
			return new ResponseSongs();
		pd.getRequestParams().initPage();
		List<PageData> list = (List<PageData>) daoSupport.findForList("SongMapper.queryHotSongsForJapan", pd);
		count = (int) daoSupport.findForObject("SongMapper.queryHotSongsForJapanCount", pd);
		ResponseSongs songs = Tools.songResovleObject(daoSupport, list, count <= 100 ? count : 100);
		System.err.println("songs.toString():" + songs.toString());
		return songs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResponseSongs queryHotSongsForAmerica(RequestSongsHot pd) throws Exception {
		System.err.println("pd.toString():" + pd.toString());
		int count = pd.getRequestParams().getDataNum() * (pd.getRequestParams().getPage() + 1);
		if (count > 100)
			return new ResponseSongs();
		pd.getRequestParams().initPage();
		List<PageData> list = (List<PageData>) daoSupport.findForList("SongMapper.queryHotSongsForAmerica", pd);
		count = (int) daoSupport.findForObject("SongMapper.queryHotSongsForAmericaCount", pd);
		ResponseSongs songs = Tools.songResovleObject(daoSupport, list, count <= 100 ? count : 100);
		System.err.println("songs.toString():" + songs.toString());
		return songs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResponseSongs queryHotSongsForFilipino(RequestSongsHot pd) throws Exception {
		System.err.println("pd.toString():" + pd.toString());
		int count = pd.getRequestParams().getDataNum() * (pd.getRequestParams().getPage() + 1);
		if (count > 100)
			return new ResponseSongs();
		pd.getRequestParams().initPage();
		List<PageData> list = (List<PageData>) daoSupport.findForList("SongMapper.queryHotSongsForFilipino", pd);
		count = (int) daoSupport.findForObject("SongMapper.queryHotSongsForFilipinoCount", pd);
		ResponseSongs songs = Tools.songResovleObject(daoSupport, list, count <= 100 ? count : 100);
		System.err.println("songs.toString():" + songs.toString());
		return songs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResponseSongs queryHotSongsForVietnam(RequestSongsHot pd) throws Exception {
		System.err.println("pd.toString():" + pd.toString());
		int count = pd.getRequestParams().getDataNum() * (pd.getRequestParams().getPage() + 1);
		if (count > 100)
			return new ResponseSongs();
		pd.getRequestParams().initPage();
		List<PageData> list = (List<PageData>) daoSupport.findForList("SongMapper.queryHotSongsForVietnam", pd);
		count = (int) daoSupport.findForObject("SongMapper.queryHotSongsForVietnamCount", pd);
		ResponseSongs songs = Tools.songResovleObject(daoSupport, list, count <= 100 ? count : 100);
		System.err.println("songs.toString():" + songs.toString());
		return songs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResponseSongs querySongsForA_Z(RequestSongsA_Z pd) throws Exception {
		System.err.println("pd.toString():" + pd.toString());
		int count = pd.getRequestParams().getDataNum() * (pd.getRequestParams().getPage() + 1);
		pd.getRequestParams().initPage();
		ResponseSongs songs = null;
		List<PageData> list = null;
		switch (pd.getRequestParams().getSongType()) {
		case 1:
			list = (List<PageData>) daoSupport.findForList("SongMapper.querySongsForA_ZNewSong", pd);
			count = (int) daoSupport.findForObject("SongMapper.querySongsForA_ZNewSongCount", pd);
			break;
		case 2:
			list = (List<PageData>) daoSupport.findForList("SongMapper.querySongsForA_ZClassic", pd);
			count = (int) daoSupport.findForObject("SongMapper.querySongsForA_ZClassicCount", pd);
			break;
		case 3:
			list = (List<PageData>) daoSupport.findForList("SongMapper.querySongsForA_ZHot", pd);
			count = (int) daoSupport.findForObject("SongMapper.querySongsForA_ZHotCount", pd);
			break;
		case 4:
			list = (List<PageData>) daoSupport.findForList("SongMapper.querySongsForA_ZOpera", pd);
			count = (int) daoSupport.findForObject("SongMapper.querySongsForA_ZOperaCount", pd);
			break;
		}
		songs = Tools.songResovleObject(daoSupport, list, count);
		System.err.println("songs.toString():" + songs.toString());
		return songs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResponseSearchSongsAndSinger searchSongsAndSinger(RequestSearchSongsAndSinger pd) throws Exception {

		ResponseSearchSongsAndSinger searchSongsAndSinger = new ResponseSearchSongsAndSinger();
		searchSongsAndSinger.setDataType(1);
		searchSongsAndSinger.setResponseCode(1);

		cn.ogsu.api.responseBean.ResponseSearchSongsAndSinger.ResponseData responseData = new cn.ogsu.api.responseBean.ResponseSearchSongsAndSinger.ResponseData();
		List<PageData> promptList = null;
		List<PageData> songList = null;
		List<PageData> singerList = null;
		int totalCount = 0;
		pd.getRequestParams().initPage();
		promptList = (List<PageData>) daoSupport.findForList("SongMapper.querySongsByNamePrompt", pd);
		if (Tools.strIsEnglish(pd.getRequestParams().getContent())) {
			songList = (List<PageData>) daoSupport.findForList("SongMapper.querySongsBySpell", pd);
			singerList = (List<PageData>) daoSupport.findForList("SongMapper.querySingerBySpell", pd);
			totalCount = (int) daoSupport.findForObject("SongMapper.querySongsBySpellCount", pd);
		} else {
			songList = (List<PageData>) daoSupport.findForList("SongMapper.querySongsByName", pd);
			singerList = (List<PageData>) daoSupport.findForList("SongMapper.querySingerByName", pd);
			totalCount = (int) daoSupport.findForObject("SongMapper.querySongsByNameCount", pd);
		}
		responseData.setTotalCount(totalCount);
		responseData.setPromptList(Tools.songResovleObject(daoSupport, promptList));
		responseData.setSongList(Tools.songResovleObject(daoSupport, songList));
		responseData.setSingerList(Tools.singerResovleObject(daoSupport, singerList));
		searchSongsAndSinger.setResponseData(responseData);
		return searchSongsAndSinger;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResponseSinger searchSinger(RequestSinger pd) throws Exception {
		ResponseSinger responseSinger = new ResponseSinger();
		responseSinger.setDataType(1);
		responseSinger.setResponseCode(1);
		cn.ogsu.api.responseBean.ResponseSinger.ResponseData responseData = new cn.ogsu.api.responseBean.ResponseSinger.ResponseData();
		pd.getRequestParams().initPage();
		List<PageData> singerList = null;
		int totalCount = 0;
		if (Tools.strIsEnglish(pd.getRequestParams().getContent())) {
			singerList = (List<PageData>) daoSupport.findForList("SongMapper.querySingerBySpellLimit", pd);
			totalCount = (int) daoSupport.findForObject("SongMapper.querySingerBySpellLimitCount", pd);
		} else {
			singerList = (List<PageData>) daoSupport.findForList("SongMapper.querySingerByNameLimit", pd);
			totalCount = (int) daoSupport.findForObject("SongMapper.querySingerByNameLimitCount", pd);
		}
		responseData.setSingerList(Tools.singerResovleObject(daoSupport, singerList));
		responseData.setTotalCount(totalCount);
		responseSinger.setResponseData(responseData);
		return responseSinger;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResponseSongs searchSongs(RequestSongs pd) throws Exception {
		pd.getRequestParams().initPage();
		List<PageData> list = null;
		int count = 0;
		if (Tools.strIsEnglish(pd.getRequestParams().getContent())) {
			list = (List<PageData>) daoSupport.findForList("SongMapper.querySongsBySpellLimit", pd);
			count = (int) daoSupport.findForObject("SongMapper.querySongsBySpellLimitCount", pd);
		} else {
			list = (List<PageData>) daoSupport.findForList("SongMapper.querySongsByNameLimit", pd);
			count = (int) daoSupport.findForObject("SongMapper.querySongsByNameLimitCount", pd);
		}

		ResponseSongs songs = Tools.songResovleObject(daoSupport, list, count);
		System.err.println("songs.toString():" + songs.toString());
		return songs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResponseHotSearch searchHotSearch(RequestHotSearch pd) throws Exception {
		List<PageData> list = (List<PageData>) daoSupport.findForList("SongMapper.queryHotSearch", pd);
		ResponseHotSearch hotSearch = new ResponseHotSearch();
		hotSearch.setDataType(1);
		hotSearch.setResponseCode(1);
		cn.ogsu.api.responseBean.ResponseHotSearch.ResponseData responseData = new cn.ogsu.api.responseBean.ResponseHotSearch.ResponseData();
		List<SearchContent> searchList = new ArrayList<>();
		SearchContent content = null;
		for (int i = 0; i < list.size(); i++) {
			content = new SearchContent();
			content.setSearchId(list.get(i).getString("id"));
			content.setSearchContent(list.get(i).getString("song_name"));
			searchList.add(content);
		}
		responseData.setSearchList(searchList);
		hotSearch.setResponseData(responseData);
		return hotSearch;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResponseSongType searchSongType() throws Exception {
		List<PageData> list = (List<PageData>) daoSupport.findForList("SongMapper.searchSongType", null);
		ResponseSongType responseSongType = new ResponseSongType();
		responseSongType.setDataType(1);
		responseSongType.setResponseCode(1);
		cn.ogsu.api.responseBean.ResponseSongType.ResponseData responseData = new cn.ogsu.api.responseBean.ResponseSongType.ResponseData();
		List<SongType> listSongType = new ArrayList<>();
		SongType songType = null;
		String name = "";
		ScreenCondition screen = null;
		for (int i = 0; i < list.size(); i++) {
			songType = new SongType();
			songType.setTypeName(list.get(i).getString("commend_type"));
			songType.setSourceId(Tools.isInteger(list.get(i).getString("commend_source"))
					? Integer.parseInt(list.get(i).getString("commend_source")) : -1);
			songType.setField_id(Tools.isInteger(list.get(i).getString("field_id"))
					? Integer.parseInt(list.get(i).getString("field_id")) : -1);
			songType.setRank(Tools.isInteger(list.get(i).getString("rank"))
					? Integer.parseInt(list.get(i).getString("rank")) : -1);
			List<ScreenCondition> listScreen = new ArrayList<>();
			if (Tools.isInteger(list.get(i).getString("commend_screen"))) {
				screen = new ScreenCondition();
				screen.setScreenId(Integer.parseInt(list.get(i).getString("commend_screen")));
				name = (String) daoSupport.findForObject("SongMapper.queryScreenNameById",
						Integer.parseInt(list.get(i).getString("commend_screen")));
				screen.setScreenName(name);
				listScreen.add(screen);
				songType.setScreenCondition(listScreen);
			}
			if (Tools.isInteger(list.get(i).getString("commend_screen2"))) {
				screen = new ScreenCondition();
				screen.setScreenId(Integer.parseInt(list.get(i).getString("commend_screen2")));
				name = (String) daoSupport.findForObject("SongMapper.queryScreenNameById",
						Integer.parseInt(list.get(i).getString("commend_screen2")));
				screen.setScreenName(name);
				listScreen.add(screen);
				songType.setScreenCondition(listScreen);
			}
			if (Tools.isInteger(list.get(i).getString("commend_screen3"))) {
				screen = new ScreenCondition();
				screen.setScreenId(Integer.parseInt(list.get(i).getString("commend_screen3")));
				name = (String) daoSupport.findForObject("SongMapper.queryScreenNameById",
						Integer.parseInt(list.get(i).getString("commend_screen3")));
				screen.setScreenName(name);
				listScreen.add(screen);
				songType.setScreenCondition(listScreen);
			}
			if (Tools.isInteger(list.get(i).getString("commend_screen4"))) {
				screen = new ScreenCondition();
				screen.setScreenId(Integer.parseInt(list.get(i).getString("commend_screen4")));
				name = (String) daoSupport.findForObject("SongMapper.queryScreenNameById",
						Integer.parseInt(list.get(i).getString("commend_screen4")));
				screen.setScreenName(name);
				listScreen.add(screen);
				songType.setScreenCondition(listScreen);
			}
			listSongType.add(songType);
		}
		responseData.setSongType(listSongType);
		responseSongType.setResponseData(responseData);
		return responseSongType;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResponseSongs searchSongsForType(RequestSongsForType pd) throws Exception {
		List<PageData> list = null;
		int count = 0;
		pd.getRequestParams().initPage();
		switch (pd.getRequestParams().getSourceId()) {
		case Const.SOURCE_COMMEND_SONGS:
			list = (List<PageData>) daoSupport.findForList("SongMapper.queryCommendSongs", pd);
			count = (int) daoSupport.findForObject("SongMapper.queryCommendSongsCount", pd);
			break;
		case Const.SOURCE_NEW_SONG:
			list = (List<PageData>) daoSupport.findForList("SongMapper.queryNewSong", pd);
			count = (int) daoSupport.findForObject("SongMapper.queryNewSongCount", pd);
			break;
		case Const.SOURCE_SONGS_CLASSIC:
			list = (List<PageData>) daoSupport.findForList("SongMapper.queryClassicSong", pd);
			count = (int) daoSupport.findForObject("SongMapper.queryClassicSongCount", pd);
			break;
		case Const.SOURCE_SONGS_LANG:
			list = (List<PageData>) daoSupport.findForList("SongMapper.queryLangSong", pd);
			count = (int) daoSupport.findForObject("SongMapper.queryLangCount", pd);
			break;
		case Const.SOURCE_SONGS_ORDERTIME:
			list = (List<PageData>) daoSupport.findForList("SongMapper.queryOrderTimeSong", pd);
			count = (int) daoSupport.findForObject("SongMapper.queryOrderTimeSongCount", pd);
			break;
		case Const.SOURCE_SONGS_TYPE:
			list = (List<PageData>) daoSupport.findForList("SongMapper.queryTypeSong", pd);
			count = (int) daoSupport.findForObject("SongMapper.queryTypeSongCount", pd);
			break;
		default:
			list = (List<PageData>) daoSupport.findForList("SongMapper.queryThemeSong", pd);
			count = (int) daoSupport.findForObject("SongMapper.queryThemeSongCount", pd);
			break;
		}
		ResponseSongs songs = Tools.songResovleObject(daoSupport, list, count);
		System.err.println("songs.toString():" + songs.toString());
		return songs;
	}

	@Override
	public String userFeedBackForSongs(RequestSongsFeedBack pd) throws Exception {
		daoSupport.findForObject("SongMapper.userFeedBackForSongs", pd);
		return "1";
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResponseSongs searchSingerSongs(RequestSingerSongs pd) throws Exception {
		pd.getRequestParams().initPage();
		List<PageData> list = null;
		int count = 0;
		list = (List<PageData>) daoSupport.findForList("SongMapper.querySingerSongs", pd);
		count = (int) daoSupport.findForObject("SongMapper.querySingerSongsCount", pd);
		ResponseSongs songs = Tools.songResovleObject(daoSupport, list, count);
		return songs;
	}

}
