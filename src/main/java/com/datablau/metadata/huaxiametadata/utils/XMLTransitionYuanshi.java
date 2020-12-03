package com.datablau.metadata.huaxiametadata.utils;

import com.datablau.metadata.huaxiametadata.entity.*;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * XML解析对象工具
 */
public class XMLTransitionYuanshi {

    private static final Logger LOG = LoggerFactory.getLogger(XMLTransitionYuanshi.class);
    /**
     * 获取用户数据库配置信息
     *
     * @param XMLName   配置文件名称
     * @return          解析后的Databases对象
     */
    public Databases dbTransition(String XMLName){

        SAXReader saxReader = new SAXReader();
        String xmlString = "";      //XML字符串
        Databases dataBases = null; //返回值

//        XMLName = "src/main/resources/"+XMLName;
        try {
            Document document = saxReader.read(new File(XMLName));
            xmlString = document.asXML();//将xml内容转化为字符串
        } catch (Exception e) {
            LOG.error("【DATABLAU】"+XMLName+"内容转化为字符串异常-->"+ e);
            return dataBases;
        }

//        xmlString = xmlString.replaceAll("[\\s\\t\\n\\r]", "");
        LOG.info("【DATABLAU】获取"+XMLName+"数据库配置------------------------------------------------------【开始】");
        LOG.info(xmlString);
        LOG.info("【DATABLAU】获取"+XMLName+"数据库配置------------------------------------------------------【结束】");

        XStream xStream = new XStream();
        //去掉包名
        xStream.alias("databases", Databases.class);
        xStream.alias("database", Database.class);
        //去掉List皮
        xStream.addImplicitCollection(Databases.class, "databaseList");


        //XML字符转对象
        try {
            dataBases = (Databases) xStream.fromXML(xmlString);
        } catch (Exception e) {
            LOG.error("【DATABLAU】"+XMLName+"字符转对象异常-->"+ e);
            return null;
        }

        return dataBases;
    }

    /**
     * 解析六大SQL
     * @param XMLName   六大SQL配置文件名称
     * @return          解析过的SQL对象
     */
    public MetadataSQL SqlTransition(String XMLName){

        SAXReader saxReader = new SAXReader();
        String xmlString = "";      //XML字符串
        MetadataSQL sql = null;     //返回值

        InputStream inputStream = null;

        try {
            Resource resource = new ClassPathResource(XMLName);
            inputStream = resource.getInputStream();
            Document document = saxReader.read(inputStream);
            xmlString = document.asXML();//将xml内容转化为字符串
        } catch (Exception e) {
            LOG.error("【DATABLAU】"+XMLName+"内容转化为字符串异常-->"+ e);
            return sql;
        }finally {
            close(inputStream);
        }

//        xmlString = xmlString.replaceAll("[\\s\\t\\n\\r]", "");

        XStream xStream = new XStream();
        //去掉包名
        xStream.alias("datasource", MetadataSQL.class);
        xStream.alias("mysql", MetadataMySQL.class);
        xStream.alias("oracle", MetadataOracle.class);
        xStream.alias("sqlserver", MetadataSQLServer.class);

        //XML字符转对象
        try {
            sql = (MetadataSQL) xStream.fromXML(xmlString);
        } catch (Exception e) {
            LOG.error("【DATABLAU】"+XMLName+"字符转对象异常-->"+ e);
            return null;
        }
        return sql;
    }

    /**
     * 关流
     * @param inputStream
     */
    public static void close(InputStream inputStream){
        try {
            if(inputStream!=null){
                inputStream.close();
            }
        } catch (IOException e) {
            LOG.error("【ERROR】InputStream关闭异常-->"+ e);
        }
    }

}
