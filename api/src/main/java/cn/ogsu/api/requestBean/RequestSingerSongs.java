package cn.ogsu.api.requestBean;

public class RequestSingerSongs {

	private String action;
	private RequestParams requestParams;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public RequestParams getRequestParams() {
		return requestParams;
	}

	public void setRequestParams(RequestParams requestParams) {
		this.requestParams = requestParams;
	}

	public static class RequestParams {
		private int singerId;
		private int dataNum;
		private int page;

		public void initPage(){
			page = page * dataNum;
		}
		
		public int getSingerId() {
			return singerId;
		}

		public void setSingerId(int singerId) {
			this.singerId = singerId;
		}

		public int getDataNum() {
			return dataNum;
		}

		public void setDataNum(int dataNum) {
			this.dataNum = dataNum;
		}

		public int getPage() {
			return page;
		}

		public void setPage(int page) {
			this.page = page;
		}

	}

}
