
package com.iot.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.iot.test.vo.BoardExposeVO;

@Mapper
public interface BoardExposeMapper {
	@Select("select uiNo from board_expose_uino where bNo=#{bNo}")
	List<Integer> exposeUiIdList(@Param("bNo") int bNo);

	@Insert("insert into board_expose_uino (uiNo,bNo) values(#{brv.uiNo},#{brv.bNo})")
	int insertExpose(@Param("bev") BoardExposeVO bev);

}
