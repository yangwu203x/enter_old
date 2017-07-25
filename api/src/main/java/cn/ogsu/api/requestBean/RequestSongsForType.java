package cn.ogsu.api.requestBean;

public class RequestSongsForType {

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

	@Override
	public String toString() {
		return "action:" + action + ", requestParams:" + requestParams.toString();
	}

	public static class RequestParams {
		private int sourceId;
		private int field_id;
		private int screen_id;
		private int dataNum;
		private int page;

		@Override
		public String toString() {
			return ", sourceId:" + sourceId + ", field_id:" + field_id + ", screen_id:" + screen_id + ", dataNum:"
					+ dataNum + ", page:" + page;
		}

		public int getSourceId() {
			return sourceId;
		}

		public void setSourceId(int sourceId) {
			this.sourceId = sourceId;
		}

		public int getField_id() {
			return field_id;
		}

		public void setField_id(int field_id) {
			this.field_id = field_id;
		}

		public int getScreen_id() {
			return screen_id;
		}

		public void setScreen_id(int screen_id) {
			this.screen_id = screen_id;
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

		public void initPage() {
			this.page = page * dataNum;
		}
	}

}
