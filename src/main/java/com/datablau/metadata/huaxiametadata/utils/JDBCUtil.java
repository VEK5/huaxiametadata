package com.datablau.metadata.huaxiametadata.utils;

import com.datablau.metadata.huaxiametadata.conf.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {
    private static final Logger LOG = LoggerFactory.getLogger(JDBCUtil.class);

    //从上下文中获取环境信息
    private static Environment environment = AppConfig.getApplicationContext().getEnvironment();

    /**
     * 获取数据库连接
     * @return Connection
     */
    public static Connection getMySQLConnection() {
        Connection conn = null;
        try {
            //加载数据库驱动
            Class.forName(environment.getProperty("spring.datasource.driverclassname"));
            //获取数据库连接
            conn = DriverManager.getConnection(environment.getProperty("spring.datasource.url"),
                    environment.getProperty("spring.datasource.username"),environment.getProperty("spring.datasource.password"));

        } catch (ClassNotFoundException e) {
            LOG.error("【ERROR】离线库-数据库驱动加载异常-->"+ e);
        } catch (SQLException e) {
            LOG.error("【ERROR】离线库-数据库获取连接异常-->"+ e);
        }
        return conn;
    }


    /**
     * 关闭io资源
     * @param io
     */
    public static void closeFile(Closeable ... io) {
        for(Closeable temp : io) {
            if(temp != null) {
                try {
                    temp.close();
                } catch (IOException e) {
                    LOG.error("【ERROR】文件关闭失败-->"+ e);
                }
            }
        }
    }

    //关闭JDBC资源  注意顺序
    public static void close(ResultSet rs,Statement ps,Connection conn) {
        try {
            if(rs!=null){
                rs.close();
            }
        } catch (SQLException e) {
            LOG.error("【ERROR】ResultSet关闭异常-->"+ e);
        }
        try {
            if(ps!=null){
                ps.close();
            }
        } catch (SQLException e) {
            LOG.error("【ERROR】Statement关闭异常-->"+ e);
        }
        try {
            if(conn!=null){
                conn.close();
            }
        } catch (SQLException e) {
            LOG.error("【ERROR】Connection关闭异常-->"+ e);
        }
    }

    public static void close(Statement ps,Connection conn){
        try {
            if(ps!=null){
                ps.close();
            }
        } catch (SQLException e) {
            LOG.error("【ERROR】Statement关闭异常-->"+ e);
        }
        try {
            if(conn!=null){
                conn.close();
            }
        } catch (SQLException e) {
            LOG.error("【ERROR】Connection关闭异常-->"+ e);
        }
    }

    public static void close(Connection conn){
        try {
            if(conn!=null){
                conn.close();
            }
        } catch (SQLException e) {
            LOG.error("【ERROR】Connection关闭异常-->"+ e);
        }
    }

}