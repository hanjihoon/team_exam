package com.iot.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.iot.test.vo.BoardHitVO;

@Mapper
public interface BoardHitMapper {
	@Select("select hSessionId from board_hit_session where bNo=#{bNo}")
	List<String> hitSessionIdList(@Param("bNo") int bNo);

	@Insert("insert into board_hit_session (hSessionId,bNo) values(#{bhv.hSessionId},#{bhv.bNo})")
	int insertHit(@Param("bhv") BoardHitVO bhv);

}