package cn.ogsu.api.requestBean;

public class RequestQueryRecommendImage {

	private String action;

	private RequestParams requestParams;
	
	public class RequestParams{
		private int date;

		public int getDate() {
			return date;
		}

		public void setDate(int date) {
			this.date = date;
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
}
