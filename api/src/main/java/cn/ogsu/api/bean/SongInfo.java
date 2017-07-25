package cn.ogsu.api.bean;

import java.util.List;

public class SongInfo {

	private String songId;
	private String songName;
	private String songModel;
	private int orderTimes;
	private List<SingerInfo> singerList;

	@Override
	public String toString() {
		return "songId:" + songId + ", songName:" + songName + ", songModel:" + songModel + ", orderTimes:" + orderTimes
				+ ", singerList.toString():" + singerList.toString();
	}

	public String getSongId() {
		return songId;
	}

	public void setSongId(String songId) {
		this.songId = songId;
	}

	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}

	public String getSongModel() {
		return songModel;
	}

	public void setSongModel(String songModel) {
		this.songModel = songModel;
	}

	public List<SingerInfo> getSingerList() {
		return singerList;
	}

	public void setSingerList(List<SingerInfo> singerList) {
		this.singerList = singerList;
	}

	public int getOrderTimes() {
		return orderTimes;
	}

	public void setOrderTimes(int orderTimes) {
		this.orderTimes = orderTimes;
	}

}
