package com.cov19.epidemic.service;

import com.cov19.epidemic.bean.ProvinceInfo;

import java.util.List;

//访问省份接口
public interface ProvinceService {
    //获取还未录入数据的省份的列表
    List<ProvinceInfo> findNoDataProvinces(String date);
}
