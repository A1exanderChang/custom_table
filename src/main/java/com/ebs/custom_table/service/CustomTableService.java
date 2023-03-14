package com.ebs.custom_table.service;

import com.ebs.custom_table.result.ResultResponse;
import com.ebs.custom_table.vo.ColumnInfoAddVO;
import com.ebs.custom_table.vo.CustomTableAddVO;
import com.ebs.custom_table.vo.CustomTableDataAddVO;
import com.ebs.custom_table.vo.CustomTableDataQueryVO;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface CustomTableService {
    /**
     * 添加自定义表
     *
     * @param customTableAddVO
     * @return
     */
    ResultResponse tableAdd(CustomTableAddVO customTableAddVO);

    /**
     * 向指定表添加字段
     *
     * @param columnInfoAddVO
     * @return
     */
    ResultResponse columnAdd(ColumnInfoAddVO columnInfoAddVO);

    /**
     * 向指定表中插入数据
     *
     * @param customTableDataAddVOList
     * @return
     */
    ResultResponse dataAdd(List<CustomTableDataAddVO> customTableDataAddVOList);

    /**
     * 查询指定表的数据
     *
     * @param customTableDataQueryVO
     * @return
     */
    ResultResponse dataQuery(CustomTableDataQueryVO customTableDataQueryVO) throws InvocationTargetException, IllegalAccessException;
}
