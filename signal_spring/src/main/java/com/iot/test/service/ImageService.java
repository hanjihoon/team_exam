package com.iot.test.service;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.iot.test.vo.ImageVO;

public interface ImageService {
	public List<ImageVO> ImgList();

	public List<ImageVO> selectByBno(int bNo);

	public void insertImg(List<MultipartFile> images, int bNo);

	public int deleteImg(int bNo);

	public void updateImg(List<ImageVO> imageVOList, List<Integer> imgNoList);

	public ImageVO save(MultipartFile multipartFile, int bNo);

	public String saveToFile(MultipartFile src, String id) throws IOException;

	public String getExtension(String fileName);

}
