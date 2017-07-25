package cn.ogsu.api.responseBean;

import java.util.List;

import cn.ogsu.api.bean.SingerInfo;
import cn.ogsu.api.bean.SongInfo;

public class ResponseSearchSongsAndSinger {

	private int responseCode;
	private int dataType;
	private ResponseData responseData;

	@Override
	public String toString() {
		return "responseCode:" + responseCode + ", dataType:" + dataType + ", responseData.toString():"
				+ responseData.toString();
	}

	public static class ResponseData {
		private int totalCount;
		private List<SongInfo> promptList;
		private List<SongInfo> songList;
		private List<SingerInfo> singerList;

		@Override
		public String toString() {
			return "promptList.toString():" + promptList.toString() + ", songList.toString():" + songList.toString()
					+ ", singerList.toString():" + singerList.toString();
		}

		public List<SongInfo> getSongList() {
			return songList;
		}

		public void setSongList(List<SongInfo> songList) {
			this.songList = songList;
		}

		public List<SongInfo> getPromptList() {
			return promptList;
		}

		public void setPromptList(List<SongInfo> promptList) {
			this.promptList = promptList;
		}

		public List<SingerInfo> getSingerList() {
			return singerList;
		}

		public void setSingerList(List<SingerInfo> singerList) {
			this.singerList = singerList;
		}

		public int getTotalCount() {
			return totalCount;
		}

		public void setTotalCount(int totalCount) {
			this.totalCount = totalCount;
		}
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	public ResponseData getResponseData() {
		return responseData;
	}

	public void setResponseData(ResponseData responseData) {
		this.responseData = responseData;
	}
}
