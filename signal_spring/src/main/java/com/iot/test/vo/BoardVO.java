package com.iot.test.vo;

import javax.inject.Named;

@Named
public class BoardVO {
	private int bNo;
	private String bName;
	private String bContent;
	private String bRegDate;
	private String uiNickName;
	private int bHit;
	private int bRecom;
	//게시물의 댓글 총 수
	private int bCommentCount;

	public int getbNo() {
		return bNo;
	}

	public void setbNo(int bNo) {
		this.bNo = bNo;
	}

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public String getbContent() {
		return bContent;
	}

	public void setbContent(String bContent) {
		this.bContent = bContent;
	}

	public String getbRegDate() {
		return bRegDate;
	}

	public void setbRegDate(String bRegDate) {
		this.bRegDate = bRegDate;
	}

	public String getUiNickName() {
		return uiNickName;
	}

	public void setUiNickName(String uiNickName) {
		this.uiNickName = uiNickName;
	}

	public int getbHit() {
		return bHit;
	}

	public void setbHit(int bHit) {
		this.bHit = bHit;
	}

	public int getbRecom() {
		return bRecom;
	}

	public void setbRecom(int bRecom) {
		this.bRecom = bRecom;
	}

	public int getbCommentCount() {
		return bCommentCount;
	}

	public void setbCommentCount(int bCommentCount) {
		this.bCommentCount = bCommentCount;
	}

	@Override
	public String toString() {
		return "BoardVO [bNo=" + bNo + ", bName=" + bName + ", bContent=" + bContent + ", bRegDate=" + bRegDate
				+ ", uiNickName=" + uiNickName + ", bHit=" + bHit + ", bRecom=" + bRecom + ", bCommentCount="
				+ bCommentCount + "]";
	}

}
