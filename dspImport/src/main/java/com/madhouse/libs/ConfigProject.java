package com.madhouse.libs;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.configuration.AbstractConfiguration;
import org.apache.commons.configuration.AbstractFileConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.INIConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

//日志 暂时forget
import org.apache.log4j.Logger;

public final class ConfigProject {
	
//	public static final Logger LOGGER = LoggerFactory.getLogger(ConfigProject.class);
	
//	public static CompositeConfiguration COMMON_CONFIG = new CompositeConfiguration();
	
	public static void init(String confDir)
	{
		//加载顺序是从前到后,一个属性在先加载的配置中查找,没有则在后面的配置中查找.
//		COMMON_CONFIG.addConfiguration(loadPropertiesConfiguration(confDir,"settings.properties"));
	//	COMMON_CONFIG.addConfiguration(loadXMLConfiguration(confDir,"data.xml"));
	}
	
	/**
	 * 获取properties配置文件属性
	 */
	public static PropertiesConfiguration loadPropertiesConfiguration(String confDir,String fileName){
		PropertiesConfiguration fileConfiguration = new PropertiesConfiguration();
		try{
	//        fileConfiguration.setReloadingStrategy( getFileChangedReloadingStrategy() );
            //加载文件前设置分隔符失效(不使用任何分隔符).
            fileConfiguration.setDelimiterParsingDisabled(true);
            //加载文件前设置分割符,默认为逗号(,)如果配置项中包含分隔符就会被分割.
            //fileConfiguration.setListDelimiter('_');
            //如果用户没有指定绝对路径:加载文件顺序为current directory,user home directory,classpath.
      //      fileConfiguration.load(getConfigURL(confDir,fileName));
		}catch(Exception e){
		//	LOGGER.error("failed to load properties config:" + fileName, e);
		}
		return fileConfiguration;
	}
	
	/** 
	 * 获取XML配置文件属性
	 */
	/*
	public static XMLConfiguration loadXMLConfiguration(String confDir,String fileName)
	{
		XMLConfiguration xmlConfiguration = new XMLConfiguration();
		try{
			xmlConfiguration.setReloadingStrategy(getFileChangedReloadingStrategy());
			xmlConfiguration.setDelimiterParsingDisabled(true);
			xmlConfiguration.setAttributeSplittingDisabled(true);
			xmlConfiguration.load(getConfigURL(confDir,fileName));
		}catch(Exception e){
			LOGGER.error("failed to load xml config:" + fileName, e);
		}
		return xmlConfiguration;
	}
	*/
	/*
	/**
	 * 通过properties文件设置system属性.
	 */
	
	public static void setSystemConfigurationByProp(String fileName) throws Exception{
	//	SystemConfiguration systemConfiguration = new SystemConfiguration();
	//	systemConfiguration.setSystemProperties(fileName);
//	}
	
	//private static URL getConfigURL(String confDir,String fileName) throws Exception{
	//	File file = new File(confDir + "/" + fileName);
	//	URL url = null;
	//	if (file.exists()) {
  //          url = file.toURI().toURL();
  //      } else {
        	/**
        	 * ConfigurationUtils.locate:
        	 * Return the location of the specified resource by searching
        	 * the user home directory, the current classpath and the system classpath.
        	 */
   //         url = ConfigurationUtils.locate(confDir,fileName);
 //           LOGGER.debug("config file url:"+url);
 //       }
//		return url;
	
//	private static FileChangedReloadingStrategy getFileChangedReloadingStrategy(){
//		FileChangedReloadingStrategy reloadingStrategy = new FileChangedReloadingStrategy();
 //       reloadingStrategy.setRefreshDelay(10000);//10s
  //      return reloadingStrategy;
//	}
	
//	public static void main(String[] args){
		ConfigProject.init("./conf");
	}
	
}