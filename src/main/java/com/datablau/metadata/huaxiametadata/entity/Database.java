package com.datablau.metadata.huaxiametadata.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

@XStreamAlias("database")
public class Database implements Serializable {

    //连接名（自定义）
    @XStreamAlias("name")
    private String name;

    //数据库类型：MySQL Oracle SqlServer
    @XStreamAlias("databaseType")
    private String databaseType;

    //要连接的数据库名
    @XStreamAlias("database")
    private String database;

    //连接地址
    @XStreamAlias("ip")
    private String ip;

    //端口号
    @XStreamAlias("port")
    private String port;

    //用户名
    @XStreamAlias("username")
    private String username;

    //密码
    @XStreamAlias("password")
    private String password;

    public Database() {
    }

    public Database(String name, String databaseType, String database, String ip, String port, String username, String password) {
        this.name = name;
        this.databaseType = databaseType;
        this.database = database;
        this.ip = ip;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Database{" +
                "name='" + name + '\'' +
                ", databaseType='" + databaseType + '\'' +
                ", database='" + database + '\'' +
                ", ip='" + ip + '\'' +
                ", port='" + port + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
