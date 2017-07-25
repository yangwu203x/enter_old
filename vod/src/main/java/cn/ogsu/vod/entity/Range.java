package cn.ogsu.vod.entity;
public class Range {
	/**
	 * 开始处
	 */
	private long from;
	/**
	 * 结束处
	 */
	private long to;
	/**
	 * 已上传大小
	 */
	private long size;
	public Range(long from, long to, long size) {
		this.from = from;
		this.to = to;
		this.size = size;
	}
	public long getFrom() {
		return this.from;
	}
	public void setFrom(long from) {
		this.from = from;
	}
	public long getTo() {
		return this.to;
	}
	public void setTo(long to) {
		this.to = to;
	}
	public long getSize() {
		return this.size;
	}
	public void setSize(long size) {
		this.size = size;
	}
}