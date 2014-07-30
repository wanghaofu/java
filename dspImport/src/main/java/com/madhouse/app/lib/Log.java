package com.madhouse.app.lib;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.log4j.Logger;

public class Log
{
    static Logger logger =Logger.getLogger(Log.class);

    public Log()
    {

 
        //         BasicConfigurator.configure();// 自动快速地使用缺省Log4j环境。
        //    PropertyConfigurator.configure ( String configFilename) ：读取使用Java的特性文件编写的配置文件。
    }

    public void write()
    {
        System.out.println( System.getProperty("user.dir"));
        Logger logger = Logger.getLogger(Log.class);
        logger.debug("开始");
     //   example2();
        logger.debug("结束");
        //debug级别的信息  
            logger.debug("This is debug message.");
       // info级别的信息  
            logger.info("This is info message.");
        //error级别的信息  
            logger.error("This is error message.");
    }
}


