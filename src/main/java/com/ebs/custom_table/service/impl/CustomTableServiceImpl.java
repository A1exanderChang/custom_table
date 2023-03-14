package com.ebs.custom_table.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ebs.custom_table.dto.CustomTableDataColumnDTO;
import com.ebs.custom_table.entity.ColumnInfo;
import com.ebs.custom_table.entity.CustomTable;
import com.ebs.custom_table.entity.CustomTableData;
import com.ebs.custom_table.mapper.ColumnInfoMapper;
import com.ebs.custom_table.mapper.ColumnTypeMapper;
import com.ebs.custom_table.mapper.CustomTableDataMapper;
import com.ebs.custom_table.mapper.CustomTableMapper;
import com.ebs.custom_table.result.ResultResponse;
import com.ebs.custom_table.service.CustomTableService;
import com.ebs.custom_table.util.PropertyAppender;
import com.ebs.custom_table.vo.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Service
public class CustomTableServiceImpl implements CustomTableService {
    @Autowired
    private CustomTableMapper customTableMapper;
    @Autowired
    private ColumnInfoMapper columnInfoMapper;
    @Autowired
    private CustomTableDataMapper customTableDataMapper;
    @Autowired
    private ColumnTypeMapper columnTypeMapper;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ResultResponse tableAdd(CustomTableAddVO customTableAddVO) {
        long count = new LambdaQueryChainWrapper<>(customTableMapper)
                .eq(CustomTable::getName, customTableAddVO.getName())
                .count();
        if (count > 0) {
            return ResultResponse.error(500, "该表名以被使用！");
        }
        CustomTable customTable = new CustomTable();
        BeanUtils.copyProperties(customTableAddVO, customTable);
        if (customTableMapper.insert(customTable) == 1) {
            return ResultResponse.success("操作成功！");
        }
        return ResultResponse.error(500, "操作失败！");
    }

    @Override
    public ResultResponse columnAdd(ColumnInfoAddVO columnInfoAddVO) {
        ColumnInfo columnInfo = new ColumnInfo();
        BeanUtils.copyProperties(columnInfoAddVO, columnInfo);
        if (columnInfoMapper.insert(columnInfo) == 1) {
            return ResultResponse.success("操作成功！");
        }
        return ResultResponse.error(500, "操作失败！");
    }

    @Transactional
    @Override
    public ResultResponse dataAdd(List<CustomTableDataAddVO> customTableDataAddVOList) {
        if (customTableDataAddVOList.size() < 1) {
            return ResultResponse.error(500, "无提交的数据！");
        }
        String uuid = UUID.randomUUID().toString();
        for (CustomTableDataAddVO customTableDataAddVO : customTableDataAddVOList) {
            CustomTableData customTableData = new CustomTableData();
            customTableData.setLine(uuid);
            BeanUtils.copyProperties(customTableDataAddVO, customTableData);
            if (customTableDataMapper.insert(customTableData) != 1) {
                return ResultResponse.error(500, "操作失败！");
            }
        }
        return ResultResponse.success("操作成功！");
    }

