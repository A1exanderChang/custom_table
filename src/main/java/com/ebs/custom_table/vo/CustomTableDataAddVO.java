package com.ebs.custom_table.vo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CustomTableDataAddVO {
    @NotNull(message = "所属字段不能为空")
    private Integer columnInfo;
    @NotEmpty(message = "值不能未为空")
    private String value;
}
