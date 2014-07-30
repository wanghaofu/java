package com.madhouse.app;
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

class file
{
    public file(){}
    public void  initLogDir()
    {
        String currentPath=System.getProperty("user.dir");
        String pwd=currentPath+"/logs";                                                                                                                                                                       
        System.out.println(pwd);           
        File dir=new File(pwd);            
        if(dir.exists()){                  
            //            dir.delete();                
            System.out.println("Sub目录存在");
        }else{                             
            dir.mkdir();                   
            System.out.println("Sub目录不存在,已建立");
        }      
    }
}
