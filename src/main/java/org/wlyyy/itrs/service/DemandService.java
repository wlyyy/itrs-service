package org.wlyyy.itrs.service;

import org.wlyyy.common.domain.BaseServicePageableRequest;
import org.wlyyy.common.domain.BaseServicePageableResponse;
import org.wlyyy.common.domain.BaseServiceResponse;
import org.wlyyy.itrs.domain.Demand;
import org.wlyyy.itrs.request.DemandQuery;

/**
 * 需求服务，提供基本查询增加删除功能以及状态修改接口。
 */
public interface DemandService {

    /**
     * 根据id获取需求
     *
     * @param id 需求id
     * @return 需求
     */
    BaseServiceResponse<Demand> getDemandById(Long id);

    /**
     * 分页查询需求
     *
     * @param request 查询请求
     * @return 分页结果
     */
    BaseServicePageableResponse<Demand> queryDemand(BaseServicePageableRequest<DemandQuery> request);

    /**
     * 更新需求状态
     * @param request 请求对象，id必传，其余至少传一项
     * @return 更新结果，返回成功条数（1或0）
     */
    BaseServiceResponse<Integer> updateDemandInfo(Demand request);

    /**
     * 逻辑删除需求
     *
     * @param id 需求id
     * @return 删除行书
     */
    BaseServiceResponse<Integer> deleteDemandLogically(Long id);

    /**
     * 新增招聘需求
     *
     * @param demand 招聘需求对象，无需id、gmt*
     * @return 带有id的招聘需求对象
     */
    BaseServiceResponse<Demand> addDemand(Demand demand);
}
