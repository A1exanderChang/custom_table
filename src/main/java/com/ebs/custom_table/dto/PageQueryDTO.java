package com.ebs.custom_table.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页查询的DTO
 *
 * @author Alexander Chang
 * @date 2022-11-2 15:44:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageQueryDTO {
    // 默认查询第 1 页
    private int pagenum = 1;
    // 默认每页 20 条记录
    private int pagesize = 20;
}
