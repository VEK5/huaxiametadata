package com.datablau.metadata.huaxiametadata.dao;

import com.datablau.metadata.huaxiametadata.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 元数据拉取Dao：六大SQL查询
 */
public class GetMetadataDao {
    private static final Logger LOG = LoggerFactory.getLogger(GetMetadataDao.class);

    /**
     * @param database 用户配置的数据库，用于拼接URL和获取用户名密码
     * @param sql      六大SQL，用于执行查询语句
     */
    public Boolean operation(Database database, MetadataSQL sql) {

        // 离线数据库Dao
        OffLineDao offLineDao = new OffLineDao();

        // 1.根据数据库类型进行判断，加载不同驱动和SQL
        String driver = "";     //驱动
        String url = "";        //URL
        // 要执行的SQL语句
        String tables = "";     //表SQL
        String columns = "";    //字段SQL
        String index = "";      //索引SQL
        String routines = "";   //存储过程/函数SQL
        String views = "";      //视图SQL
        String pks = "";        //主键SQL
        // DATABASE_INFO拼接
        String ipport = database.getName().trim() + "-" + database.getDatabaseType().trim().toLowerCase() + "-" + database.getIp() + ":" + database.getPort();

        if ("mysql".equals(database.getDatabaseType().trim().toLowerCase())) {
            driver = "com.mysql.cj.jdbc.Driver";
            url = "jdbc:mysql://" + database.getIp() + ":" + database.getPort() + "/" + database.getDatabase() + "?useUnicode=true&useSSL=true&serverTimezone=UTC&characterEncoding=UTF-8&autoReconnect=true";
            tables = sql.getMysql().getTables();//表
            columns = sql.getMysql().getColumns();//字段
            index = sql.getMysql().getIndex();//索引
            routines = sql.getMysql().getRoutines();//存储过程/函数
            views = sql.getMysql().getViews();//视图
            pks = sql.getMysql().getPks();//主键
        } else if ("oracle".equals(database.getDatabaseType().trim().toLowerCase())) {
            driver = "oracle.jdbc.driver.OracleDriver";
            url = "jdbc:oracle:thin:@" + database.getIp() + ":" + database.getPort() + ":" + database.getDatabase();
            tables = sql.getOracle().getTables();//表
            columns = sql.getOracle().getColumns();//字段
            index = sql.getOracle().getIndex();//索引
            routines = sql.getOracle().getRoutines();//存储过程/函数
            views = sql.getOracle().getViews();//视图
            pks = sql.getOracle().getPks();//主键
        } else if ("sqlserver".equals(database.getDatabaseType().trim().toLowerCase())) {
            driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            url = "jdbc:sqlserver://" + database.getIp() + ":" + database.getPort() + ";database=" + database.getDatabase();
            tables = sql.getSqlserver().getTables();//表
            columns = sql.getSqlserver().getColumns();//字段
            index = sql.getSqlserver().getIndex();//索引
            routines = sql.getSqlserver().getRoutines();//存储过程/函数
            views = sql.getSqlserver().getViews();//视图
            pks = sql.getSqlserver().getPks();//主键
        }

        LOG.info("【DATABLAU】数据库类型：" + database.getDatabaseType().trim().toLowerCase());
        LOG.info("【DATABLAU】数据库驱动：" + driver);
        LOG.info("【DATABLAU】数据库URL：" + url);

        // 2.注册驱动
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            LOG.error("【ERROR】数据库驱动类无法加载异常" + driver + "-->" + e);
            return false;
        }

