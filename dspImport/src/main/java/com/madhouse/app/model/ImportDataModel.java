package com.madhouse.app.model;
import java.util.*;
import java.io.*;

import com.madhouse.libs.*;
import com.madhouse.app.repository.camp; //import camp class
public class ImportDataModel
{
 
	
	public void counter()
	{
		
         //get 配置文件中日志目录 after contain in systemvariable		
		  iConfig iconf = new iConfig("config.properties");
	      String logFileName = iconf.getString("DSP_LOGS");
	      Afile ilog = new Afile(logFileName) ;
	      
	     //write log
	      try{
	    	  
	    	  Afile ifile= new Afile("./dataImport.log");
	    	  ifile.initLogDir();
	          ifile.writeBytes( "80000006|800007|3|2|hello|\n"); 
	          
	      }catch (IOException e)
	      {
	          System.out.println("file error"+ e);
	       }
	      
	      
	      //read log 
	      
	      String content = ilog.reader();
	      String logArray[] = content.split("|"); //字符串数组表示方法，key 数字下表索引
	      
	      
	      
	     
	      
	      //读取日志
	      
	      //切分日志
	      
	      //注册到类种 利用map 保存累
	      
	      //写入文件
	      
	      camp icamp = new camp(content);
	      
	      //
	      String  CampId = logArray[camp._OFFSET_CAMPID];
	      int ss= (int)camp._OFFSET_CAMPID;
	      
	  //    camp icamp = new camp();
	    //  camp.setCamp(logArray[1]);
	      
	      //List ilist =new List();
	      
	      Set iset = new HashSet();
	      
	      String s1 = new String("hello");
	      
	      iset.add("xx1");
	      iset.add("xx2");
	    
	      
	      
	    /*  boolean isExists= false;
	      Iterator it = iset.iterator();
	      while( it.hasNext())
	      {
	    	 String oldStr=(String)it.next();
	    	  String newStr="xx";
	    	  if(newStr.equals(oldStr))
	    	  {
	    		  isExists = true;
	    	  }
	      }*/
	      
	      
	      
	    //This is use haspMap!   
		Map map = new HashMap();
		map.put("name","tony this is you first hashmap counter");
		map.put("campaign",2345);
		
		//String str = map.get("name").toString();
		//String str2= map.get("campaign").toString();
		
		int m = 0;
//		String ikey="";
		for(int s=1;s<100;s++)
		{
			m++;
//			ikey = "key" + s;
			map.put( "key"+s , s);
			
			}
		int campId =30;
		int position =2;
		Map postion = new HashMap();
		
		ArrayList xx= new ArrayList();
		xx.add(position);
		
		map.put("campain"+ campId, xx);
		System.out.println(m);
		/**
		xx|xx|xx|xx|xx|xx|xx
		how to counter?
		
		
		datalog !
		
		key                    value
		"campaign+aspostion"    +1
		
		
		how to get ?
		
		xx yy 34 !
		xx|xx|xx|xx 34
		!
		how to make the only !
		key?
		?
		key ser

		记录存储！
		array！
		list！
		
		多个map？
		
		
		
		**/
		
	}
	
	public void readLog()
	{
			Afile ifile =new Afile("/services/serving_log"); //设定文件写入路径
			
	}
	
	}