package com.overwatch.dataServer.dao;
import org.apache.ibatis.annotations.*;
import ustc.mike.overwatch.common.data.Record;

import java.util.List;

@Mapper
public interface RecordMapper {

    @Insert({
            "insert into record(name,avg_load,os,time_stamp,cpu_num) ",
            "values(#{name},#{avgLoad},#{os},#{timeStamp},#{cpuNum})"
    })
//    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insertRecord(Record record);


//    List<Student> studentList = JSONObject.parseArray(jsonString, Student.class);
    @Select({
            "select name,avg_load,os,time_stamp,cpu_num ",
            "from record ",
            "where name=#{name} and time_stamp>=#{begin} and time_stamp <=#{end} "
    })
    List<Record> selectByNameAndTime(String name,String begin,String end);

    @Delete({
            "delete from record where name=#{name}"
    })
    void deleteByname(String name);

    @Select({
            "select name,avg_load,os,time_stamp,cpu_num from record where time_stamp = ",
            "select time_stamp from record order by time_stamp desc limit 0,1"
    })
    Record selectOneUpToDate(String name);

    @Select({
            "select name,avg_load,os,time_stamp,cpu_num from record"
    })
    List<Record> selectAll();

    @Select({
            "select name,avg_load,os,time_stamp,cpu_num ",
            "from record where(name,time_stamp) in (select name,max(time_stamp) from record group by name) "
    })
    List<Record> selectAllUpToDate();
}
