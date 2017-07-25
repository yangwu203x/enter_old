package cn.ogsu.api.requestBean;

public class RequestSearchSongsAndSinger {

	private String action;

	private RequestParams requestParams;

	public static class RequestParams {
		private String content;
		private int dataNum;
		private int page;

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "content:" + content;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
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
		
		public void initPage(){
			this.page = page * dataNum;
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
