package com.iot.test.vo;

import javax.inject.Named;

@Named
public class BoardRecommandVO {
	private Integer rNo;
	private Integer bNo;
	private Integer uiNo;
	public Integer getrNo() {
		return rNo;
	}
	public void setrNo(Integer rNo) {
		this.rNo = rNo;
	}
	public Integer getbNo() {
		return bNo;
	}
	public void setbNo(Integer bNo) {
		this.bNo = bNo;
	}
	public Integer getUiNo() {
		return uiNo;
	}
	public void setUiNo(Integer uiNo) {
		this.uiNo = uiNo;
	}
	@Override
	public String toString() {
		return "BoardRecommandVO [rNo=" + rNo + ", bNo=" + bNo + ", uiNo=" + uiNo + "]";
	}
}
