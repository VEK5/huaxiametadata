package com.datablau.metadata.huaxiametadata.dao;

import com.datablau.metadata.huaxiametadata.entity.*;
import com.datablau.metadata.huaxiametadata.utils.JDBCUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * 离线库Dao：旧数据删除、新数据入库
 */
public class OffLineDao {

    // 单次批处理的最大值
    private final int MAX_ADDBATCH_NUM = 500;
    private static final Logger LOG = LoggerFactory.getLogger(OffLineDao.class);

    /**
     * 将表信息存入数据库
     *
     * @param tablelist    要存入的数据
     * @param databaseInfo 删除字段
     */
    public void saveTable(List<DumpTables> tablelist, String databaseInfo) throws SQLException {

        if (tablelist != null && tablelist.size() > 0) {
            //1.获取链接
            Connection conn = JDBCUtil.getMySQLConnection();
            PreparedStatement stmt;

            int count = 0;  // 用于判断是否达到最大执行数
            int index = 0;  // 当前插入记录的下标

            //2.离线库操作
            conn.setAutoCommit(false);  //设置手动提交
            // 2.1 删除数据
            LOG.info("【DATABLAU】正在删除离线库" + databaseInfo + "的表信息旧数据......");
            stmt = conn.prepareStatement("delete from DUMP_TABLES where DATABASE_INFO = ?");
            stmt.setString(1, databaseInfo);
            stmt.executeUpdate();
            conn.commit();
            LOG.info("【DATABLAU】离线库表信息旧数据删除成功");

            // 2.2 数据插入
            LOG.info("【DATABLAU】表信息正在录入离线数据库......");
            try {
                stmt = conn.prepareStatement("INSERT INTO DUMP_TABLES(TABLE_CAT,TABLE_SCHEM,TABLE_NAME,TABLE_TYPE,REMARKS,DATABASE_INFO) VALUES (?,?,?,?,?,?)");
                while (index < tablelist.size()) {
                    stmt.setString(1, tablelist.get(index).getTableCat());
                    stmt.setString(2, tablelist.get(index).getTableSchem());
                    stmt.setString(3, tablelist.get(index).getTableName());
                    stmt.setString(4, tablelist.get(index).getTableType());
                    stmt.setString(5, tablelist.get(index).getRemarks());
                    stmt.setString(6, tablelist.get(index).getDatabaseInfo());

                    stmt.addBatch();
                    // 计数值自增
                    count++;
                    index++;

                    // 当达到达到最大增加批次值
                    if (count >= MAX_ADDBATCH_NUM) {
                        count = 0;
                        // 每500条执行一次,防止内存不足。
                        stmt.executeBatch();
                        // 清空
                        stmt.clearBatch();
                    }
                }

                // 当最后还有剩余时,且数据量不到500时,执行批处理sql
                if (count > 0) {
                    stmt.executeBatch();
                }
                // 当所有的批处理sql都执行完成后,对所有新增或者更新的数据进行提交
                conn.commit();
                LOG.info("【DATABLAU】表信息录入离线数据库成功,共计" + index + "条数据");
            } catch (SQLException e) {
                conn.rollback();//捕获到插入异常，此处回滚，防止删除时死锁
                throw e;
            } finally {
                //3.关闭连接
                JDBCUtil.close(stmt, conn);
            }
        } else {
            LOG.info("【DATABLAU】要存入的表数据tablelist为空");
        }
    }

