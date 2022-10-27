package com.ustc.overwatch;

public class Client extends Data {
    private boolean isOnline;
    private String  ip;
    private String  name;

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (isOnline != client.isOnline) return false;
        if (ip != null ? !ip.equals(client.ip) : client.ip != null) return false;
        return name != null ? name.equals(client.name) : client.name == null;
    }

    @Override
    public int hashCode() {
        int result = (isOnline ? 1 : 0);
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
