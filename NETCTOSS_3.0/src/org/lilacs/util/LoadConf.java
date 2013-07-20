package org.lilacs.util;

import java.io.IOException;
import java.util.Properties;

public class LoadConf {
	private static Properties prop ;
	static{
		prop= new Properties();
		try {
			prop.load(org.lilacs.util.LoadConf.class.getClassLoader().getResourceAsStream("org/lilacs/util/OperateLogger.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getResult(String key){
		String result = prop.getProperty(key);
		if(result == null)	
			return "";
		else
			return result;
	}
}
