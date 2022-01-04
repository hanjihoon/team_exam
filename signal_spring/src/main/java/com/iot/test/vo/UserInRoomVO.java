package com.iot.test.vo;

public class UserInRoomVO {
	
	private int uirNo;
	private String uiId;
	private String rName;
	
	public int getUirNo() {
		return uirNo;
	}
	public void setUirNo(int uirNo) {
		this.uirNo = uirNo;
	}
	public String getUiId() {
		return uiId;
	}
	public void setUiId(String uiId) {
		this.uiId = uiId;
	}
	
	public String getrName() {
		return rName;
	}
	public void setrName(String rName) {
		this.rName = rName;
	}
	@Override
	public String toString() {
		return "UserInRoomVO [uirNo=" + uirNo + ", uiId=" + uiId + ", rName=" + rName + "]";
	}
	

}
