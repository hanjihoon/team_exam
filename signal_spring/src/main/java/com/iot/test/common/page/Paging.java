package com.iot.test.common.page;

public class Paging {

	private int maxContent;
	private int maxPage;
	private int currentPage;
	private int maxBlock;
	private int currentBlock;
	private int restPage;

	public Paging(int maxContent) {
		this.maxContent = maxContent;
		if (maxContent % 20 != 0) {
			this.maxPage = (maxContent / 20) + 1;
		} else {
			this.maxPage = maxContent / 20;
		}
		if (maxPage % 10 != 0) {
			this.maxBlock = (maxPage / 10) + 1;
		} else {
			this.maxBlock = maxPage / 10;
		}
		restPage = maxPage % 10;
	}

	

	public int getMaxContent() {
		return maxContent;
	}



	public int getMaxPage() {
		return maxPage;
	}



	public int getCurrentPage() {
		return currentPage;
	}



	public int getMaxBlock() {
		return maxBlock;
	}



	public int getCurrentBlock() {
		return currentBlock;
	}



	public int getRestPage() {
		return restPage;
	}



	public void setMaxContent(int maxContent) {
		this.maxContent = maxContent;
	}



	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}



	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}



	public void setMaxBlock(int maxBlock) {
		this.maxBlock = maxBlock;
	}



	public void setCurrentBlock(int currentBlock) {
		this.currentBlock = currentBlock;
	}



	public void setRestPage(int restPage) {
		this.restPage = restPage;
	}



	@Override
	public String toString() {
		return "Paging [maxContent=" + maxContent + ", maxPage=" + maxPage + ", currentPage=" + currentPage
				+ ", maxBlock=" + maxBlock + ", currentBlock=" + currentBlock + ", restPage=" + restPage + "]";
	}

}
