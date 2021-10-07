package com.cov19.epidemic.service.impl;

import com.cov19.epidemic.bean.DailyEpidemicInfo;
import com.cov19.epidemic.bean.EpidemicDetailInfo;
import com.cov19.epidemic.bean.EpidemicInfo;
import com.cov19.epidemic.bean.ProvinceInfo;
import com.cov19.epidemic.mapper.EpidemicMapper;
import com.cov19.epidemic.mapper.ProvinceMapper;
import com.cov19.epidemic.service.EpidemicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EpidemicServiceImpl implements EpidemicService {
    @Autowired
    private EpidemicMapper epidemicMapper;
    @Autowired
    private ProvinceMapper provinceMapper;
    @Override
    public List<ProvinceInfo> saveData(DailyEpidemicInfo dailyEpidemicInfo, Integer userId) {
        //获取当前数据，跟系统时间一样
        Date current = new Date();
        //数据的日期
        String[] ymd = dailyEpidemicInfo.getDate().split("-");
        short year = 0, month = 0, day = 0;
        year = Short.parseShort(ymd[0]);
        month = Short.parseShort(ymd[1]);
        day = Short.parseShort(ymd[2]);
        //遍历疫情信息
        for (EpidemicInfo epidemicInfo: dailyEpidemicInfo.getArray()){
            //设置录入该数据的用户编号
            epidemicInfo.setUserId(userId);
            //设置录入数据日期
            epidemicInfo.setInputDate(current);
            //设置数据对应日期
            epidemicInfo.setDataYear(year);
            epidemicInfo.setDataMonth(month);
            epidemicInfo.setDataDay(day);
            epidemicMapper.saveInfo(epidemicInfo);
        }
        return provinceMapper.findNoDataProvinces(year,month,day);
    }

    @Override
    public List<EpidemicDetailInfo> findLatestData() {
        //查询每个省的累计数量和新增数量
        Calendar calendar = new GregorianCalendar();//获取当前日期
        short year = 0, month = 0, day = 0;
        year = (short) calendar.get(calendar.YEAR);
        month = (short)(calendar.get(calendar.MONTH)+1);
        day = (short) calendar.get(calendar.DATE);

        Map<String,Short> condition = new HashMap<>();
        condition.put("year",year);
        condition.put("month",month);
        condition.put("day",day);
        //查询每个省份累计数量和当日新增数量
        return epidemicMapper.findLatestData(condition);
    }
}