    /**
     * 将字段信息存入数据库
     *
     * @param columnslist  要存入的数据
     * @param databaseInfo 删除字段
     */
    public void saveColumns(List<DumpColumns> columnslist, String databaseInfo) throws SQLException {

        if (columnslist != null && columnslist.size() > 0) {
            // 1.获取链接
            Connection conn = JDBCUtil.getMySQLConnection();
            PreparedStatement stmt;

            int count = 0;  // 用于判断是否达到最大执行数
            int index = 0;  // 当前插入记录的下标

            // 2.离线库操作
            conn.setAutoCommit(false);  //设置手动提交

            //2.1 删除数据
            LOG.info("【DATABLAU】正在删除离线库" + databaseInfo + "的字段旧数据......");
            stmt = conn.prepareStatement("delete from DUMP_COLUMNS where DATABASE_INFO = ?");
            stmt.setString(1, databaseInfo);
            stmt.executeUpdate();
            conn.commit();
            LOG.info("【DATABLAU】离线库字段旧数据删除成功");

            //2.2 数据插入
            LOG.info("【DATABLAU】字段信息正在录入离线数据库......");
            try {
                stmt = conn.prepareStatement("INSERT INTO DUMP_COLUMNS(TABLE_CAT,TABLE_SCHEM,TABLE_NAME,COLUMN_NAME,DATA_TYPE,TYPE_NAME,COLUMN_SIZE,DECIMAL_DIGITS,REMARKS,COLUMN_DEF,ORDINAL_POSITION,IS_NULLABLE,IS_AUTOINCREMENT,DATABASE_INFO) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                while (index < columnslist.size()) {
                    stmt.setString(1, columnslist.get(index).getTableCat());
                    stmt.setString(2, columnslist.get(index).getTableSchem());
                    stmt.setString(3, columnslist.get(index).getTableName());
                    stmt.setString(4, columnslist.get(index).getColumnName());
                    stmt.setInt(5, columnslist.get(index).getDataType());
                    stmt.setString(6, columnslist.get(index).getTypeName());
                    stmt.setInt(7, columnslist.get(index).getColumnSize());
                    stmt.setInt(8, columnslist.get(index).getDecimalDigits());
                    stmt.setString(9, columnslist.get(index).getRemarks());
                    stmt.setString(10, columnslist.get(index).getColumnDef());
                    stmt.setLong(11, columnslist.get(index).getOrdinalPosition());
                    stmt.setString(12, columnslist.get(index).getIsNullable());
                    stmt.setString(13, columnslist.get(index).getIsAutoincrement());
                    stmt.setString(14, columnslist.get(index).getDatabaseInfo());

                    stmt.addBatch();
                    // 计数值自增
                    count++;
                    index++;

                    // 当达到达到最大增加批次值
                    if (count >= MAX_ADDBATCH_NUM) {
                        count = 0;
                        // 每500条执行一次,防止内存不足。
                        stmt.executeBatch();
                        // 清空
                        stmt.clearBatch();
                    }

                }
                // 当最后还有剩余时,且数据量不到500时,执行批处理sql
                if (count > 0) {
                    stmt.executeBatch();
                }
                // 当所有的批处理sql都执行完成后,对所有新增或者更新的数据进行提交
                conn.commit();
                LOG.info("【DATABLAU】字段信息录入离线数据库成功,共计" + index + "条数据");
            } catch (SQLException e) {
                conn.rollback();//捕获到插入异常，此处回滚，防止删除时死锁
                throw e;
            } finally {
                //3.关闭连接
                JDBCUtil.close(stmt, conn);
            }
        } else {
            LOG.info("【DATABLAU】要存入的字段数据columnslist为空");
        }
    }

