package com.datablau.metadata.huaxiametadata.entity;

import java.io.Serializable;

/**
 * 索引
 */
public class DumpIndexes implements Serializable {

    private String tableCat;
    private String tableSchem;
    private String tableName;
    private int nonUnique;
    private String indexQualifier;
    private String indexName;
    private int type;
    private long ordinalPosition;
    private String columnName;
    private String ascOrDesc;
    private long cardinality;
    private int pages;
    private String filterCondition;
    private String databaseInfo;

    public DumpIndexes() {
    }

    public DumpIndexes(String tableCat, String tableSchem, String tableName, int nonUnique, String indexQualifier, String indexName, int type, long ordinalPosition, String columnName, String ascOrDesc, long cardinality, int pages, String filterCondition, String databaseInfo) {
        this.tableCat = tableCat;
        this.tableSchem = tableSchem;
        this.tableName = tableName;
        this.nonUnique = nonUnique;
        this.indexQualifier = indexQualifier;
        this.indexName = indexName;
        this.type = type;
        this.ordinalPosition = ordinalPosition;
        this.columnName = columnName;
        this.ascOrDesc = ascOrDesc;
        this.cardinality = cardinality;
        this.pages = pages;
        this.filterCondition = filterCondition;
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

    public int getNonUnique() {
        return nonUnique;
    }

    public void setNonUnique(int nonUnique) {
        this.nonUnique = nonUnique;
    }

    public String getIndexQualifier() {
        return indexQualifier;
    }

    public void setIndexQualifier(String indexQualifier) {
        this.indexQualifier = indexQualifier;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getOrdinalPosition() {
        return ordinalPosition;
    }

    public void setOrdinalPosition(long ordinalPosition) {
        this.ordinalPosition = ordinalPosition;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getAscOrDesc() {
        return ascOrDesc;
    }

    public void setAscOrDesc(String ascOrDesc) {
        this.ascOrDesc = ascOrDesc;
    }

    public long getCardinality() {
        return cardinality;
    }

    public void setCardinality(long cardinality) {
        this.cardinality = cardinality;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getFilterCondition() {
        return filterCondition;
    }

    public void setFilterCondition(String filterCondition) {
        this.filterCondition = filterCondition;
    }

    public String getDatabaseInfo() {
        return databaseInfo;
    }

    public void setDatabaseInfo(String databaseInfo) {
        this.databaseInfo = databaseInfo;
    }

    @Override
    public String toString() {
        return "DumpIndexes{" +
                "tableCat='" + tableCat + '\'' +
                ", tableSchem='" + tableSchem + '\'' +
                ", tableName='" + tableName + '\'' +
                ", nonUnique=" + nonUnique +
                ", indexQualifier='" + indexQualifier + '\'' +
                ", indexName='" + indexName + '\'' +
                ", type=" + type +
                ", ordinalPosition=" + ordinalPosition +
                ", columnName='" + columnName + '\'' +
                ", ascOrDesc='" + ascOrDesc + '\'' +
                ", cardinality=" + cardinality +
                ", pages=" + pages +
                ", filterCondition='" + filterCondition + '\'' +
                ", databaseInfo='" + databaseInfo + '\'' +
                '}';
    }
}
