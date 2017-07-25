package cn.ogsu.api.requestBean;

public class RequestSongsA_Z {

	private String action;

	private RequestParams requestParams;

	public static class RequestParams {
		private int songType;
		private String spell;
		private int dataNum;
		private int page;

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
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "songType:" + songType + ", spell:" + spell + ", dataNum:" + dataNum + ", page:" + page;
		}

		public int getSongType() {
			return songType;
		}

		public void setSongType(int songType) {
			this.songType = songType;
		}

		public String getSpell() {
			return spell;
		}

		public void setSpell(String spell) {
			this.spell = spell;
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
