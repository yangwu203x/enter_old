package cn.ogsu.api.requestBean;

public class RequestSongsFeedBack {

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
		private int userId = -1;
		private String songName = "";
		private String singerName = "";
		private String songLang = "";
		private String feedBackContent = "";

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public String getSongName() {
			return songName;
		}

		public void setSongName(String songName) {
			this.songName = songName;
		}

		public String getSingerName() {
			return singerName;
		}

		public void setSingerName(String singerName) {
			this.singerName = singerName;
		}

		public String getSongLang() {
			return songLang;
		}

		public void setSongLang(String songLang) {
			this.songLang = songLang;
		}

		public String getFeedBackContent() {
			return feedBackContent;
		}

		public void setFeedBackContent(String feedBackContent) {
			this.feedBackContent = feedBackContent;
		}

	}

}
