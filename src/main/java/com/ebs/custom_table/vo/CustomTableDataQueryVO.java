package com.ebs.custom_table.vo;

import com.ebs.custom_table.dto.PageQueryDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CustomTableDataQueryVO extends PageQueryDTO {
    @NotNull(message = "表id不能为空")
    private Integer tableId;// 表id
    private Integer columnId;// 字段id，用于查询
    private String queryType;// 查询类型，类型必须满足QueryType枚举类的内容。eq、like、ne、gt、ge、lt、le
    private String queryParam;// 查询参数
}
