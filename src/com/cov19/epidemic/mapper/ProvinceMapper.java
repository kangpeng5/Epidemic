package com.cov19.epidemic.mapper;

import com.cov19.epidemic.bean.ProvinceInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//操作省份
@Mapper
public interface ProvinceMapper {
    //根据日期查询对应省份信息，通过疫情信息验证
    @Select(value = "SELECT p.province_id,p.province_name,p.province_py FROM provinces p " +
            "WHERE (p.del_flag IS NULL OR p.del_flag = 0)" +
            "AND p.province_id NOT IN (" +
            "    SELECT e.province_id FROM epidemics e" +
            "    WHERE e.data_year = #{arg0} AND e.data_month = #{arg1} AND e.data_day = #{arg2}" +
            ")ORDER BY p.province_id LIMIT 0,6")
    List<ProvinceInfo> findNoDataProvinces(short year,short month,short day);
}
