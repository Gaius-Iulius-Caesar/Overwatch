package com.overwatch.dataServer.dao;

import org.apache.ibatis.annotations.*;
import ustc.mike.overwatch.common.data.Record;
import ustc.mike.overwatch.common.data.Result;

import java.util.List;
@Mapper
public interface ResultMapper {



    @Select({
            "select c.ip,c.name,avg_load,time_stamp,cpu_num ",
            "from record as r ,client as c ",
            "where c.ip=r.ip and c.name=#{name} and time_stamp>=#{begin} and time_stamp <=#{end} "
    })
    List<Result> selectByNameAndTime(String name, String begin, String end);


    @Select({
            "select c.ip,c.name,avg_load,time_stamp,cpu_num ",
            "from record as r,client as c ",
            "where  c.ip=r.ip and time_stamp = select time_stamp from record order by time_stamp desc limit 0,1"
    })
    Result selectOneUpToDate(String name);

    @Select({
            "select c.ip,c.name,avg_load,time_stamp,cpu_num ",
            "from record as r,client as c ",
            "where c.ip=r.ip"
    })
    List<Result> selectAll();

    @Select({
            "select c.ip,c.name,avg_load,time_stamp,cpu_num ",
            "from record as r,client as c ",
            "where c.ip=r.ip and (r.ip,time_stamp) in (select ip,max(time_stamp) from record group by ip) "
    })
    List<Result> selectAllUpToDate();

}
