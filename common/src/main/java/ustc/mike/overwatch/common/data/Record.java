package ustc.mike.overwatch.common.data;

import com.alibaba.fastjson.JSON;
import org.springframework.context.annotation.Bean;

public class Record extends Data {


        private int id;
        private String ip;

        private Double avgLoad;

        private Long timeStamp;
        private Integer cpuNum;

        public Record() { }

        public Record(String ip, Double avgload, Long timestamp, Integer cpunum) {
            this.ip = ip;
            this.avgLoad = avgload;
            this.timeStamp = timestamp;
            this.cpuNum = cpunum;
        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Double getAvgLoad() {
        return avgLoad;
    }

    public void setAvgLoad(Double avgLoad) {
        this.avgLoad = avgLoad;
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
