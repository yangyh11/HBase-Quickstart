package com.quickstart.hbase.examples;

import com.quickstart.hbase.conf.HBaseConf;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * @description:
 * @author: yangyh
 * @create: 2020-06-26 18:37
 */
public class TestMain {

    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", HBaseConf.getValue("hbase.zookeeper.quorum"));

        Connection conn = ConnectionFactory.createConnection(conf);

        Table table = conn.getTable(TableName.valueOf("Student"));


        // Get 根据rowKey查询数据
        String rowKey = "rowkey1";
        Get get = new Get(Bytes.toBytes(rowKey));

        get.addFamily(Bytes.toBytes("baseInfo"));

        Result result = table.get(get);

        // 从结果集根据列族和列名获取Cell对象
        Cell ageCell = result.getColumnLatestCell(Bytes.toBytes("baseInfo"), Bytes.toBytes("age"));
        Cell nameCell = result.getColumnLatestCell(Bytes.toBytes("baseInfo"), Bytes.toBytes("name"));

        // 从Cell对象获取值
        byte[] ageValueByte = CellUtil.cloneValue(ageCell);
        byte[] nameValueByte = CellUtil.cloneValue(nameCell);

        // 转换为对应的类型
        System.out.println(Bytes.toString(ageValueByte));
        System.out.println(Bytes.toString(nameValueByte));
    }
}
