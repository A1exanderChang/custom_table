package com.ebs.custom_table.vo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ColumnInfoAddVO {
    @NotNull(message = "隶属表不能为空！")
    private Integer customTable;
    @NotNull(message = "表字段类型不能为空")
    private Integer columnType;
    @NotEmpty(message = "字段名ZH不能未空")
    private String name;
    @NotEmpty(message = "字段名ZH不能未空")
    private String value;
    private String remark;
}
