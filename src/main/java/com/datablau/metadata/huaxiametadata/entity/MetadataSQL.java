package com.datablau.metadata.huaxiametadata.entity;

import java.io.Serializable;

/**
 * XStream解析六大SQL配置文件使用
 */
public class MetadataSQL implements Serializable {

    private MetadataMySQL mysql;
    private MetadataOracle oracle;
    private MetadataSQLServer sqlserver;

    public MetadataSQL() {
    }

    public MetadataSQL(MetadataMySQL mysql, MetadataOracle oracle, MetadataSQLServer sqlserver) {
        this.mysql = mysql;
        this.oracle = oracle;
        this.sqlserver = sqlserver;
    }

    public MetadataMySQL getMysql() {
        return mysql;
    }

    public void setMysql(MetadataMySQL mysql) {
        this.mysql = mysql;
    }

    public MetadataOracle getOracle() {
        return oracle;
    }

    public void setOracle(MetadataOracle oracle) {
        this.oracle = oracle;
    }

    public MetadataSQLServer getSqlserver() {
        return sqlserver;
    }

    public void setSqlserver(MetadataSQLServer sqlserver) {
        this.sqlserver = sqlserver;
    }

    @Override
    public String toString() {
        return "MetadataSQL{" +
                "mysql=" + mysql +
                ", oracle=" + oracle +
                ", sqlserver=" + sqlserver +
                '}';
    }
}
