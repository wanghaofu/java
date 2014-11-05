package com.madhouse.libs;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.configuration.AbstractConfiguration;
import org.apache.commons.configuration.AbstractFileConfiguration;
import org.apache.commons.configuration.INIConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.ConfigurationException;

//日志 暂时forget
import org.apache.log4j.Logger;

/**获取配置参数的进一步封装
 * 
 * @author tony
 *
 */
public class iConfig
{
	static Logger logger = Logger.getLogger(iConfig.class);
	static Configuration config= null;
	
	public  iConfig(String file)
	{
		try{
		config = new PropertiesConfiguration(file);
		}catch(ConfigurationException e)
		{
			logger.error(e.getMessage());
		}
	}
	
	
	
	public  String getString(String key)
	{
	   return config.getString(key);
	}
	
	public  int getInt(String key)
	{
		return config.getInt(key);
	}
	
	
	
	
	public void testConfig()
	{
		Configuration config = null;
		 //config = new PropertiesConfiguration("config.properties");  //参
		
		try {
			config = new PropertiesConfiguration("config.properties");  //参
			// Change the list delimiter character to a slash
//			config.setListDelimiter('/'); //设置分割字符串 

			String ip = config.getString("ip");   //参数为key
			int port = config.getInt("port"); 
			
		    String title = config.getString("application.title");
			
		    //method 1
		    String[] colors = config.getStringArray("keys");
		    
		    //method 2
		    List colorList = config.getList("keys");
		    
		    System.out.println(colors[1]);
		    
		    System.out.println(ip);
			System.out.println(port);
			System.out.println(title);
		} catch(ConfigurationException e)
		{
			logger.error(e.getMessage());
		}
		
	}
	
	//通用配置参数获取方法 静态直接获取
	public static String getS( String key )
	{
		return key;
	}
	
	public static int getI(String key)
	{
		int xx=123;
		return xx;	
	}
	
	public static void readProperties()
	{
		
		
	}

	
	public static void readIni()
	{
		try{
			INIConfiguration config = new INIConfiguration("config.ini");
			String basestr = config.getBasePath();
			String filestr = config.getFileName();
			System.out.println(filestr);
			String pathstr = config.getPath();
			System.out.println(pathstr);
			Set zykhstr = config.getSections();
			
			for(Object setVal : zykhstr){
				System.out.println(setVal.toString());
			}
			
			}catch (ConfigurationException e) {
				logger.error(e.getMessage());
			}
			
	}
	
}