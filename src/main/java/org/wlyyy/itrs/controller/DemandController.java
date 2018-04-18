package org.wlyyy.itrs.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.wlyyy.common.domain.BaseRestPageableResponse;
import org.wlyyy.common.domain.BaseRestResponse;
import org.wlyyy.itrs.domain.Demand;
import org.wlyyy.itrs.vo.DemandListItemVo;

@RequestMapping("demand")
public class DemandController {

    @RequestMapping("list")
    public BaseRestPageableResponse<DemandListItemVo> queryDemandList() {

        return null;
    }
}
