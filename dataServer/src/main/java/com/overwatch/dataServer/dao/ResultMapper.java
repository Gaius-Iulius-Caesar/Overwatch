package com.overwatch.dataServer.dao;

import org.apache.ibatis.annotations.*;
import ustc.mike.overwatch.common.data.Record;
import ustc.mike.overwatch.common.data.Result;

import java.util.List;
@Mapper
public interface ResultMapper {



    @Select({
            "select ip,name,avg_load,time_stamp,cpu_num ",
            "from record as r ,client as c ",
            "where c.ip=r.ip and c.name=#{name} and time_stamp>=#{begin} and time_stamp <=#{end} "
    })
    @Results(id="resultMap", value= {
            @org.apache.ibatis.annotations.Result(column = "ip", property = "ip"),
            @org.apache.ibatis.annotations.Result(column = "name", property = "name"),
            @org.apache.ibatis.annotations.Result(column = "avg_load", property = "avgLoad"),
            @org.apache.ibatis.annotations.Result(column = "time_stamp", property = "timeStamp"),
            @org.apache.ibatis.annotations.Result(column = "cpu_num", property = "cpuNum"),
    })
    List<Result> selectByNameAndTime(String name, String begin, String end);


//    @Select({
//            "select c.ip,c.name,avg_load,time_stamp,cpu_num ",
//            "from record as r,client as c ",
//            "where  c.ip=r.ip and time_stamp = select time_stamp from record order by time_stamp desc limit 0,1"
//    })
//    @ResultMap("resultMap")
//    Result selectOneUpToDate(String name);

    @Select({
            "select c.ip,c.name,avg_load,time_stamp,cpu_num ",
            "from record as r,client as c ",
            "where c.ip=r.ip"
    })
    @ResultMap("resultMap")
    List<Result> selectAll();

    @Select({
            "select c.ip,c.name,avg_load,time_stamp,cpu_num ",
            "from record as r,client as c ",
            "where c.ip=r.ip and (r.ip,time_stamp) in (select ip,max(time_stamp) from record group by ip) "
    })
    @ResultMap("resultMap")
    List<Result> selectAllUpToDate();

}
