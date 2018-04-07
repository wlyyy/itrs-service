package org.abomb4.itrs.dao;

import org.abomb4.itrs.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 买票e
 *
 * @author abomb4
 */
@Repository
@Mapper
public interface TextMapper {

    @Select("select * from user where id = #{id}")
    User getById(@Param("id") Long id);
}
