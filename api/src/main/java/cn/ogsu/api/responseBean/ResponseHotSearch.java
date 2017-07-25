package cn.ogsu.api.responseBean;

import java.util.List;

import cn.ogsu.api.bean.SongInfo;

public class ResponseHotSearch {

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

		private List<SearchContent> searchList;

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "searchList.toString():" + searchList.toString();
		}

		public static class SearchContent {
			private String searchId;
			private String searchContent;

			@Override
			public String toString() {
				return "id:" + getSearchId() + ", content" + getSearchContent();
			}

			public String getSearchId() {
				return searchId;
			}

			public void setSearchId(String searchId) {
				this.searchId = searchId;
			}

			public String getSearchContent() {
				return searchContent;
			}

			public void setSearchContent(String searchContent) {
				this.searchContent = searchContent;
			}

		}

		public List<SearchContent> getSearchList() {
			return searchList;
		}

		public void setSearchList(List<SearchContent> searchList) {
			this.searchList = searchList;
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
