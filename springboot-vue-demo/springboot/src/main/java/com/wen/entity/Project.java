package com.wen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@TableName("project")//mybatis-plus
@Data
public class Project {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String projectId;
    private String projectName;
    private String validFlag;

}
