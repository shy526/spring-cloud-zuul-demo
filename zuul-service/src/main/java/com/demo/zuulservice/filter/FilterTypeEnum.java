package com.demo.zuulservice.filter;

/**
 * 过滤器类型
 * @author Administrator
 */

public enum FilterTypeEnum {


    /**
     * 过滤器类型
     */
    PRE("pre"),
    ROUTING("routing"),
    POST("post"),
    ERROR("error")
    ;
    /**
     * typeString
     */
    private String typeString;
    FilterTypeEnum(String typeString) {
        this.typeString = typeString;
    }

    public String getTypeString() {
        return typeString;
    }
}
