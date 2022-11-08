package ustc.mike.overwatch.common.data;

public class Result extends  Data{
    private String ip;
    private String name;

    private Double avgLoad;

    private Long timeStamp;
    private Integer cpuNum;
    private boolean status;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
