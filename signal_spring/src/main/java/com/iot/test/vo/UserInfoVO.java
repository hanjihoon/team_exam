package com.iot.test.vo;

public class UserInfoVO {
	
	private int uiNo;
	private String uiId;
	private String uiPwd;
	private String uiNickName;
	private String uiEmail;
	private String uiRegdate;
	private String iconName;
	
	
	public String getIconName() {
		return iconName;
	}
	public void setIconName(String iconName) {
		this.iconName = iconName;
	}
	public int getUiNo() {
		return uiNo;
	}
	public void setUiNo(int uiNo) {
		this.uiNo = uiNo;
	}
	public String getUiId() {
		return uiId;
	}
	public void setUiId(String uiId) {
		this.uiId = uiId;
	}
	public String getUiPwd() {
		return uiPwd;
	}
	public void setUiPwd(String uiPwd) {
		this.uiPwd = uiPwd;
	}
	public String getUiNickName() {
		return uiNickName;
	}
	public void setUiNickName(String uiNickName) {
		this.uiNickName = uiNickName;
	}
	public String getUiEmail() {
		return uiEmail;
	}
	public void setUiEmail(String uiEmail) {
		this.uiEmail = uiEmail;
	}
	public String getUiRegdate() {
		return uiRegdate;
	}
	public void setUiRegdate(String uiRegdate) {
		this.uiRegdate = uiRegdate;
	}
	@Override
	public String toString() {
		return "UserInfoVO [uiNo=" + uiNo + ", uiId=" + uiId + ", uiPwd=" + uiPwd + ", uiNickName=" + uiNickName
				+ ", uiEmail=" + uiEmail + ", uiRegdate=" + uiRegdate + ", iconName=" + iconName + "]";
	}
	
	
	
	
}
