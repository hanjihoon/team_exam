package com.iot.test.vo;

import javax.inject.Named;

@Named
public class BoardHitVO {
	private Integer hNo;
	private String hSessionId;
	private Integer bNo;
	public Integer gethNo() {
		return hNo;
	}
	public void sethNo(Integer hNo) {
		this.hNo = hNo;
	}
	public String gethSessionId() {
		return hSessionId;
	}
	public void sethSessionId(String hSessionId) {
		this.hSessionId = hSessionId;
	}
	public Integer getbNo() {
		return bNo;
	}
	public void setbNo(Integer bNo) {
		this.bNo = bNo;
	}
	@Override
	public String toString() {
		return "BoardHitVO [hNo=" + hNo + ", hSessionId=" + hSessionId + ", bNo=" + bNo + "]";
	}
	
}
