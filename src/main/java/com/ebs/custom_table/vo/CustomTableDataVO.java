package com.ebs.custom_table.vo;

import com.ebs.custom_table.dto.CustomTableDataColumnDTO;
import com.ebs.custom_table.entity.ColumnInfo;
import lombok.Data;

import java.util.List;

/**
 * 用于返回自定义表查询的结果
 */
@Data
public class CustomTableDataVO {
    private List<ColumnInfo> columns;
    private List<CustomTableDataColumnDTO> data;
    private long count;
    private long pagenum;
    private long pagesize;
}
