package com.len.enume;

//<option value="1">范围风险</option><option value="2">质量风险</option>
//<option value="3">进度风险</option><option value="4">成本风险</option>
//<option value="5">技术风险</option><option value="6">管理风险</option>
//<option value="7">商业风险</option><option value="8">法律风险</option>


//@Getter
public enum RiskTypeEnum {
    TYPE1(1, "范围风险"),
    TYPE2(2, "质量风险"),
    TYPE3(3, "进度风险"),
    TYPE4(4, "成本风险"),
    TYPE5(5, "技术风险"),
    TYPE6(6, "管理风险"),
    TYPE7(7, "商业风险"),
    TYPE8(8, "法律风险"),
    TYPE9(9, "其他风险");

    private Integer code;

    private String message;

    RiskTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static RiskTypeEnum getValue(int code) {
        for (RiskTypeEnum type : values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return TYPE9;
    }
}
