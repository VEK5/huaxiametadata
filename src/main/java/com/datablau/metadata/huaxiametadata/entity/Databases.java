package com.datablau.metadata.huaxiametadata.entity;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.io.Serializable;
import java.util.List;

//@XStreamAlias("databases")
public class Databases implements Serializable {

    @XStreamImplicit(itemFieldName="database")
    private List<Database> databaseList;


    public Databases() {
    }

    public Databases(List<Database> databaseList) {
        this.databaseList = databaseList;
    }

    public List<Database> getDatabaseList() {
        return databaseList;
    }

    public void setDatabaseList(List<Database> databaseList) {
        this.databaseList = databaseList;
    }

    @Override
    public String toString() {
        return "Databases{" +
                "databaseList=" + databaseList +
                '}';
    }
}
