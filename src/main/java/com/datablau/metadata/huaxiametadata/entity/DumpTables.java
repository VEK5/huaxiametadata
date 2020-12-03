package com.datablau.metadata.huaxiametadata.entity;

import java.io.Serializable;

/**
 * 表
 */
public class DumpTables implements Serializable {

    private String tableCat;
    private String tableSchem;
    private String tableName;
    private String tableType;
    private String remarks;
    private String databaseInfo;

    public DumpTables() {
    }

    public DumpTables(String tableCat, String tableSchem, String tableName, String tableType, String remarks, String databaseInfo) {
        this.tableCat = tableCat;
        this.tableSchem = tableSchem;
        this.tableName = tableName;
        this.tableType = tableType;
        this.remarks = remarks;
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

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDatabaseInfo() {
        return databaseInfo;
    }

    public void setDatabaseInfo(String databaseInfo) {
        this.databaseInfo = databaseInfo;
    }
    @Override
    public String toString() {
        return "DumpTables{" +
                "tableCat='" + tableCat + '\'' +
                ", tableSchem='" + tableSchem + '\'' +
                ", tableName='" + tableName + '\'' +
                ", tableType='" + tableType + '\'' +
                ", remarks='" + remarks + '\'' +
                ", databaseInfo='" + databaseInfo + '\'' +
                '}';
    }
}
