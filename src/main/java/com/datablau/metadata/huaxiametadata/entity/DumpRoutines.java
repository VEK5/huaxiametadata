package com.datablau.metadata.huaxiametadata.entity;

import java.io.Serializable;

/**
 * 存储过程/函数
 */
public class DumpRoutines implements Serializable {

    private String tableCat;
    private String tableSchem;
    private String tableName;
    private String routineType;
    private int line;
    private String text;
    private String routineDefinition;
    private String parameters;
    private String returnV;
    private String databaseInfo;

    public DumpRoutines() {
    }

    public DumpRoutines(String tableCat, String tableSchem, String tableName, String routineType, int line, String text, String routineDefinition, String parameters, String returnV, String databaseInfo) {
        this.tableCat = tableCat;
        this.tableSchem = tableSchem;
        this.tableName = tableName;
        this.routineType = routineType;
        this.line = line;
        this.text = text;
        this.routineDefinition = routineDefinition;
        this.parameters = parameters;
        this.returnV = returnV;
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

    public String getRoutineType() {
        return routineType;
    }

    public void setRoutineType(String routineType) {
        this.routineType = routineType;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRoutineDefinition() {
        return routineDefinition;
    }

    public void setRoutineDefinition(String routineDefinition) {
        this.routineDefinition = routineDefinition;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getReturnV() {
        return returnV;
    }

    public void setReturnV(String returnV) {
        this.returnV = returnV;
    }

    public String getDatabaseInfo() {
        return databaseInfo;
    }

    public void setDatabaseInfo(String databaseInfo) {
        this.databaseInfo = databaseInfo;
    }
    @Override
    public String toString() {
        return "DumpRoutines{" +
                "tableCat='" + tableCat + '\'' +
                ", tableSchem='" + tableSchem + '\'' +
                ", tableName='" + tableName + '\'' +
                ", routineType='" + routineType + '\'' +
                ", line=" + line +
                ", text='" + text + '\'' +
                ", routineDefinition='" + routineDefinition + '\'' +
                ", parameters='" + parameters + '\'' +
                ", returnV='" + returnV + '\'' +
                ", databaseInfo='" + databaseInfo + '\'' +
                '}';
    }
}