    /**
     * 将索引信息存入数据库
     *
     * @param indexlist    要存入的数据
     * @param databaseInfo 删除字段
     */
    public void saveIndex(List<DumpIndexes> indexlist, String databaseInfo) throws SQLException {
        if (indexlist != null && indexlist.size() > 0) {
            //1.获取链接
            Connection conn = JDBCUtil.getMySQLConnection();
            PreparedStatement stmt;

            int count = 0;  // 用于判断是否达到最大执行数
            int index = 0;  // 当前插入记录的下标

            //2.离线库操作
            conn.setAutoCommit(false);  //设置手动提交
            // 2.1 删除数据
            LOG.info("【DATABLAU】正在删除离线库" + databaseInfo + "的索引信息旧数据......");
            stmt = conn.prepareStatement("delete from DUMP_INDEXES where DATABASE_INFO = ?");
            stmt.setString(1, databaseInfo);
            stmt.executeUpdate();
            conn.commit();
            LOG.info("【DATABLAU】离线库索引信息旧数据删除成功");

            // 2.2 数据插入
            LOG.info("【DATABLAU】索引信息正在录入离线数据库......");
            try {
                stmt = conn.prepareStatement("INSERT INTO DUMP_INDEXES(TABLE_CAT,TABLE_SCHEM,TABLE_NAME,NON_UNIQUE,INDEX_QUALIFIER,INDEX_NAME,TYPE,ORDINAL_POSITION,COLUMN_NAME,ASC_OR_DESC,CARDINALITY,PAGES,FILTER_CONDITION,DATABASE_INFO) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                while (index < indexlist.size()) {
                    stmt.setString(1, indexlist.get(index).getTableCat());
                    stmt.setString(2, indexlist.get(index).getTableSchem());
                    stmt.setString(3, indexlist.get(index).getTableName());
                    stmt.setInt(4, indexlist.get( index).getNonUnique());
                    stmt.setString(5, indexlist.get(index).getIndexQualifier());
                    stmt.setString(6, indexlist.get(index).getIndexName());
                    stmt.setInt(7, indexlist.get(index).getType());
                    stmt.setLong(8, indexlist.get(index).getOrdinalPosition());
                    stmt.setString(9, indexlist.get(index).getColumnName());
                    stmt.setString(10, indexlist.get(index).getAscOrDesc());
                    stmt.setLong(11, indexlist.get(index).getCardinality());
                    stmt.setInt(12, indexlist.get(index).getPages());
                    stmt.setString(13, indexlist.get(index).getFilterCondition());
                    stmt.setString(14, indexlist.get(index).getDatabaseInfo());

                    stmt.addBatch();

                    // 计数值自增
                    count++;
                    index++;

                    // 当达到达到最大增加批次值
                    if (count >= MAX_ADDBATCH_NUM) {
                        count = 0;
                        // 每500条执行一次,防止内存不足。
                        stmt.executeBatch();
                        // 清空
                        stmt.clearBatch();
                    }
                }
                // 当最后还有剩余时,且数据量不到500时,执行批处理sql
                if (count > 0) {
                    stmt.executeBatch();
                }
                // 当所有的批处理sql都执行完成后,对所有新增或者更新的数据进行提交
                conn.commit();
                LOG.info("【DATABLAU】索引信息录入离线数据库成功,共计" + index + "条数据");
            } catch (SQLException e) {
                conn.rollback();//捕获到插入异常，此处回滚，防止删除时死锁
                throw e;
            } finally {
                //3.关闭连接
                JDBCUtil.close(stmt, conn);
            }
        } else {
            LOG.info("【DATABLAU】要存入的索引数据indexlist为空");
        }
    }

    /**
     * 将存储过程、函数 信息存入数据库
     *
     * @param routineslist 要存入的数据
     * @param databaseInfo 删除字段
     */
    public void saveRoutines(List<DumpRoutines> routineslist, String databaseInfo) throws SQLException {
        if (routineslist != null && routineslist.size() > 0) {
            //1.获取链接
            Connection conn = JDBCUtil.getMySQLConnection();
            PreparedStatement stmt;

            int count = 0;  // 用于判断是否达到最大执行数
            int index = 0;  // 当前插入记录的下标

            //2.离线库操作
            conn.setAutoCommit(false);  //设置手动提交
            // 2.1 删除数据
            LOG.info("【DATABLAU】正在删除离线库" + databaseInfo + "的存储过程/函数信息旧数据......");
            stmt = conn.prepareStatement("delete from DUMP_ROUTINES where DATABASE_INFO = ?");
            stmt.setString(1, databaseInfo);
            stmt.executeUpdate();
            conn.commit();
            LOG.info("【DATABLAU】离线库存储过程/函数信息旧数据删除成功");

            // 2.2 数据插入
            LOG.info("【DATABLAU】存储过程/函数信息正在录入离线数据库......");
            try {
                stmt = conn.prepareStatement("INSERT INTO DUMP_ROUTINES(ROUTINE_CAT,ROUTINE_SCHEM,ROUTINE_NAME,ROUTINE_TYPE,LINE,TEXT,ROUTINE_DEFINITION,PARAMETERS,RETURN_V,DATABASE_INFO) VALUES (?,?,?,?,?,?,?,?,?,?)");
                while (index < routineslist.size()) {
                    stmt.setString(1, routineslist.get(index).getTableCat());
                    stmt.setString(2, routineslist.get(index).getTableSchem());
                    stmt.setString(3, routineslist.get(index).getTableName());
                    stmt.setString(4, routineslist.get(index).getRoutineType());
                    stmt.setInt(5, routineslist.get(index).getLine());
                    stmt.setString(6, routineslist.get(index).getText());
                    stmt.setString(7, routineslist.get(index).getRoutineDefinition());
                    stmt.setString(8, routineslist.get(index).getParameters());
                    stmt.setString(9, routineslist.get(index).getReturnV());
                    stmt.setString(10, routineslist.get(index).getDatabaseInfo());

                    stmt.addBatch();
                    // 计数值自增
                    count++;
                    index++;

                    // 当达到达到最大增加批次值
                    if (count >= MAX_ADDBATCH_NUM) {
                        count = 0;
                        // 每500条执行一次,防止内存不足。
                        stmt.executeBatch();
                        // 清空
                        stmt.clearBatch();
                    }
                }

                // 当最后还有剩余时,且数据量不到500时,执行批处理sql
                if (count > 0) {
                    stmt.executeBatch();
                }
                // 当所有的批处理sql都执行完成后,对所有新增或者更新的数据进行提交
                conn.commit();
                LOG.info("【DATABLAU】存储过程/函数信息录入离线数据库成功,共计" + index + "条数据");
            } catch (SQLException e) {
                conn.rollback();//捕获到插入异常，此处回滚，防止删除时死锁
                throw e;
            } finally {
                //3.关闭连接
                JDBCUtil.close(stmt, conn);
            }
        } else {
            LOG.info("【DATABLAU】要存入的存储过程/函数数据routineslist为空");
        }
    }

