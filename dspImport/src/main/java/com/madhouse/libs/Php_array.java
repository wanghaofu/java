package com.madhouse.libs;
import java.lang.reflect.Array;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
 
/**
 * @category 写着玩 一个类似于PHP动态数组的数据结构 数组是PHP里最强大的逻辑实现 JAVA能否想PHP那樣靈活地使用數據結構 關鍵是泛型數組
 *           实现 因为java到1.7都不支持泛型数组 用字节码反射实现的泛型数组 姑且还算OK 顺带实现一下日期格式转换 MD5生成
 *           emial格式校检
 * @author 虚空假面
 * @version 0.000001 有时间继续写 ,array_flip, ,array_merge ,array_rand ,array_shuffle
 *          ,array_diff ,array_chunk 纯属蛋疼
 * */
 
public class Php_array {
 
    @SuppressWarnings("unchecked")
    /** 一个抽象的假想PHP数组 想了好久才弄出来 */
    public static <T> T[] J2P_Array_Factory(Class<T> cls, int length) {
        return (T[]) Array.newInstance(cls, length);
    }
 
    /**
     * @category 区间求和 测试一下可变参数
     * @return ∑[n,m]
     * @param 一个区间
     *            可以像PHP数组,指定步长,步长是一个整形
     */
    public static int array_sum(int FromNumber, int ToNumber, int... step) {
        int s = (step.length == 0) ? 1 : step[0];
        int k = 0;
        for (int i = Math.min(FromNumber, ToNumber)/** L boundary */
        ; i <= Math.max(FromNumber, ToNumber)/** R boundary */
        ; i += s)
            k += i;
        return k;
    }
 
    // 泛型程序员的痛 请看下面的array_range方法实现
    public static int array_sum(Object[] a) {
        int sum = 0;
        for (Object o : a) {
            if (o instanceof Integer)
                sum += (Integer) o;
            else
                continue;
        }
        return sum;
    }
 
    /**
     * @category 支持类似PHP的 array_range(0,25,5) -> {0,5,10,15,20,25} 字符 或者 整形
     *           可以指定步长 [ ink step]
     * @param T
     *            start , T end , int step 支持整形和字符
     * @return generic array 根据字节码判断生成的泛型数组
     * */
    @SuppressWarnings("unchecked")
    public static <T> T[] array_range(T start, T end, int... step) {
        int s = (step.length == 0) ? 1 : step[0];
        int L;
        if (start instanceof Integer & end instanceof Integer) { // 全数字
            L = Math.abs(((Integer) start - (Integer) end - 1)) / s;
            Integer[] rs = J2P_Array_Factory(Integer.class, L);
            for (int i = 0, v = Math.min((Integer) start, (Integer) end); i < L; i++, v += s) {
                rs[i] = v;
            }
            return (T[]) rs;
        } else if (start instanceof Character ^ end instanceof Character) { // 混合字符数字
            /**
             * 太烦躁,不写了 // 伪代码 顶针 a = 10 -> z = 35 p1 在区间10,35左边
             * P1在[10,35]之间->映射到字符 p1 在区间10,35右边 p1在参数1 p1在参数2 回文 联接结果集 done
             */
        } else if (start instanceof Character & end instanceof Character) {// 全字符
            /** a = 10 b = 11 ... z=35 板拇指数了一下 英文字母的确是26个！今天唯一的收获 */
            int boundry_L = Character.getNumericValue((Character) start);
            int boundry_R = Character.getNumericValue((Character) end);
            boolean need2rev = false;
            // 如果参数1大于参数2 z....a 重写置换区间
 
            if (boundry_R <= boundry_L) {
                need2rev = true;
                // 如果需要回文显示 如 z-b z,y,x,w,...d,e,b 回调回文方法并打印
                int temp = boundry_L;
                boundry_L = boundry_R;
                boundry_R = temp;
            }
            Character value = Character
                    .forDigit(boundry_L, Character.MAX_RADIX);
            L = (boundry_R - boundry_L + 1) / s;
            Character[] rs = J2P_Array_Factory(Character.class, L);
            // fill the data array
            for (int i = 0; i < rs.length; i++) {
                rs[i] = value;
                value = Character.forDigit((boundry_L += s),
                        Character.MAX_RADIX);
            }
            if (need2rev)
                reverseString(rs);
            return (T[]) rs;
        } else {
            L = 0;
            System.out.println("不支持的类型");
            System.exit(0);
            throw new RuntimeException();
        }
        return null;
    }
 
