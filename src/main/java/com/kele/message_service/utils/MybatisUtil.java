package com.kele.message_service.utils;

import com.kele.message_service.config.HostConfig;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.FileInputStream;
import java.io.InputStream;

public class MybatisUtil {
    private static MybatisUtil instance = new MybatisUtil();
    private SqlSessionFactory sqlSessionFactory;

    private final static String SQLMAPCONFIG_PATH_LOCAL = "/media/jojo/Code/Web/message_service/src/main/resources/sqlMapConfig.xml";
    private final static String SQLMAPCONFIG_PATH_ALIYUN = "resources/sqlMapConfig.xml";
    private MybatisUtil(){
        try{
            String resource = "";
            switch (HostConfig.getHostName()){
                case HostConfig.DEFAULT_ALIYUN_HOST_NAME:
                    resource = SQLMAPCONFIG_PATH_ALIYUN;
                    break;
                case HostConfig.DEFAULT_LOCAL_HOST_NAME:
                    resource = SQLMAPCONFIG_PATH_LOCAL;
                    break;
                    default: resource = SQLMAPCONFIG_PATH_ALIYUN;
            }
//            resource = SQLMAPCONFIG_PATH_ALIYUN;
            System.out.println("数据库配置文件路径：" + resource);
            InputStream inputStream = new FileInputStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);    // 创建 SqlSessionFactory
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static MybatisUtil getInstance() {
        return instance;
    }

    public SqlSession getSqlSession() {
        SqlSession sqlSession;
        sqlSession = sqlSessionFactory.openSession();
        return sqlSession;
    }
}
