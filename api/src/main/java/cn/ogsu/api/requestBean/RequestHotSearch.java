package cn.ogsu.api.requestBean;

public class RequestHotSearch {

	private String action;

	private RequestParams requestParams;

	public static class RequestParams {
		private int dataNum;

		public int getDataNum() {
			return dataNum;
		}

		public void setDataNum(int dataNum) {
			this.dataNum = dataNum;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "dataNum:" + dataNum ;
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
