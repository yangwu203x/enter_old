package cn.ogsu.api.util;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.ogsu.api.bean.SingerInfo;
import cn.ogsu.api.bean.SongInfo;
import cn.ogsu.api.dao.DaoSupport;
import cn.ogsu.api.responseBean.ResponseSongs;
import net.sf.json.JSONObject;

/**
 * 工具类
 *
 * @author albert
 * @time 2016年9月27日
 */
public class Tools {
	/**
	 * 加密响应数据
	 * 
	 * @param result
	 * @return
	 */
	public static String encode(String result) {
		try {
			result = Base64.encode(result.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param date
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date date) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		date = sdf.parse(sdf.format(date));
		Date bdate = new Date(System.currentTimeMillis());
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 判断是否是int
	 */
	public static boolean isInteger(String num) {
		try {
			Integer.parseInt(num);
		} catch (Exception e) {
			// e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 歌曲数据封装
	 * 
	 * @param daoSupport
	 * @param list
	 * @param totalCount
	 *            总数据
	 * @return
	 * @throws Exception
	 */
	public static ResponseSongs songResovleObject(DaoSupport daoSupport, List<PageData> list, int totalCount)
			throws Exception {
		ResponseSongs responseUserCollect = new ResponseSongs();
		responseUserCollect.setResponseCode(1);
		responseUserCollect.setDataType(1);
		cn.ogsu.api.responseBean.ResponseSongs.ResponseData responseData = new cn.ogsu.api.responseBean.ResponseSongs.ResponseData();
		responseData.setTotalCount(totalCount);
		List<SongInfo> listSongInfo = new ArrayList<>();
		SongInfo songInfo = null;
		SingerInfo singerInfo = null;
		String singerId = "";
		String singerName = "";
		for (int i = 0; i < list.size(); i++) {
			songInfo = new SongInfo();
			songInfo.setSongId(list.get(i).getString("id"));
			songInfo.setSongName(list.get(i).getString("song_name"));
			songInfo.setSongModel(list.get(i).getString("song_model").equals(0) ? "vod"
					: list.get(i).getString("song_model").equals(1) ? "MRS" : "MIDI");
			// songInfo.setOrderTimes(isInteger(list.get(i).getString("ordertime"))
			// ? Integer.parseInt(list.get(i).getString("ordertime")) : 0);
			songInfo.setOrderTimes(0);
			List<SingerInfo> listSinger = new ArrayList<>();
			singerName = list.get(i).getString("singer_name");
			System.err.println("singerName:" + singerName + ", songName:" + songInfo.getSongName());
			if (singerName != null && !singerName.equals("")) {
				singerInfo = new SingerInfo();
				singerInfo.setSingerId("-1");
				singerInfo.setSingerName(singerName);
				listSinger.add(singerInfo);
			} else {
				singerId = list.get(i).getString("singer_id_one");
				if (Tools.isInteger(singerId) && !singerId.equals(-1)) {
					singerInfo = new SingerInfo();
					singerInfo.setSingerId(singerId);
					singerInfo.setSingerName((String) daoSupport.findForObject("SongMapper.querySingerNameById",
							Integer.parseInt(list.get(i).getString("singer_id_one"))));
					listSinger.add(singerInfo);
				}
				singerId = list.get(i).getString("singer_id_two");
				if (Tools.isInteger(singerId) && !singerId.equals(-1)) {
					singerInfo = new SingerInfo();
					singerInfo.setSingerId(singerId);
					singerInfo.setSingerName((String) daoSupport.findForObject("SongMapper.querySingerNameById",
							Integer.parseInt(list.get(i).getString("singer_id_two"))));
					listSinger.add(singerInfo);
				}
				singerId = list.get(i).getString("singer_id_three");
				if (Tools.isInteger(singerId) && !singerId.equals(-1)) {
					singerInfo = new SingerInfo();
					singerInfo.setSingerId(singerId);
					singerInfo.setSingerName((String) daoSupport.findForObject("SongMapper.querySingerNameById",
							Integer.parseInt(list.get(i).getString("singer_id_three"))));
					listSinger.add(singerInfo);
				}
			}
			songInfo.setSingerList(listSinger);
			listSongInfo.add(songInfo);
		}
		responseData.setSongList(listSongInfo);
		responseUserCollect.setResponseData(responseData);
		return responseUserCollect;
	}

	/**
	 * 歌曲数据封装 返回List<SongInfo>
	 * 
	 * @param daoSupport
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public static List<SongInfo> songResovleObject(DaoSupport daoSupport, List<PageData> list) throws Exception {
		List<SongInfo> listSongInfo = new ArrayList<>();
		SongInfo songInfo = null;
		SingerInfo singerInfo = null;
		String singerId = "";
		String singerName = "";
		for (int i = 0; i < list.size(); i++) {
			songInfo = new SongInfo();
			songInfo.setSongId(list.get(i).getString("id"));
			songInfo.setSongName(list.get(i).getString("song_name"));
			songInfo.setSongModel(list.get(i).getString("song_model").equals(0) ? "vod"
					: list.get(i).getString("song_model").equals(1) ? "MRS" : "MIDI");
			// songInfo.setOrderTimes(isInteger(list.get(i).getString("ordertime"))
			// ? Integer.parseInt(list.get(i).getString("ordertime")) : 0);
			songInfo.setOrderTimes(0);
			List<SingerInfo> listSinger = new ArrayList<>();
			singerId = list.get(i).getString("singer_id_one");
			singerName = list.get(i).getString("singer_name");
			System.err.println("singerName:" + singerName + ", songName:" + songInfo.getSongName());
			if (singerName != null && !singerName.equals("")) {
				singerInfo = new SingerInfo();
				singerInfo.setSingerId("-1");
				singerInfo.setSingerName(singerName);
				listSinger.add(singerInfo);
			} else {
				if (Tools.isInteger(singerId) && !singerId.equals(-1)) {
					singerInfo = new SingerInfo();
					singerInfo.setSingerId(singerId);
					singerInfo.setSingerName((String) daoSupport.findForObject("SongMapper.querySingerNameById",
							Integer.parseInt(list.get(i).getString("singer_id_one"))));
					listSinger.add(singerInfo);
				}
				singerId = list.get(i).getString("singer_id_two");
				if (Tools.isInteger(singerId) && !singerId.equals(-1)) {
					singerInfo = new SingerInfo();
					singerInfo.setSingerId(singerId);
					singerInfo.setSingerName((String) daoSupport.findForObject("SongMapper.querySingerNameById",
							Integer.parseInt(list.get(i).getString("singer_id_two"))));
					listSinger.add(singerInfo);
				}
				singerId = list.get(i).getString("singer_id_three");
				if (Tools.isInteger(singerId) && !singerId.equals(-1)) {
					singerInfo = new SingerInfo();
					singerInfo.setSingerId(singerId);
					singerInfo.setSingerName((String) daoSupport.findForObject("SongMapper.querySingerNameById",
							Integer.parseInt(list.get(i).getString("singer_id_three"))));
					listSinger.add(singerInfo);
				}
			}
			songInfo.setSingerList(listSinger);
			listSongInfo.add(songInfo);
		}
		return listSongInfo;
	}

	/**
	 * 歌曲数据封装 返回List<SingerInfo>
	 * 
	 * @param daoSupport
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public static List<SingerInfo> singerResovleObject(DaoSupport daoSupport, List<PageData> list) throws Exception {
		List<SingerInfo> listSingerInfo = new ArrayList<>();
		SingerInfo singerInfo = null;
		for (int i = 0; i < list.size(); i++) {
			singerInfo = new SingerInfo();
			singerInfo.setSingerId(list.get(i).getString("id"));
			singerInfo.setSingerName(list.get(i).getString("singer_name"));
			singerInfo.setImgPath(list.get(i).getString("header_path"));
			singerInfo.setSongCount(Tools.isInteger(list.get(i).getString("song_count")) == true
					? Integer.parseInt(list.get(i).getString("song_count")) : 0);
			listSingerInfo.add(singerInfo);
		}
		return listSingerInfo;
	}

	/**
	 * 歌曲数据解析
	 * 
	 * @param responseImage
	 *            总数据
	 * @return
	 * @throws Exception
	 */
	public static String jsonResovle(Object responseImage) throws Exception {
		ReturnData<String> data = null;
		String jsonStr = GsonUtil.getInstance().toJson(responseImage);
		data = new ReturnData<>(Const.SUCCESS_FLAG, Const.SUCCESS_MSG);
		data.setData(jsonStr);
		System.err.println("jsonStr:" + jsonStr);
		String result = JSONObject.fromObject(data).toString();
		return result;
	}

	/**
	 * 判断字符串是否是英文
	 * 
	 * @param word
	 * @return
	 */
	public static boolean strIsEnglish(String word) {
		return word.matches("[a-zA-Z]+");
	}

	public static String getUTF8StringFromGBKString(String gbkStr) {
		try {
			return new String(getUTF8BytesFromGBKString(gbkStr), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new InternalError();
		}
	}

	public static byte[] getUTF8BytesFromGBKString(String gbkStr) {
		int n = gbkStr.length();
		byte[] utfBytes = new byte[3 * n];
		int k = 0;
		for (int i = 0; i < n; i++) {
			int m = gbkStr.charAt(i);
			if (m < 128 && m >= 0) {
				utfBytes[k++] = (byte) m;
				continue;
			}
			utfBytes[k++] = (byte) (0xe0 | (m >> 12));
			utfBytes[k++] = (byte) (0x80 | ((m >> 6) & 0x3f));
			utfBytes[k++] = (byte) (0x80 | (m & 0x3f));
		}
		if (k < utfBytes.length) {
			byte[] tmp = new byte[k];
			System.arraycopy(utfBytes, 0, tmp, 0, k);
			return tmp;
		}
		return utfBytes;
	}

}