    /**
     * 将 视图 信息存入数据库
     *
     * @param viewslist    要存入的数据
     * @param databaseInfo 删除字段
     */
    public void saveViews(List<DumpViews> viewslist, String databaseInfo) throws SQLException {
        if (viewslist != null && viewslist.size() > 0) {
            //1.获取链接
            Connection conn = JDBCUtil.getMySQLConnection();
            PreparedStatement stmt;

            int count = 0;  // 用于判断是否达到最大执行数
            int index = 0;  // 当前插入记录的下标

            //2.离线库操作
            conn.setAutoCommit(false);  //设置手动提交
            // 2.1 删除数据
            LOG.info("【DATABLAU】正在删除离线库" + databaseInfo + "的视图信息旧数据......");
            stmt = conn.prepareStatement("delete from DUMP_VIEWS where DATABASE_INFO = ?");
            stmt.setString(1, databaseInfo);
            stmt.executeUpdate();
            conn.commit();
            LOG.info("【DATABLAU】离线库视图信息旧数据删除成功");

            // 2.2 数据插入
            LOG.info("【DATABLAU】视图信息正在录入离线数据库......");
            try {
                stmt = conn.prepareStatement("INSERT INTO DUMP_VIEWS(TABLE_CAT,TABLE_SCHEM,TABLE_NAME,REMARKS,TEXT,DATABASE_INFO) VALUES (?,?,?,?,?,?)");
                while (index < viewslist.size()) {
                    stmt.setString(1, viewslist.get(index).getTableCat());
                    stmt.setString(2, viewslist.get(index).getTableSchem());
                    stmt.setString(3, viewslist.get(index).getTableName());
                    stmt.setString(4, viewslist.get(index).getRemarks());
                    stmt.setString(5, viewslist.get(index).getText());
                    stmt.setString(6, viewslist.get(index).getDatabaseInfo());

                    stmt.addBatch();
                    // 计数值自增
                    count++;
                    index++;

                    // 当达到达到最大增加批次值
                    if (count >= MAX_ADDBATCH_NUM) {
                        count = 0;
                        // 每500条执行一次,防止内存不足。
                        stmt.executeBatch();
                        // 清空
                        stmt.clearBatch();
                    }
                }
                // 当最后还有剩余时,且数据量不到500时,执行批处理sql
                if (count > 0) {
                    stmt.executeBatch();
                }
                // 当所有的批处理sql都执行完成后,对所有新增或者更新的数据进行提交
                conn.commit();
                LOG.info("【DATABLAU】视图信息录入离线数据库成功,共计" + index + "条数据");

            } catch (SQLException e) {
                conn.rollback();//捕获到插入异常，此处回滚，防止删除时死锁
                throw e;
            } finally {
                //3.关闭连接
                JDBCUtil.close(stmt, conn);
            }
        } else {
            LOG.info("【DATABLAU】要存入的视图数据viewslist为空");
        }
    }

