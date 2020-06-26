package com.quickstart.hbase.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @description: HBase配置工具类
 * @author: yangyh
 * @create: 2020-06-26 18:25
 */
public class HBaseConf {
    private static final Logger LOGGER = LoggerFactory.getLogger(HBaseConf.class);
    private static HBaseConf instance = null;
    static Properties properties = new Properties();

    public HBaseConf() {
        ClassLoader ret = Thread.currentThread().getContextClassLoader();
        if (ret == null) {
            ret = ClassLoader.getSystemClassLoader();
        }

        InputStream inputStream = ret.getResourceAsStream("hbaseclient.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    // 用延迟加载方式实现了懒汉式单例，但在多线程环境下会产生多个single对象，使用synchronized同步锁
    public static synchronized String getValue(String key) {
        if (instance == null) {
            instance = new HBaseConf();
        }
        return properties.getProperty(key, "");
    }
}
