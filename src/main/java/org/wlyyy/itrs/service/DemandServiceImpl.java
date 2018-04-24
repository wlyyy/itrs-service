package org.wlyyy.itrs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wlyyy.common.domain.BaseServicePageableRequest;
import org.wlyyy.common.domain.BaseServicePageableResponse;
import org.wlyyy.common.domain.BaseServiceResponse;
import org.wlyyy.itrs.dao.DemandRepository;
import org.wlyyy.itrs.domain.Demand;
import org.wlyyy.itrs.request.DemandQuery;

@Service
public class DemandServiceImpl implements DemandService {

    @Autowired
    private DemandRepository repo;

    @Override
    public BaseServiceResponse<Demand> getDemandById(Long id) {
        try {
            return new BaseServiceResponse<>(true, "success", repo.getById(id), null);
        } catch (RuntimeException e) {
            return new BaseServiceResponse<>(false, "查询出现异常", null, e);
        }
    }

    @Override
    public BaseServicePageableResponse<Demand> queryDemand(BaseServicePageableRequest<DemandQuery> request) {
        return null;
    }

    @Override
    public BaseServiceResponse<Integer> updateDemandInfo(Demand request) {
        return null;
    }

    @Override
    public BaseServiceResponse<Integer> deleteDemandLogically(Long id) {
        return null;
    }

    @Override
    public BaseServiceResponse<Demand> addDemand(Demand demand) {
        return null;
    }
}
