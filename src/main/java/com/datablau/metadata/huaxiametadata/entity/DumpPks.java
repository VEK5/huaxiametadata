package com.datablau.metadata.huaxiametadata.entity;

import java.io.Serializable;

/**
 * 主键
 */
public class DumpPks implements Serializable {

    private String tableCat;
    private String tableSchem;
    private String tableName;
    private String columnName;
    private int keySeq;
    private String pkName;
    private String databaseInfo;

    public DumpPks() {
    }

    public DumpPks(String tableCat, String tableSchem, String tableName, String columnName, int keySeq, String pkName, String databaseInfo) {
        this.tableCat = tableCat;
        this.tableSchem = tableSchem;
        this.tableName = tableName;
        this.columnName = columnName;
        this.keySeq = keySeq;
        this.pkName = pkName;
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

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public int getKeySeq() {
        return keySeq;
    }

    public void setKeySeq(int keySeq) {
        this.keySeq = keySeq;
    }

    public String getPkName() {
        return pkName;
    }

    public void setPkName(String pkName) {
        this.pkName = pkName;
    }

    public String getDatabaseInfo() {
        return databaseInfo;
    }

    public void setDatabaseInfo(String databaseInfo) {
        this.databaseInfo = databaseInfo;
    }
    @Override
    public String toString() {
        return "DumpPks{" +
                "tableCat='" + tableCat + '\'' +
                ", tableSchem='" + tableSchem + '\'' +
                ", tableName='" + tableName + '\'' +
                ", columnName='" + columnName + '\'' +
                ", keySeq=" + keySeq +
                ", pkName='" + pkName + '\'' +
                ", databaseInfo='" + databaseInfo + '\'' +
                '}';
    }
}
