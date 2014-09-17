package com.madhouse.app;
import com.madhouse.app.lib.*;
import java.io.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.SortingParams;
//mysql需要倒入的包
import java.sql.*;

//import java.util.HashMap;
/**
 *  *
 */
public class App 
{
    public static void main( String[] args )
    {
        MutliThread m1=new MutliThread("Window 1");
        MutliThread m2=new MutliThread("Window 2");
        m1.start();
        m2.start();
        try{
        Afile ifile= new Afile();
        ifile.initLogDir();
        ifile.writeBytes( "80000006|800007|3|2|hello|\n"); 
        }catch (IOException e){
            System.out.println("file error"+ e);
        }
        Log log= new Log();
        
        log.write();

        Mysql imysql = new Mysql();
        imysql.conn();
        redis();
        mysql();
    }
    public static void redis()
    {
        Jedis jedis =new Jedis("192.168.2.9");
        String keys="name";
        jedis.del(keys);
        jedis.set(keys,"snowolf");
        String value=jedis.get(keys);
        System.out.println(value);
        System.out.println( "Hello World!" );

    }

    public static void mapString()
    {
    	
    }
    public static void mysql()
    {
        String driver = "com.mysql.jdbc.Driver";
        //URL指向要访问的数据库名scutcs
        String url = "jdbc:mysql://127.0.0.1:3306/test"; //注意格式否则链接不上
        //MySQL配置时的用户名
        String user = "root";
        //Java连接MySQL配置时的密码
        String password = "";
        try {
            // 加载驱动程序
            Class.forName(driver);
            //连续数据库
            Connection conn = DriverManager.getConnection(url, user, password);
            if(!conn.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //  statement用来执行SQL语句
            Statement statement = conn.createStatement();
            // 要执行的SQL语句
            String sql = "select * from user";
            //结果集
            ResultSet rs = statement.executeQuery(sql);
            System.out.println("-----------------");
            System.out.println("执行结果如下所示:");
            System.out.println("-----------------");
            System.out.println(" 学号" + "\t" + " 姓名");
            System.out.println("-----------------");
            String name = null;
            while(rs.next()) {  
                // 选择sname这列数据
                name = rs.getString("id");
                // 首先使用ISO-8859-1字符集将name解码为字节序列并将结果存储新的字节数组中。
                // 然后使用GB2312字符集解码指定的字节数组
                name = new String(name.getBytes("ISO-8859-1"),"GB2312");
                // 输出结果
                System.out.println(rs.getString("name") + "\t" + name);
            }
            rs.close();
            conn.close();
        } catch(ClassNotFoundException e) {
            System.out.println("Sorry,can`t find the Driver!"); 
            e.printStackTrace();
        } catch(SQLException e) {
            e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        } 

    }
}
