package cn.ogsu.api.bean;

public class SingerInfo {

	private String singerId;
	private String singerName;
	private String imgPath;
	private boolean isAttention;
	private int songCount;

	@Override
	public String toString() {
		return "singerId:" + singerId + ", singerName:" + singerName + ", imgPath:" + imgPath + ", isAttention:"
				+ isAttention + ", songCount:" + songCount;
	}

	public String getSingerId() {
		return singerId;
	}

	public void setSingerId(String singerId) {
		this.singerId = singerId;
	}

	public String getSingerName() {
		return singerName;
	}

	public void setSingerName(String singerName) {
		this.singerName = singerName;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public boolean isAttention() {
		return isAttention;
	}

	public void setAttention(boolean isAttention) {
		this.isAttention = isAttention;
	}

	public int getSongCount() {
		return songCount;
	}

	public void setSongCount(int songCount) {
		this.songCount = songCount;
	}

}
