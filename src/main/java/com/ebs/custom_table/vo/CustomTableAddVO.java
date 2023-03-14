package com.ebs.custom_table.vo;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CustomTableAddVO {
    @NotEmpty(message = "表名不能为空！")
    private String name;
    private String remark;
}
