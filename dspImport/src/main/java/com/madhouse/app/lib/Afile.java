package com.madhouse.app.lib;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
public class Afile 
{
    public Afile(){}
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
    //writ byte
    public void writeBytes(String content) throws IOException
    {
        if(content =="")
        {
            content = "Now is the time for all good men|"
                + " to come to the aid of their country|"
                + " and pay their due taxes.\n";
        }
        byte buf[] = content.getBytes();
        FileOutputStream f1 = new FileOutputStream("file2.txt",true);
        f1.write(buf);
        f1.close();
    }
    public static void method1(String file, String conent) {     
        BufferedWriter out = null;
        try {     
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
            out.write(conent);
        } catch (Exception e) {     
            e.printStackTrace();
        } finally {     
            try {     
                if(out != null){  
                    out.close();
                }  
            } catch (IOException e) {     
                e.printStackTrace();
            }     
        }     
    }     

    /**   
     * 追加文件：使用FileWriter   
     *    
     * @param fileName   
     * @param content   
     */    
    public static void method2(String fileName, String content) {   
        FileWriter writer = null;
        try {     
            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件     
            writer = new FileWriter(fileName, true);
            writer.write(content);
        } catch (IOException e) {     
            e.printStackTrace();
        } finally {     
            try {     
                if(writer != null){  
                    writer.close();
                }  
            } catch (IOException e) {     
                e.printStackTrace();
            }     
        }   
    }     

    /**   
     * 追加文件：使用RandomAccessFile   
     *    
     * @param fileName 文件名   
     * @param content 追加的内容   
     */    
    public static void method3(String fileName, String content) {   
        RandomAccessFile randomFile = null;
        try {     
            // 打开一个随机访问文件流，按读写方式     
            randomFile = new RandomAccessFile(fileName, "rw");
            // 文件长度，字节数     
            long fileLength = randomFile.length();
            // 将写文件指针移到文件尾。     
            randomFile.seek(fileLength);
            randomFile.writeBytes(content);
        } catch (IOException e) {     
            e.printStackTrace();
        } finally{  
            if(randomFile != null){  
                try {  
                    randomFile.close();
                } catch (IOException e) {  
                    e.printStackTrace();
                }  
            }  
        }  
    }    
    /**
     * 打印f路径下所有的文件和文件夹
     * @param f 文件对象
     */
    public static void printAllFile(File f){
        //打印当前文件名
        System.out.println(f.getName());
        //是否是文件夹
        if(f.isDirectory()){
            //获得该文件夹下所有子文件和子文件夹
            File[] f1 = f.listFiles();
            //循环处理每个对象
            int len = f1.length;
            for(int i = 0;i < len;i++){
                //递归调用，处理每个文件对象
                printAllFile(f1[i]);
            }
        }
    }
    public void  mvFiles(String sourceDir, String targetDir)
    {
    }

    public void cpFile(String sourceDir, String targetDir)
    {
    }
    public void mkdir(String pathName)
    {
    }
    /**
     * 删除对象f下的所有文件和文件夹
     * @param f 文件路径
     */
    public static void deleteAll(File f){
        //文件
        if(f.isFile()){
            f.delete();
        }else{ //文件夹
            //获得当前文件夹下的所有子文件和子文件夹
            File f1[] = f.listFiles();
            //循环处理每个对象
            int len = f1.length;
            for(int i = 0;i < len;i++){
                //递归调用，处理每个文件对象
                deleteAll(f1[i]);
            }
            //删除当前文件夹
            f.delete();
        }
    }
}
