package org.wlyyy.itrs.dao;

import org.apache.ibatis.annotations.Select;
import org.wlyyy.itrs.domain.Demand;

public interface DemandRepository {

    @Select("select * from demand where id = #{id}")
    Demand getById(Long id);
}
