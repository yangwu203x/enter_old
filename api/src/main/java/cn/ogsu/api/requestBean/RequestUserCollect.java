package cn.ogsu.api.requestBean;

public class RequestUserCollect {

	private String action;

	private RequestParams requestParams;

	public static class RequestParams {
		private int userId;
		private int dataNum;
		private int page;

		public void initPage() {
			this.page = page * dataNum;
		}

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public int getDataNum() {
			return dataNum;
		}

		public void setDataNum(int dataNum) {
			this.dataNum = dataNum;
			initPage();
		}

		public int getPage() {
			return page;
		}

		public void setPage(int page) {
			this.page = page;
			initPage();
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "userId:" + userId + ",dataNum:" + dataNum + ",page:" + page;
		}

	}

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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "action:" + action + "," + requestParams.toString();
	}

}
