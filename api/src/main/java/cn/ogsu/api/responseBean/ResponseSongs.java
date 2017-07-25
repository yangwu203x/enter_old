package cn.ogsu.api.responseBean;

import java.util.List;

import cn.ogsu.api.bean.SongInfo;

public class ResponseSongs {

	private int responseCode;
	private int dataType;
	private ResponseData responseData;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "responseCode:" + responseCode + ", dataType:" + dataType + ", responseData.toString():"
				+ responseData.toString();
	}

	public static class ResponseData {
		private int totalCount;
		private List<SongInfo> songList;

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "totalCount:" + totalCount + ", songList.toString():" + songList.toString() + "\n"
					+ " songList.size():" + songList.size();
		}

		public int getTotalCount() {
			return totalCount;
		}

		public void setTotalCount(int totalCount) {
			this.totalCount = totalCount;
		}

		public List<SongInfo> getSongList() {
			return songList;
		}

		public void setSongList(List<SongInfo> songList) {
			this.songList = songList;
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
