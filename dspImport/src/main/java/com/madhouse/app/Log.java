
package com.madhouse.app;
import org.apache.log4j.Logger;
class Log
{
    static Logger logger =Logger.getLogger(Log.class);

    public Log()
    {
       
       // BasicConfigurator.configure();// 自动快速地使用缺省Log4j环境。
        //PropertyConfigurator.configure ( String configFilename) ：读取使用Java的特性文件编写的配置文件。

    }
    public void write()
    {

//        Logger logger = Logger.getLogger(Log.class);
        logger.debug("开始");
        //        example2();
        logger.debug("结束");
        // 记录debug级别的信息  
        logger.debug("This is debug message.");
        // 记录info级别的信息  
        logger.info("This is info message.");
        // 记录error级别的信息  
        logger.error("This is error message.");
    }
}
