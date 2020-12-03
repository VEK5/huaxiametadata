package com.datablau.metadata.huaxiametadata.entity;

import java.io.Serializable;

/**
 * 视图
 */
public class DumpViews implements Serializable {

    private String tableCat;
    private String tableSchem;
    private String tableName;
    private String remarks;
    private String text;
    private String databaseInfo;

    public DumpViews() {
    }

    public DumpViews(String tableCat, String tableSchem, String tableName, String remarks, String text, String databaseInfo) {
        this.tableCat = tableCat;
        this.tableSchem = tableSchem;
        this.tableName = tableName;
        this.remarks = remarks;
        this.text = text;
        this.databaseInfo = databaseInfo;
    }

    public String getTableCat() {
        return tableCat;
    }

    public void setTableCat(String tableCat) {
        this.tableCat = tableCat;
    }

    public String getTableSchem() {
        return tableSchem;
    }

    public void setTableSchem(String tableSchem) {
        this.tableSchem = tableSchem;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDatabaseInfo() {
        return databaseInfo;
    }

    public void setDatabaseInfo(String databaseInfo) {
        this.databaseInfo = databaseInfo;
    }
    @Override
    public String toString() {
        return "DumpViews{" +
                "tableCat='" + tableCat + '\'' +
                ", tableSchem='" + tableSchem + '\'' +
                ", tableName='" + tableName + '\'' +
                ", remarks='" + remarks + '\'' +
                ", text='" + text + '\'' +
                ", databaseInfo='" + databaseInfo + '\'' +
                '}';
    }
}
