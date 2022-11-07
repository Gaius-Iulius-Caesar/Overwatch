package com.overwatch.dataServer.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import ustc.mike.overwatch.common.data.Client;

@Mapper
public interface ClientMapper {

    @Insert({
          "insert into client(ip,name) values(#{ip},#{name})"
    })
    void insertClient(Client client);
    @Delete({
        "delete from client where ip=#{ip} and name=#{name}"
    })
    void deleteClient(Client client);
}
