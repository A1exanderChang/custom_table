package com.ebs.custom_table.dto;

import lombok.Data;

/**
 * 用于封装自定义表的行数据
 */
@Data
public class CustomTableDataColumnDTO {
    private String line;// 行号，其它属性通过动态代理添加
}
