package com.datablau.metadata.huaxiametadata.execute;

import com.datablau.metadata.huaxiametadata.dao.GetMetadataDao;
import com.datablau.metadata.huaxiametadata.entity.Database;
import com.datablau.metadata.huaxiametadata.entity.Databases;
import com.datablau.metadata.huaxiametadata.entity.MetadataSQL;
import com.datablau.metadata.huaxiametadata.utils.XMLTransition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Order(1)
public class ApplicationRunner implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationRunner.class);

    /**
     * 获取用户配置的数据库信息
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {

        //1.读取配置文件，获取用户配置的数据库信息
        XMLTransition xMLTransition = new XMLTransition();
        Databases dataBases = xMLTransition.dbTransition("databases.xml");

        //2.读取SQL配置文件,获取六大SQL封装的对象。
        MetadataSQL sql = xMLTransition.SqlTransition("MetadataSQL.xml");

        //3.根据用户的配置文件遍历进行数据库连接
        if (dataBases != null && dataBases.getDatabaseList().size() > 0 && sql != null) {
            LOG.info("【DATABLAU】您共有" + dataBases.getDatabaseList().size() + "个数据库配置，即将按顺序执行......");
            List<String> message = new ArrayList<String>();     //记录执行结果
            for (Database db : dataBases.getDatabaseList()) {
                LOG.info("【DATABLAU】[" + db.getName() + "]----------------------------------------------------------【开始执行】");
                long startTime = System.currentTimeMillis();    //获取开始时间
                GetMetadataDao metadataDao = new GetMetadataDao();
                Boolean flag = metadataDao.operation(db, sql);
                long endTime = System.currentTimeMillis();      //获取结束时间
                LOG.info("【DATABLAU】[" + db.getName() + "]----------------------------------------------------------【执行结束】" + (flag == true ? "成功" : "失败") + "-耗时" + (endTime - startTime) + "ms");

                message.add("[" + db.getName() + "]" + "-" + (flag == true ? "成功" : "失败") + "-耗时" + (endTime - startTime) + "ms");
            }


            LOG.info("【DATABLAU】元数据采集完成------------结果如下:");
            for (int i = 0; i < message.size(); i++) {
                LOG.info("【DATABLAU】" + message.get(i));
            }
            LOG.info("【DATABLAU】--------------------------------------");
        }

        //4.执行完毕，退出系统
        System.exit(0);
    }
}
