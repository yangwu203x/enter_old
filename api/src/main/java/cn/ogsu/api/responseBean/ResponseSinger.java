package cn.ogsu.api.responseBean;

import java.util.List;

import cn.ogsu.api.bean.SingerInfo;

public class ResponseSinger {

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
		private List<SingerInfo> singerList;

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "totalCount:" + totalCount + ", singerList.toString():" + getSingerList().toString();
		}

		public int getTotalCount() {
			return totalCount;
		}

		public void setTotalCount(int totalCount) {
			this.totalCount = totalCount;
		}

		public List<SingerInfo> getSingerList() {
			return singerList;
		}

		public void setSingerList(List<SingerInfo> singerList) {
			this.singerList = singerList;
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
