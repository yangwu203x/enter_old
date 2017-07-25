package cn.ogsu.api.responseBean;

import java.util.List;

public class ResponseSongType {

	private int responseCode;
	private int dataType;
	private ResponseData responseData;

	@Override
	public String toString() {
		return "responseCode:" + responseCode + ", dataType:" + dataType + ", responseData:" + responseData;
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

	public static class ResponseData {

		public List<SongType> getSongType() {
			return songType;
		}

		public void setSongType(List<SongType> songType) {
			this.songType = songType;
		}

		private List<SongType> songType;

		@Override
		public String toString() {
			return ", songType:" + songType.toString();
		}

		public static class SongType {
			private String typeName;
			private int sourceId;
			private int field_id;
			private int rank;
			private List<ScreenCondition> screenCondition;

			@Override
			public String toString() {
				return ", typeName:" + typeName + ", sourceId:" + sourceId + ", field_id:" + field_id
						+ ", screenCondition:" + screenCondition;
			}

			public String getTypeName() {
				return typeName;
			}

			public void setTypeName(String typeName) {
				this.typeName = typeName;
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

			public List<ScreenCondition> getScreenCondition() {
				return screenCondition;
			}

			public void setScreenCondition(List<ScreenCondition> screenCondition) {
				this.screenCondition = screenCondition;
			}

			public int getRank() {
				return rank;
			}

			public void setRank(int rank) {
				this.rank = rank;
			}

			public static class ScreenCondition {
				private String screenName;
				private int screenId;

				@Override
				public String toString() {
					return ", screenName:" + screenName + ", screenId:" + screenId;
				}

				public String getScreenName() {
					return screenName;
				}

				public void setScreenName(String screenName) {
					this.screenName = screenName;
				}

				public int getScreenId() {
					return screenId;
				}

				public void setScreenId(int screenId) {
					this.screenId = screenId;
				}
			}
		}
	}

}
