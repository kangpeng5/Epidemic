package com.cov19.epidemic.mapper;

import com.cov19.epidemic.bean.EpidemicDetailInfo;
import com.cov19.epidemic.bean.EpidemicInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface EpidemicMapper {
    @Insert(value = "INSERT INTO epidemics(province_id,data_year,data_month,data_day," +
            "affirmed,suspected,isolated,dead,cured,user_id,input_date)" +
            "VALUES(#{provinceId},#{dataYear},#{dataMonth},#{dataDay}," +
            "#{affirmed},#{suspected},#{isolated},#{dead},#{cured},#{userId},#{inputDate})")
    int saveInfo(EpidemicInfo epidemicInfo);
    //根据日期查询疫情信息
    @Select(value = "SELECT e1.province_id,temp.province_name,e1.data_year,e1.data_month,e1.data_day," +
            "               temp.affirmed_total,temp.suspected_total,temp.isolated_total,temp.cured_total,temp.dead_total" +
            "               FROM epidemics e1 RIGHT OUTER JOIN(" +
            "                  SELECT e. province_id,p.province_name,SUM(e.affirmed) affirmed_total, " +
            "                 SUM(e.suspected) suspected_total,SUM(e.isolated) isolated_total, " +
            "                  SUM(e.cured) cured_total,SUM(e.dead) dead_total" +
            "                  FROM epidemics e RIGHT OUTER JOIN provinces p ON e.province_id = p.province_id" +
            "                  GROUP BY e.province_id,p.province_name" +
            "               ) temp ON e1.province_id = temp.province_id" +
            "              WHERE e1.data_year=#{year} AND e1.data_month =#{month} AND e1.data_day =#{day}")
    List<EpidemicDetailInfo> findLatestData(Map<String,Short> condition);
}

