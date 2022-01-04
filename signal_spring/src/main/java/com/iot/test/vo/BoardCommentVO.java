package com.iot.test.vo;

import javax.inject.Named;

@Named
public class BoardCommentVO {
	private Integer bcNo;
	private String bcText;
	private String uiNickName;
	private Integer bNo;
	private String bcRegDate;
	private String iconName;

	public String getIconName() {
		return iconName;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
	}

	public Integer getBcNo() {
		return bcNo;
	}

	public void setBcNo(Integer bcNo) {
		this.bcNo = bcNo;
	}

	public String getBcText() {
		return bcText;
	}

	public void setBcText(String bcText) {
		this.bcText = bcText;
	}

	public String getUiNickName() {
		return uiNickName;
	}

	public void setUiNickName(String uiNickName) {
		this.uiNickName = uiNickName;
	}

	public Integer getbNo() {
		return bNo;
	}

	public void setbNo(Integer bNo) {
		this.bNo = bNo;
	}

	public String getBcRegDate() {
		return bcRegDate;
	}

	public void setBcRegDate(String bcRegDate) {
		this.bcRegDate = bcRegDate;
	}

	@Override
	public String toString() {
		return "BoardCommentVO [bcNo=" + bcNo + ", bcText=" + bcText + ", uiNickName=" + uiNickName + ", bNo=" + bNo
				+ ", bcRegDate=" + bcRegDate + ", iconName=" + iconName + "]";
	}

}
