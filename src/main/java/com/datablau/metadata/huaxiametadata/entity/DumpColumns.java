package com.datablau.metadata.huaxiametadata.entity;

import java.io.Serializable;

/**
 * 字段
 */
public class DumpColumns implements Serializable {

    private String tableCat;//null
    private String tableSchem;//数据库
    private String tableName;//表名
    private String columnName;//字段名
    private int dataType;//0
    private String typeName;//类型 varchar
    private int columnSize;//0
    private int decimalDigits;//0
    private String remarks;//备注
    private String columnDef;//
    private long ordinalPosition;//
    private String isNullable;//
    private String isAutoincrement;//
    private String databaseInfo;//

    public DumpColumns() {

    }

    public DumpColumns(String tableCat, String tableSchem, String tableName, String columnName, int dataType, String typeName, int columnSize, int decimalDigits, String remarks, String columnDef, long ordinalPosition, String isNullable, String isAutoincrement, String databaseInfo) {
        this.tableCat = tableCat;
        this.tableSchem = tableSchem;
        this.tableName = tableName;
        this.columnName = columnName;
        this.dataType = dataType;
        this.typeName = typeName;
        this.columnSize = columnSize;
        this.decimalDigits = decimalDigits;
        this.remarks = remarks;
        this.columnDef = columnDef;
        this.ordinalPosition = ordinalPosition;
        this.isNullable = isNullable;
        this.isAutoincrement = isAutoincrement;
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

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(int columnSize) {
        this.columnSize = columnSize;
    }

    public int getDecimalDigits() {
        return decimalDigits;
    }

    public void setDecimalDigits(int decimalDigits) {
        this.decimalDigits = decimalDigits;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getColumnDef() {
        return columnDef;
    }

    public void setColumnDef(String columnDef) {
        this.columnDef = columnDef;
    }

    public long getOrdinalPosition() {
        return ordinalPosition;
    }

    public void setOrdinalPosition(long ordinalPosition) {
        this.ordinalPosition = ordinalPosition;
    }

    public String getIsNullable() {
        return isNullable;
    }

    public void setIsNullable(String isNullable) {
        this.isNullable = isNullable;
    }

    public String getIsAutoincrement() {
        return isAutoincrement;
    }

    public void setIsAutoincrement(String isAutoincrement) {
        this.isAutoincrement = isAutoincrement;
    }

    public String getDatabaseInfo() {
        return databaseInfo;
    }

    public void setDatabaseInfo(String databaseInfo) {
        this.databaseInfo = databaseInfo;
    }

    @Override
    public String toString() {
        return "DumpColumns{" +
                "tableCat='" + tableCat + '\'' +
                ", tableSchem='" + tableSchem + '\'' +
                ", tableName='" + tableName + '\'' +
                ", columnName='" + columnName + '\'' +
                ", dataType=" + dataType +
                ", typeName='" + typeName + '\'' +
                ", columnSize=" + columnSize +
                ", decimalDigits=" + decimalDigits +
                ", remarks='" + remarks + '\'' +
                ", columnDef='" + columnDef + '\'' +
                ", ordinalPosition=" + ordinalPosition +
                ", isNullable='" + isNullable + '\'' +
                ", isAutoincrement='" + isAutoincrement + '\'' +
                ", databaseInfo='" + databaseInfo + '\'' +
                '}';
    }
}

