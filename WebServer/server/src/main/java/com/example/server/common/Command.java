package com.example.server.common;

import java.util.HashMap;

/**
 * 通信命令的格式类
 *
 * @author Wu Jianxin
 * @version 1.00
 * @Date 2022.10.25
 */
public class Command extends Data {

    private int type;

    private HashMap<String, String> contents;


    public int getType() {
        return type;
    }


    public void setType(int type) {
        this.type = type;
    }


    public HashMap<String, String> getContents() {
        return contents;
    }


    public void setContents(HashMap<String, String> contents) {
        this.contents = contents;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Command command = (Command) o;

        if (type != command.type) return false;
        return contents != null ? contents.equals(command.contents) : command.contents == null;
    }

    @Override
    public int hashCode() {
        int result = type;
        result = 31 * result + (contents != null ? contents.hashCode() : 0);
        return result;
    }
}
