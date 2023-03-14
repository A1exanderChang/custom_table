package com.ebs.custom_table.controller;

import com.ebs.custom_table.result.ResultResponse;
import com.ebs.custom_table.service.CustomTableService;
import com.ebs.custom_table.vo.ColumnInfoAddVO;
import com.ebs.custom_table.vo.CustomTableAddVO;
import com.ebs.custom_table.vo.CustomTableDataAddVO;
import com.ebs.custom_table.vo.CustomTableDataQueryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
@RequestMapping("custom_table")
public class CustomTableController {
    @Autowired
    private CustomTableService customTableService;

    /************
     * 自定义表
     ************/
    /**
     * 添加自定义表
     *
     * @param customTableAddVO
     * @return
     */
    @PostMapping("table/add")
    public ResultResponse tableAdd(@Validated @RequestBody CustomTableAddVO customTableAddVO) {
        return customTableService.tableAdd(customTableAddVO);
    }

    /************
     * 表字段
     ************/
    /**
     * 向指定表添加字段
     *
     * @param columnInfoAddVO
     * @return
     */
    @PostMapping("column/add")
    public ResultResponse columnAdd(@Validated @RequestBody ColumnInfoAddVO columnInfoAddVO) {
        return customTableService.columnAdd(columnInfoAddVO);
    }

    /************
     * 表数据
     ************/
    /**
     * 向指定表中插入数据
     *
     * @param customTableDataAddVOList
     * @return
     */
    @PostMapping("data/add")
    public ResultResponse dataAdd(@Validated @RequestBody List<CustomTableDataAddVO> customTableDataAddVOList) {
        return customTableService.dataAdd(customTableDataAddVOList);
    }

    /**
     * 查询指定表的数据
     *
     * @param customTableDataQueryVO
     * @return
     */
    @PostMapping("data/query")
    public ResultResponse dataQuery(@Validated @RequestBody CustomTableDataQueryVO customTableDataQueryVO) throws InvocationTargetException, IllegalAccessException {
        return customTableService.dataQuery(customTableDataQueryVO);
    }
}
