package com.ebs.custom_table.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName column_type
 */
@TableName(value = "column_type")
@Data
public class ColumnType implements Serializable {
    /**
     * 主键，无符号，自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 创建时间，必备字段。默认为当前时间，不允许更改。
     */
    @TableField(value = "gmt_create", select = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;

    /**
     * 修改时间，必备字段。ON UPDATE CURRENT_TIMESTAMP
     */
    @TableField(value = "gmt_modified", select = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;

    /**
     * 逻辑删除。0，否；非0，是。类型与该表主键保持一致
     */
    @TableField(value = "deleted", select = false)
    @TableLogic(value = "0")
    private Integer deleted;

    @TableField(exist = false, select = false)
    private static final long serialVersionUID = 1L;
}