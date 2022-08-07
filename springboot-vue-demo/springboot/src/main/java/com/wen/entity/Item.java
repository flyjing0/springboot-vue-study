package com.wen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@TableName("items")
@Data
public class Item {
    @TableId(type = IdType.AUTO)
    private Integer id;
    // 细目ID
    private String itemId;
    // 细目名称
    private String itemName;
    // 项目ID
    private String projectId;
    // 项目ID
    @TableField(exist = false)
    private String projectName;
    // 临床意义
    private String clinicalSignificance;
    // 标本类型
    private String specimenType;
    // 标本收集
    private String specimenCollection;
    // 报告时间
    private String reportTime;
    // 参考范围
    private String referenceRange;


}
