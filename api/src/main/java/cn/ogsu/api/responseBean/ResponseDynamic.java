package cn.ogsu.api.responseBean;

import java.util.List;

public class ResponseDynamic {

	private List<Dynamic> dynamicList;
	
	
	public List<Dynamic> getDynamicList() {
		return dynamicList;
	}


	public void setDynamicList(List<Dynamic> dynamicList) {
		this.dynamicList = dynamicList;
	}


	public static class Dynamic{
		private int userId;
		private String userName;
		private int isVip;
		private int isMaster;
		private int isInstruments;
		private String userImgPath;
		private int dynamicId;
		private String dynamicWorld;
		private String dynamicFilePath;
		private int dynamicType;
		private String dynamicImgPath;
		private String dynamicDate;
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public int getIsVip() {
			return isVip;
		}
		public void setIsVip(int isVip) {
			this.isVip = isVip;
		}
		public int getIsMaster() {
			return isMaster;
		}
		public void setIsMaster(int isMaster) {
			this.isMaster = isMaster;
		}
		public int getIsInstruments() {
			return isInstruments;
		}
		public void setIsInstruments(int isInstruments) {
			this.isInstruments = isInstruments;
		}
		public String getUserImgPath() {
			return userImgPath;
		}
		public void setUserImgPath(String userImgPath) {
			this.userImgPath = userImgPath;
		}
		public int getDynamicId() {
			return dynamicId;
		}
		public void setDynamicId(int dynamicId) {
			this.dynamicId = dynamicId;
		}
		public String getDynamicWorld() {
			return dynamicWorld;
		}
		public void setDynamicWorld(String dynamicWorld) {
			this.dynamicWorld = dynamicWorld;
		}
		public String getDynamicFilePath() {
			return dynamicFilePath;
		}
		public void setDynamicFilePath(String dynamicFilePath) {
			this.dynamicFilePath = dynamicFilePath;
		}
		public int getDynamicType() {
			return dynamicType;
		}
		public void setDynamicType(int dynamicType) {
			this.dynamicType = dynamicType;
		}
		public String getDynamicImgPath() {
			return dynamicImgPath;
		}
		public void setDynamicImgPath(String dynamicImgPath) {
			this.dynamicImgPath = dynamicImgPath;
		}
		public String getDynamicDate() {
			return dynamicDate;
		}
		public void setDynamicDate(String dynamicDate) {
			this.dynamicDate = dynamicDate;
		}
		
	}
	
}
