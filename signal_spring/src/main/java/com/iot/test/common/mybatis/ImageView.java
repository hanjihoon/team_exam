package com.iot.test.common.mybatis;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import com.iot.test.service.impl.ImageServiceImpl;
import com.iot.test.vo.ImageVO;

@Component("imageView")
public class ImageView extends AbstractView {

	@Autowired
	ImageServiceImpl ImageServiceImpl;

	@Override
	protected void renderMergedOutputModel(Map model, HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		ImageVO ImageVO = (ImageVO) model.get("imageFile");

		// 응답 메시지에 파일의 길이를 넘겨줍니다.
		res.setContentLength(ImageVO.getImgSize());

		// 응답의 타입이 이미지임을 알려줍니다.
		res.setContentType(ImageVO.getImgType());

		// 파일로부터 byte를 읽어옵니다.
		byte[] bytes = readFile(ImageVO.getImgName() + "." + ImageVO.getImgType());
		write(res, bytes);
	}

	/**
	 * 파일로부터 byte 배열 읽어오기
	 */
	private byte[] readFile(String fileName) throws IOException {
		String path = ImageServiceImpl.IMAGE_DIR + fileName;

		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path));
		int length = bis.available();
		byte[] bytes = new byte[length];
		bis.read(bytes);
		bis.close();

		return bytes;
	}

	/**
	 * 응답 OutputStream에 파일 내용 쓰기
	 */
	private void write(HttpServletResponse res, byte[] bytes) throws IOException {
		OutputStream output = res.getOutputStream();
		output.write(bytes);
		output.flush();
	}

}
