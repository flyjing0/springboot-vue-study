package com.wen.entity;

import lombok.Data;

import java.util.List;

@Data
public class ResultItems {

    // projectId
    private String id;
    private String text;
    private String itemId;
    private List<ResultItems> children;
    /** 导航名称右上角徽标 */
    //private Boolean dot;
    /** 是否在导航名称右上角显示小红点 */
    //private int badge;
}
