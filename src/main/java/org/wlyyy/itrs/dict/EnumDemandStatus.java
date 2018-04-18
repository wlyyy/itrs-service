package org.wlyyy.itrs.dict;

/**
 * 需求状态枚举。
 *
 * @author wly
 */
public enum EnumDemandStatus implements IDictionary<Integer> {

    NORMAL(1, "正常", "正常"),
    FULL(2, "人数已满", "人数已满"),
    DELEED(3, "已删除", "已删除"),
    ;

    private final Integer code;
    private final String name;
    private final String desc;

    EnumDemandStatus(Integer code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
