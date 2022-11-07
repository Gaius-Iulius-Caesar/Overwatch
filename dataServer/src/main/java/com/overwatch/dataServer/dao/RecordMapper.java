package com.overwatch.dataServer.dao;
import org.apache.ibatis.annotations.*;
import ustc.mike.overwatch.common.data.Record;


import java.util.List;

@Mapper
public interface RecordMapper {

    @Insert({
            "insert into record(ip,avg_load,time_stamp,cpu_num,status) ",
            "values(#{ip},#{avgLoad},#{timeStamp},#{cpuNum},#{status})"
    })
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insertRecord(Record record);
}
