package cn.itcast.bookmanager.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.util.Properties;
import java.sql.Connection;


public class DbUtil {
	
		//1.加载驱动
		//数据连接url
		static String url;
		//创建Properties对象
		static Properties info = new Properties();
		
		//静态代码块
		static {
			//获取属性文件的输入流
			InputStream input = DbUtil.class.getClassLoader().getResourceAsStream("cn/itcast/bookmanager/utils/config.properties");
		
			try {
				//加载属性文件
				info.load(input);
				//从属性文件中读取url
				url = info.getProperty("url");
				//从属性文件中读取driver
				String driverClassName = info.getProperty("driver");
				//加载驱动程序
				Class.forName(driverClassName);
				System.out.println("驱动程序加载成功...");
				
			}catch(ClassNotFoundException e) {
				System.out.println("驱动程序加载失败...");
			}catch(IOException e) {
				System.out.println("属性文件加载失败..");
			}
		}
	public static Connection getConnection()throws Exception{
		
	    Connection conn = DriverManager.getConnection(url,info);
		return conn;
	}

	public void closeCon (Connection con)throws Exception {
		if(con!=null){
			con.close();
		}
	}
	
}
