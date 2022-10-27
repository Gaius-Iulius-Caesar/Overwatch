package ustc.mike.overwatch.common.data;

import com.alibaba.fastjson.JSON;

public class Record extends Data {

        private int id;


        private String name;


        private Double avgLoad;


        private String os;


        private Long timeStamp;

        private Integer cpuNum;


        public Record() { }

        public Record(String name, Double avgload, String os, Long timestamp, Integer cpunum) {
            this.name = name;
            this.avgLoad = avgload;
            this.os = os;
            this.timeStamp = timestamp;
            this.cpuNum = cpunum;
        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAvgLoad() {
        return avgLoad;
    }

    public void setAvgLoad(Double avgLoad) {
        this.avgLoad = avgLoad;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Integer getCpuNum() {
        return cpuNum;
    }

    public void setCpuNum(Integer cpuNum) {
        this.cpuNum = cpuNum;
    }


}