    public static void reverseString(Character[] s) {
        int n = s.length - 1;
        for (int j = (n - 1) >> 1; j >= 0; --j) {
            Character temp1 = s[j];
            Character temp2 = s[n - j];
            s[j] = temp2;
            s[n - j] = temp1;
        }
    }
 
    /**
     * java生成MD5摘要算发版本太多了 写一个像PHP那么简单的
     * 
     * MD5 算法实现 在RFC1321 中定义 在RFC 1321中， 给出了Test suite用来检验你的实现是否正确： MD5 ("") =
     * d41d8cd98f00b204e9800998ecf8427e MD5 ("a") =
     * 0cc175b9c0f1b6a831c399e269772661 MD5 ("abc") =
     * 900150983cd24fb0d6963f7d28e17f72 MD5 ("message digest") =
     * f96b697d7cb7938d525a2f31aaf161d0 MD5 ("abcdefghijklmnopqrstuvwxyz")
     * =c3fcd3d76192e4007dfb496cca67e13b
     * 
     * @author Stig.DK Dim
     * @param byte[] source 字节数组
     * @return String md5 value String 返回加密以后的md5字符串
     */
    public static String php_md5(String pwd) {
        String S = null;
        java.security.MessageDigest md;
        try {
            md = java.security.MessageDigest.getInstance("MD5");
            md.update(pwd.getBytes());
            byte[] temp = md.digest();
            /** 得到一个128位长整数 用二进制表示就是16个Byte位 */
 
            char hex[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                    'a', 'b', 'c', 'd', 'e', 'f' };
            char str[] = new char[32];
            /** 每个字节用16进制表示的话 使用2个字符 所以16进制需要32个字符 */
            int k = 0; // 转换结果中对应的字符位置
            for (int i = 0; i < hex.length; ++i) {// 从第一个字节开始，对MD5的每一个字节
                byte b = temp[i];// 转换成16进制字符 取出第i个字符
                str[k++] = hex[b >>> 4 & 0xf]; // 取字节中高4位的数字转换
                str[k++] = hex[b & 0xf]; // 取右移以后低四位的数字
            }
            S = new String(str);
            return S;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "No Such a algorithm";
    }
 
    public static String php_Validate_Email(String _email)
            throws RuntimeException {
        final String STD_EMAIL = "^(\\d|\\w|\\-|_){3,}@([0-9a-z_\\-]*)(\\.(com|cn|inc|org|cc|edu|de)*){0,1}$";
        if (_email.matches(STD_EMAIL))
            return _email;
        else {
            throw new RuntimeException("非法电子邮件" + _email);
        }
    }
 
    /**
     * @category 接受一个对象参数 返回该对象对应的日期格式 如果是Date对象就返回 yyyy-MM-dd对应的格式化输出字符串
     *           如1985-11-15 如果是String 对象就返回对应的Date对象表示 列入 Fri Nov 15 00:00:00
     *           CST 1985
     * @param Date
     *            or String
     * @return Object to be String or Date
     * @throws ParseException
     * */
    public static Object php_parse_DateAndString(Object unix)
            throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (unix instanceof java.util.Date) { // 得到一个日期对象的字符串格式化表示
            return sdf.format(unix);
        } else if (unix instanceof String) {
            /** 得到一个字符串对应的date对象并返回 */
            Date D = sdf.parse(unix.toString());
            return D;
        } else
            throw new ParseException(null, 0);
    }
 
