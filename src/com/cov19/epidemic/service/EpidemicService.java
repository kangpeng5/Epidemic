package com.cov19.epidemic.service;

import com.cov19.epidemic.bean.DailyEpidemicInfo;
import com.cov19.epidemic.bean.EpidemicDetailInfo;
import com.cov19.epidemic.bean.ProvinceInfo;

import java.util.List;

public interface EpidemicService {
    //保存当日疫情数据，返回还未录入数据的省份列表
    List<ProvinceInfo> saveData(DailyEpidemicInfo dailyEpidemicInfo, Integer userId);

    List<EpidemicDetailInfo> findLatestData();
}