    /**
     * 将 主键 信息存入数据库
     *
     * @param pkslist      要存入的数据
     * @param databaseInfo 删除字段
     */
    public void savePks(List<DumpPks> pkslist, String databaseInfo) throws SQLException {
        if (pkslist != null && pkslist.size() > 0) {
            //1.获取链接
            Connection conn = JDBCUtil.getMySQLConnection();
            PreparedStatement stmt;

            int count = 0;  // 用于判断是否达到最大执行数
            int index = 0;  // 当前插入记录的下标

            //2.离线库操作
            conn.setAutoCommit(false);  //设置手动提交
            // 2.1 删除数据
            LOG.info("【DATABLAU】正在删除离线库" + databaseInfo + "的主键信息旧数据......");
            stmt = conn.prepareStatement("delete from DUMP_PKS where DATABASE_INFO = ?");
            stmt.setString(1, databaseInfo);
            stmt.executeUpdate();
            conn.commit();
            LOG.info("【DATABLAU】离线库主键信息旧数据删除成功");

            // 2.2 数据插入
            LOG.info("【DATABLAU】主键信息正在录入离线数据库......");
            try {
                stmt = conn.prepareStatement("INSERT INTO DUMP_PKS(TABLE_CAT,TABLE_SCHEM,TABLE_NAME,COLUMN_NAME,KEY_SEQ,PK_NAME,DATABASE_INFO) VALUES (?,?,?,?,?,?,?)");
                while (index < pkslist.size()) {
                    stmt.setString(1, pkslist.get(index).getTableCat());
                    stmt.setString(2, pkslist.get(index).getTableSchem());
                    stmt.setString(3, pkslist.get(index).getTableName());
                    stmt.setString(4, pkslist.get(index).getColumnName());
                    stmt.setInt(5, pkslist.get(index).getKeySeq());
                    stmt.setString(6, pkslist.get(index).getPkName());
                    stmt.setString(7, pkslist.get(index).getDatabaseInfo());
                    stmt.addBatch();
                    // 计数值自增
                    count++;
                    index++;

                    // 当达到达到最大增加批次值
                    if (count >= MAX_ADDBATCH_NUM) {
                        count = 0;
                        // 每500条执行一次,防止内存不足。
                        stmt.executeBatch();
                        // 清空
                        stmt.clearBatch();
                    }
                }
                // 当最后还有剩余时,且数据量不到500时,执行批处理sql
                if (count > 0) {
                    stmt.executeBatch();
                }
                // 当所有的批处理sql都执行完成后,对所有新增或者更新的数据进行提交
                conn.commit();
                LOG.info("【DATABLAU】主键信息录入离线数据库成功,共计" + index + "条数据");
            } catch (SQLException e) {
                conn.rollback();//捕获到插入异常，此处回滚，防止删除时死锁
                throw e;
            } finally {
                //3.关闭连接
                JDBCUtil.close(stmt, conn);
            }

        } else {
            LOG.info("【DATABLAU】要存入的主键数据pkslist为空");
        }
    }


    /**
     * 发生异常，执行撤销操作
     *
     * @param databaseInfo 数据库连接对象
     */
    public void delOffLine(String databaseInfo) {

        // 1.获取链接
        Connection conn = JDBCUtil.getMySQLConnection();
        PreparedStatement stmt = null;

        // 2.删除数据
        LOG.info("【DATABLAU】异常撤销正在执行......");
        try {

            stmt = conn.prepareStatement("delete from DUMP_TABLES where DATABASE_INFO = ?");
            stmt.setString(1, databaseInfo);
            stmt.executeUpdate();

            stmt = conn.prepareStatement("delete from DUMP_COLUMNS where DATABASE_INFO = ?");
            stmt.setString(1, databaseInfo);
            stmt.executeUpdate();

            stmt = conn.prepareStatement("delete from DUMP_INDEXES where DATABASE_INFO = ?");
            stmt.setString(1, databaseInfo);
            stmt.executeUpdate();

            stmt = conn.prepareStatement("delete from DUMP_ROUTINES where DATABASE_INFO = ?");
            stmt.setString(1, databaseInfo);
            stmt.executeUpdate();

            stmt = conn.prepareStatement("delete from DUMP_VIEWS where DATABASE_INFO = ?");
            stmt.setString(1, databaseInfo);
            stmt.executeUpdate();

            stmt = conn.prepareStatement("delete from DUMP_PKS where DATABASE_INFO = ?");
            stmt.setString(1, databaseInfo);
            stmt.executeUpdate();

            LOG.info("【DATABLAU】异常撤销执行成功");
        } catch (SQLException e) {
            LOG.error("【ERROR】异常撤销执行失败-->" + e);
        }

        // 3.关闭连接
        JDBCUtil.close(stmt, conn);

    }

}
