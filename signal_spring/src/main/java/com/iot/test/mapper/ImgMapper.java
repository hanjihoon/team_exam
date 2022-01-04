package com.iot.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.iot.test.vo.ImageVO;

@Mapper
public interface ImgMapper {

	@Select("select imgNo, imgName, bNo from image_file")
	public List<ImageVO> ImgList();

	@Select("select imgNo, imgName, imgId, imgType, imgSize, imgRegDate, bNo from image_file where bNo = #{bNo}")
	public List<ImageVO> selectByBno(@Param("bNo") int bNo);

	@Insert("insert into image_file(imgName, imgId, imgRegDate, imgType, imgSize, bNo) "
			+ "values(#{img.imgName}, #{img.imgId}, current_timestamp, #{img.imgType}, #{img.imgSize}, #{img.bNo})")
	public int insertImg(@Param("img") ImageVO img);

	@Delete("delete from image_file where bNo = #{bNo}")
	public int deleteImgByBNo(@Param("bNo") Integer bNo);
	
	@Delete("delete from image_file where imgId = #{imgId}")
	public int deleteImgByImgId(@Param("imgId") String imgId);
	
	@Update("update image_file set imgName = #{imgName}, bNo =#{bNo} where imgNo = #{imgNo}")
	public int updateImg(@Param("img") ImageVO img);
}
