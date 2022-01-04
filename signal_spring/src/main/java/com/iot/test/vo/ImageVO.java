package com.iot.test.vo;

public class ImageVO {
	private Integer imgNo;
	private String imgName;
	private String imgId;
	private String imgType;
	private int imgSize;
	private char imgRegDate;
	private Integer bNo;

	public Integer getImgNo() {
		return imgNo;
	}

	public void setImgNo(Integer imgNo) {
		this.imgNo = imgNo;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public String getImgId() {
		return imgId;
	}

	public void setImgId(String imgId) {
		this.imgId = imgId;
	}

	public String getImgType() {
		return imgType;
	}

	public void setImgType(String imgType) {
		this.imgType = imgType;
	}

	public int getImgSize() {
		return imgSize;
	}

	public void setImgSize(int imgSize) {
		this.imgSize = imgSize;
	}

	public char getImgRegDate() {
		return imgRegDate;
	}

	public void setImgRegDate(char imgRegDate) {
		this.imgRegDate = imgRegDate;
	}

	public Integer getbNo() {
		return bNo;
	}

	public void setbNo(Integer bNo) {
		this.bNo = bNo;
	}

	@Override
	public String toString() {
		return "ImageVO [imgNo=" + imgNo + ", imgName=" + imgName + ", imgId=" + imgId + ", imgType=" + imgType
				+ ", imgSize=" + imgSize + ",  +imgRegDate=" + imgRegDate + ", bNo=" + bNo + "]";

	}
}