    @Override
    public ResultResponse dataQuery(CustomTableDataQueryVO customTableDataQueryVO) throws InvocationTargetException, IllegalAccessException {
        if (StringUtils.isNotBlank(customTableDataQueryVO.getQueryType())) {
            if (StringUtils.isBlank(customTableDataQueryVO.getQueryParam())) {
                return ResultResponse.error(500, "条件查询时，需要同时提交查询类型及查询参数！");
            }
        }
        CustomTableDataVO customTableDataVO = new CustomTableDataVO();
        /**
         * 根据提交的表id查询表名
         */
        CustomTable customTable = customTableMapper.selectById(customTableDataQueryVO.getTableId());
        if (Objects.isNull(customTable)) {
            return ResultResponse.error(500, "表不存在！");
        }
        /**
         * 根据表id查询表的字段
         */
        List<ColumnInfo> columnInfoList = new LambdaQueryChainWrapper<ColumnInfo>(columnInfoMapper)
                .eq(ColumnInfo::getCustomTable, customTable.getId())
                .orderByAsc(ColumnInfo::getId, ColumnInfo::getSort)
                .list();
        List<Integer> columnInfoIdList = new ArrayList<>();
        for (ColumnInfo columnInfo : columnInfoList) {
            columnInfoIdList.add(columnInfo.getId());
        }
        customTableDataVO.setColumns(columnInfoList);
        /**
         * 根据表的字段，查询对应的内容
         */
        if (columnInfoIdList.size() > 0) {
            LambdaQueryWrapper<CustomTableData> customTableDataLambdaQueryWrapper = new LambdaQueryWrapper<>();
            if (StringUtils.isNotBlank(customTableDataQueryVO.getQueryType())
                    && !Objects.isNull(customTableDataQueryVO.getColumnId())
                    && StringUtils.isNotBlank(customTableDataQueryVO.getQueryParam())) {
                customTableDataLambdaQueryWrapper.eq(CustomTableData::getColumnInfo, customTableDataQueryVO.getColumnId());
                switch (customTableDataQueryVO.getQueryType()) {
                    case "eq":
                        customTableDataLambdaQueryWrapper.eq(CustomTableData::getValue, customTableDataQueryVO.getQueryParam());
                        break;
                    case "like":
                        customTableDataLambdaQueryWrapper.like(CustomTableData::getValue, customTableDataQueryVO.getQueryParam());
                        break;
                    case "ne":
                        customTableDataLambdaQueryWrapper.ne(CustomTableData::getValue, customTableDataQueryVO.getQueryParam());
                        break;
                    case "gt":
                        customTableDataLambdaQueryWrapper.gt(CustomTableData::getValue, customTableDataQueryVO.getQueryParam());
                        break;
                    case "ge":
                        customTableDataLambdaQueryWrapper.ge(CustomTableData::getValue, customTableDataQueryVO.getQueryParam());
                        break;
                    case "lt":
                        customTableDataLambdaQueryWrapper.lt(CustomTableData::getValue, customTableDataQueryVO.getQueryParam());
                        break;
                    case "le":
                        customTableDataLambdaQueryWrapper.le(CustomTableData::getValue, customTableDataQueryVO.getQueryParam());
                        break;
                }
            }
            // 创建分页，页码由前端提供，每页大小由前端提供的页面大小乘该表的字段个数
            Page<CustomTableData> customTableDataPage = new Page<>(customTableDataQueryVO.getPagenum(), customTableDataQueryVO.getPagesize() * columnInfoIdList.size());
            IPage<CustomTableData> customTableDataIPage = customTableDataMapper.selectPage(customTableDataPage, customTableDataLambdaQueryWrapper);
            customTableDataVO.setCount(customTableDataIPage.getTotal() / columnInfoIdList.size());
            customTableDataVO.setPagenum(customTableDataIPage.getCurrent());
            customTableDataVO.setPagesize(customTableDataIPage.getSize() / columnInfoIdList.size());
            List<CustomTableData> customTableDataList = customTableDataIPage.getRecords();

            List<HashMap<String, Object>> hashMapList = new ArrayList<>();
            for (CustomTableData customTableData : customTableDataList) {
                for (ColumnInfo columnInfo : columnInfoList) {
                    if (columnInfo.getId().equals(customTableData.getColumnInfo())) {
                        boolean flag = false;
                        hashMapListLoop:
                        for (int i = 0; i < hashMapList.size(); i++) {
                            HashMap<String, Object> stringObjectHashMap = hashMapList.get(i);
                            if (stringObjectHashMap.get("line").equals(customTableData.getLine())) {
                                // 该行已经在map集合中
                                stringObjectHashMap.put(columnInfo.getValue(), customTableData.getValue());
                                hashMapList.set(i, stringObjectHashMap);
                                // 将标示更改为true，标示已匹配到
                                flag = true;
                                break hashMapListLoop;
                            }
                        }
                        // 该行未在map集合中，直接将该行添加
                        if (!flag) {
                            HashMap<String, Object> hashMap = new HashMap<>();
                            hashMap.put("line", customTableData.getLine());
                            hashMap.put(columnInfo.getValue(), customTableData.getValue());
                            hashMapList.add(hashMap);
                        }
                    }
                }
            }
            /**
             * 将map动态代理为CustomTableDataColumnDTO对象
             */
            List<CustomTableDataColumnDTO> customTableDataColumnDTOList = new ArrayList<>();
            for (HashMap<String, Object> hashMap : hashMapList) {
                CustomTableDataColumnDTO customTableDataColumnDTO = new CustomTableDataColumnDTO();
                customTableDataColumnDTO.setLine(hashMap.get("line").toString());
                customTableDataColumnDTO = (CustomTableDataColumnDTO) PropertyAppender.generate(customTableDataColumnDTO, hashMap);
                customTableDataColumnDTOList.add(customTableDataColumnDTO);
            }
            customTableDataVO.setData(customTableDataColumnDTOList);
        }
        return ResultResponse.success(customTableDataVO);
    }
}
