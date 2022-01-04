
package com.iot.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.iot.test.vo.BoardRecommandVO;

@Mapper
public interface BoardRecommandMapper {
	@Select("select uiNo from board_recommand_uino where bNo=#{bNo}")
	List<Integer> recomUiIdList(@Param("bNo") int bNo);

	@Insert("insert into board_recommand_uino (uiNo,bNo) values(#{brv.uiNo},#{brv.bNo})")
	int insertRecom(@Param("brv") BoardRecommandVO brv);
}