        // 3.获得连接
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, database.getUsername(), database.getPassword());
            LOG.info("【DATABLAU】数据库获取连接成功：" + conn);
        } catch (SQLException e) {
            LOG.error("【ERROR】数据库获取连接失败" + "-->" + e);
            return false;
        }

        // 4.获得语句执行平台,通过数据库连接对象,获取到SQL语句的执行者对象
        Statement stat = null;
        try {
            stat = conn.createStatement();
            LOG.info("【DATABLAU】数据库操作对象创建成功：" + stat);
        } catch (SQLException e) {
            LOG.error("【ERROR】数据库操作对象建立创建失败" + "-->" + e);
            return false;
        }

        // 5.查回来的数据信息，存入List，为入离线库做准备
        List<DumpTables> tablelist = new ArrayList<DumpTables>();
        List<DumpColumns> columnslist = new ArrayList<DumpColumns>();
        List<DumpIndexes> indexlist = new ArrayList<DumpIndexes>();
        List<DumpRoutines> routineslist = new ArrayList<DumpRoutines>();
        List<DumpViews> viewslist = new ArrayList<DumpViews>();
        List<DumpPks> pkslist = new ArrayList<DumpPks>();


        // 6. 执行sql语句，获取元数据并录入离线库
        ResultSet rs = null;

        // 6.1 获取表信息
        try {
            LOG.info("【DATABLAU】正在获取表信息......");
            rs = stat.executeQuery(tables);
            while (rs.next()) {
                DumpTables dumpTables = new DumpTables();

                dumpTables.setTableCat((String) rs.getObject("table_cat"));
                dumpTables.setTableSchem((String) rs.getObject("table_schem"));
                dumpTables.setTableName((String) rs.getObject("table_name"));
                dumpTables.setTableType((String) rs.getObject("table_type"));
                dumpTables.setRemarks((String) rs.getObject("remarks"));
                dumpTables.setDatabaseInfo(ipport);

                tablelist.add(dumpTables);
            }

        } catch (SQLException e) {
            LOG.error("【ERROR】获取表信息异常" + "-->" + e);
            return false;
        }
        LOG.info("【DATABLAU】表信息获取成功,共计" + tablelist.size() + "条数据");
        //存入表信息
        try {
            offLineDao.saveTable(tablelist, ipport);
        } catch (SQLException e) {
            LOG.error("【ERROR】表信息存入离线库时发生异常" + "-->" + e);
            offLineDao.delOffLine(ipport);
            return false;
        }

        // 6.2 获取存储过程/函数信息
        try {
            LOG.info("【DATABLAU】正在获取存储过程/函数信息......");
            rs = stat.executeQuery(routines);
            while (rs.next()) {
                DumpRoutines dumpRoutines = new DumpRoutines();

                dumpRoutines.setTableCat((String) rs.getObject("routine_cat"));
                dumpRoutines.setTableSchem((String) rs.getObject("routine_schem"));
                dumpRoutines.setTableName((String) rs.getObject("routine_name"));
                dumpRoutines.setRoutineType((String) rs.getObject("routine_type"));
                dumpRoutines.setLine(rs.getInt("line"));
                dumpRoutines.setText((String) rs.getObject("text"));
                dumpRoutines.setDatabaseInfo(ipport);

                routineslist.add(dumpRoutines);
            }

        } catch (SQLException e) {
            LOG.error("【ERROR】获取存储过程/函数信息异常" + "-->" + e);
            return false;
        }
        LOG.info("【DATABLAU】存储过程/函数信息获取成功,共计" + routineslist.size() + "条数据");
        //存存储过程/函数信息
        try {
            offLineDao.saveRoutines(routineslist, ipport);
        } catch (SQLException e) {
            LOG.error("【ERROR】存储过程/函数信息存入离线库时发生异常" + "-->" + e);
            offLineDao.delOffLine(ipport);
            return false;
        }

        // 6.3 获取视图信息
        try {
            LOG.info("【DATABLAU】正在获取视图信息......");
            rs = stat.executeQuery(views);
            while (rs.next()) {
                DumpViews dumpViews = new DumpViews();

                dumpViews.setTableCat((String) rs.getObject("table_cat"));
                dumpViews.setTableSchem((String) rs.getObject("table_schem"));
                dumpViews.setTableName((String) rs.getObject("table_name"));
                dumpViews.setRemarks((String) rs.getObject("remarks"));
                dumpViews.setText((String) rs.getObject("text"));
                dumpViews.setDatabaseInfo(ipport);

                viewslist.add(dumpViews);
            }

        } catch (SQLException e) {
            LOG.error("【ERROR】获取视图信息异常" + "-->" + e);
            return false;
        }
        LOG.info("【DATABLAU】视图信息获取成功,共计" + viewslist.size() + "条数据");
        //存视图信息
        try {
            offLineDao.saveViews(viewslist, ipport);
        } catch (SQLException e) {
            LOG.error("【ERROR】视图信息存入离线库时发生异常" + "-->" + e);
            offLineDao.delOffLine(ipport);
            return false;
        }


        // 6.4 获取主键信息
        try {
            LOG.info("【DATABLAU】正在获取主键信息......");
            rs = stat.executeQuery(pks);
            while (rs.next()) {
                DumpPks dumpPks = new DumpPks();

                dumpPks.setTableCat((String) rs.getObject("TABLE_CAT"));
                dumpPks.setTableSchem((String) rs.getObject("TABLE_SCHEM"));
                dumpPks.setTableName((String) rs.getObject("TABLE_NAME"));
                dumpPks.setColumnName((String) rs.getObject("COLUMN_NAME"));
                dumpPks.setKeySeq(rs.getInt("KEY_SEQ"));
                dumpPks.setPkName((String) rs.getObject("PK_NAME"));
                dumpPks.setDatabaseInfo(ipport);

                pkslist.add(dumpPks);
            }

        } catch (SQLException e) {
            LOG.error("【ERROR】获取主键信息异常" + "-->" + e);
            return false;
        }
        LOG.info("【DATABLAU】主键信息获取成功,共计" + pkslist.size() + "条数据");
        //存入主键信息
        try {
            offLineDao.savePks(pkslist, ipport);
        } catch (SQLException e) {
            LOG.error("【ERROR】主键信息存入离线库时发生异常" + "-->" + e);
            offLineDao.delOffLine(ipport);
            return false;
        }

        // 6.5 获取索引信息
        try {
            LOG.info("【DATABLAU】正在获取索引信息......");
            rs = stat.executeQuery(index);
            while (rs.next()) {
                DumpIndexes dumpIndexes = new DumpIndexes();

                dumpIndexes.setTableCat((String) rs.getObject("TABLE_CAT"));
                dumpIndexes.setTableSchem((String) rs.getObject("TABLE_SCHEM"));
                dumpIndexes.setTableName((String) rs.getObject("TABLE_NAME"));
                dumpIndexes.setNonUnique(rs.getInt("NON_UNIQUE"));
                dumpIndexes.setIndexQualifier((String) rs.getObject("INDEX_QUALIFIER"));
                dumpIndexes.setIndexName((String) rs.getObject("INDEX_NAME"));
                dumpIndexes.setType(rs.getInt("TYPE"));
                dumpIndexes.setOrdinalPosition(rs.getLong("ORDINAL_POSITION"));
                dumpIndexes.setColumnName((String) rs.getObject("COLUMN_NAME"));
                dumpIndexes.setAscOrDesc((String) rs.getObject("ASC_OR_DESC"));
                dumpIndexes.setCardinality(rs.getLong("CARDINALITY"));
                dumpIndexes.setPages(rs.getInt("PAGES"));
                dumpIndexes.setFilterCondition((String) rs.getObject("FILTER_CONDITION"));
                dumpIndexes.setDatabaseInfo(ipport);

                indexlist.add(dumpIndexes);
            }

        } catch (SQLException e) {
            LOG.error("【ERROR】获取索引信息异常" + "-->" + e);
            return false;
        }
        LOG.info("【DATABLAU】索引信息获取成功,共计" + indexlist.size() + "条数据");
        //存索引信息
        try {
            offLineDao.saveIndex(indexlist, ipport);
        } catch (SQLException e) {
            LOG.error("【ERROR】索引信息存入离线库时发生异常" + "-->" + e);
            offLineDao.delOffLine(ipport);
            return false;
        }

        // 6.6 获取字段信息
        try {
            LOG.info("【DATABLAU】正在获取字段信息......");
            rs = stat.executeQuery(columns);
            long startTime = System.currentTimeMillis();    //获取开始时间
            while (rs.next()) {
                DumpColumns dumpColumns = new DumpColumns();

                dumpColumns.setTableCat((String) rs.getObject("TABLE_CAT"));
                dumpColumns.setTableSchem((String) rs.getObject("TABLE_SCHEM"));
                dumpColumns.setTableName((String) rs.getObject("TABLE_NAME"));
                dumpColumns.setColumnName((String) rs.getObject("COLUMN_NAME"));
                dumpColumns.setDataType(rs.getInt("DATA_TYPE"));
                dumpColumns.setTypeName((String) rs.getObject("TYPE_NAME"));
                dumpColumns.setColumnSize(rs.getInt("COLUMN_SIZE"));
                dumpColumns.setDecimalDigits(rs.getInt("DECIMAL_DIGITS"));
                dumpColumns.setRemarks((String) rs.getObject("REMARKS"));
                dumpColumns.setColumnDef((String) rs.getObject("COLUMN_DEF"));
                dumpColumns.setOrdinalPosition(rs.getLong("ORDINAL_POSITION"));
                dumpColumns.setIsNullable((String) rs.getObject("IS_NULLABLE"));
                dumpColumns.setIsAutoincrement((String) rs.getObject("IS_AUTOINCREMENT"));
                dumpColumns.setDatabaseInfo(ipport);

                columnslist.add(dumpColumns);
            }
            long endTime = System.currentTimeMillis();    //获取结束时间
            LOG.info("【DATABLAU】字段信息获取成功耗时" + (endTime - startTime) + "ms");
        } catch (SQLException e) {
            LOG.error("【ERROR】获取字段信息异常" + "-->" + e);
            return false;
        }
        LOG.info("【DATABLAU】字段信息获取成功,共计" + columnslist.size() + "条数据");
        //存字段信息
        try {
            long startTime = System.currentTimeMillis();    //获取开始时间
            offLineDao.saveColumns(columnslist, ipport);
            long endTime = System.currentTimeMillis();    //获取开始时间
            LOG.info("【DATABLAU】字段信息存入成功耗时" + (endTime - startTime) + "ms");
        } catch (SQLException e) {
            LOG.error("【ERROR】字段信息存入离线库时发生异常" + "-->" + e);
            offLineDao.delOffLine(ipport);
            return false;
        }

        // 8. 释放资源
        try {
            if (stat != null) {
                stat.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            LOG.error("【ERROR】释放资源时发生异常" + "-->" + e);
            return false;
        }

        return true;
    }

}