    public static void main(String[] args) {
 
        System.out.println("\n-----------------------------------------\n");
        System.out.println("\n区间求和[] -> sum : " + array_sum(0, -3));
        System.out.println("\n区间求和[] -> sum : " + array_sum(-1, -100));
        System.out.println("\n区间求和[] -> sum : " + array_sum(-100, -1));
        System.out.println("\n区间求和[] -> sum : " + array_sum(-100, -1, 2));
        System.out.println("\n-----------------------------------------\n");
 
        System.out.println("\n-----------------------------------------\n");
        Object[] a = { 1, 2, 3, 5, 6, 66, "fuckyou", new Date() };
        System.out
                .println("\n测试array_sum[java]-> :{ 1, 2, 3, 5, 6, 66, fuckyou, new Date() };\n 结果"
                        + array_sum(a));
        System.out.println("\n-----------------------------------------\n");
        /**
         * ----------------------------------------- 輸出 测试array_sum[java]-> :{
         * 1, 2, 3, 5, 6, 66, fuckyou, new Date() }; 结果83
         * -----------------------------------------
         * */
 
        System.out.println("\n-----------------------------------------\n");
        System.out.println("测试array_range[java](-1,6)");
        for (int num : array_range(-1, 6))
            System.out.print(num + ",");
        System.out.println("\n-----------------------------------------\n");
        /**
         * ----------------------------------------- 輸出
         * 测试array_range[java](-1,6) -1,0,1,2,3,4,5,6,
         * -----------------------------------------
         * */
 
        System.out.println("\n-----------------------------------------\n");
        System.out.println("测试array_range[java](5,100,5[步长])");
        for (int num : array_range(5, 100, 5))
            System.out.print(num + ",");
        System.out.println("\n-----------------------------------------\n");
        /**
         * ----------------------------------------- 輸出
         * 测试array_range[java](5,100,5[步长])
         * 5,10,15,20,25,30,35,40,45,50,55,60,65,70,75,80,85,90,95,
         * -----------------------------------------
         * */
 
        System.out.println("\n-----------------------------------------\n");
        System.out.println("测试array_range[java](a,z,3[步长])");
        for (char c : array_range('a', 'z', 3))
            System.out.print(c + ",");
        System.out.println("\n-----------------------------------------\n");
        /**
         * ----------------------------------------- 輸出
         * 测试array_range[java](a,z,3[步长]) a,d,g,j,m,p,s,v,
         * -----------------------------------------
         * */
 
        System.out.println("\n-----------------------------------------\n");
        System.out.println("测试array_range[java](z,b[步长])");
        for (char c : array_range('Z', 'A'))
            System.out.print(c + ",");
        /**
         * ----------------------------------------- 输出
         * 测试array_range[java](z,b[步长])
         * z,y,x,w,v,u,t,s,r,q,p,o,n,m,l,k,j,i,h,g,f,e,d,c,b,a,
         * -----------------------------------------
         * */
        System.out.println("\n-----------------------------------------\n");
 
        System.out.println("\n-----------------------------------------\n");
        System.out.println("测试php_md5('hello world')");
        System.err.println(php_md5("hello world"));
        /**
         * ----------------------------------------- 输出 测试php_md5('hello world')
         * 5eb63bbbe01eeed093cb22bb8f5acdc3
         * */
        System.out.println("\n-----------------------------------------\n");
 
        System.out.println("\n-----------------------------------------\n");
        System.out.println("测试php_parse_DateAndString(new Date())");
        try {
            System.err.println(php_parse_DateAndString(new Date()));
        } catch (ParseException e) {
        }
        /**
         * ----------------------------------------- 输出 Exception in thread
         * "main" java.lang.RuntimeException: 非法电子邮件
         * */
        System.out.println("\n-----------------------------------------\n");
 
        System.out.println("\n-----------------------------------------\n");
        System.out.println("测试php_Validate_email('helloworld@123.com.com')");
        System.err.println(php_Validate_Email("helloworld@123.com.com"));
        /**
         * ----------------------------------------- 输出 Exception in thread
         * "main" java.lang.RuntimeException: 非法电子邮件
         * */
        System.out.println("\n-----------------------------------------\n");
 
    }
 
}
