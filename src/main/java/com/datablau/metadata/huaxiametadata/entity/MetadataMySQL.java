package com.datablau.metadata.huaxiametadata.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

/**
 * XStream解析MySQL的SQL使用
 */
@XStreamAlias("mysql")
public class MetadataMySQL implements Serializable {

    //表
    @XStreamAlias("tables")
    private String tables;

    //字段
    @XStreamAlias("columns")
    private String columns;

    //索引
    @XStreamAlias("index")
    private String index;

    //函数/存储过程
    @XStreamAlias("routines")
    private String routines;

    //视图
    @XStreamAlias("views")
    private String views;

    //主键
    @XStreamAlias("pks")
    private String pks;

    public MetadataMySQL() {
    }

    public MetadataMySQL(String tables, String columns, String index, String routines, String views, String pks) {
        this.tables = tables;
        this.columns = columns;
        this.index = index;
        this.routines = routines;
        this.views = views;
        this.pks = pks;
    }

    public String getTables() {
        return tables;
    }

    public void setTables(String tables) {
        this.tables = tables;
    }

    public String getColumns() {
        return columns;
    }

    public void setColumns(String columns) {
        this.columns = columns;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getRoutines() {
        return routines;
    }

    public void setRoutines(String routines) {
        this.routines = routines;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getPks() {
        return pks;
    }

    public void setPks(String pks) {
        this.pks = pks;
    }

    @Override
    public String toString() {
        return "MetadataMySQL{" +
                "tables='" + tables + '\'' +
                ", columns='" + columns + '\'' +
                ", index='" + index + '\'' +
                ", routines='" + routines + '\'' +
                ", views='" + views + '\'' +
                ", pks='" + pks + '\'' +
                '}';
    }
}
