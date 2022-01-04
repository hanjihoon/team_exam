package com.iot.test.vo;

public class BoardExposeVO {
	private Integer eNo;
	private Integer bNo;
	private Integer uiNo;
	public Integer geteNo() {
		return eNo;
	}
	public void seteNo(Integer eNo) {
		this.eNo = eNo;
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
		return "BoardExposeVO [eNo=" + eNo + ", bNo=" + bNo + ", uiNo=" + uiNo + "]";
	}
}
