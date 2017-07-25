package cn.ogsu.api.responseBean;

import java.util.List;

public class ResponseRecommendImage {

	private int responseCode;
	private int dataType;
	private ResponseData responseData;
	
	public static class ResponseData{
		private int update;
		private int date;
		private List<ImageList> imageList;
		
		
		public static class ImageList{
			private String imageUrl;
			private int imageType;
			private String searchCondition;
			private String httpUrl;
			public String getImageUrl() {
				return imageUrl;
			}
			public void setImageUrl(String imageUrl) {
				this.imageUrl = imageUrl;
			}
			public int getImageType() {
				return imageType;
			}
			public void setImageType(int imageType) {
				this.imageType = imageType;
			}
			public String getSearchCondition() {
				return searchCondition;
			}
			public void setSearchCondition(String searchCondition) {
				this.searchCondition = searchCondition;
			}
			public String getHttpUrl() {
				return httpUrl;
			}
			public void setHttpUrl(String httpUrl) {
				this.httpUrl = httpUrl;
			}
		}


		public int getUpdate() {
			return update;
		}


		public void setUpdate(int update) {
			this.update = update;
		}


		public int getDate() {
			return date;
		}


		public void setDate(int date) {
			this.date = date;
		}


		public List<ImageList> getImageList() {
			return imageList;
		}


		public void setImageList(List<ImageList> imageList) {
			this.imageList = imageList;
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
