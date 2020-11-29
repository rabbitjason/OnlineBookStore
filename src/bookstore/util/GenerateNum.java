package bookstore.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerateNum {

	private GenerateNum() {}
	
	private static GenerateNum g = null;
	
	public static synchronized GenerateNum getInstance() {
        if (g == null) {
            g = new GenerateNum();
        }
        return g;
    }
	
	private static int count = 0;
	
	private static final int total = 9999;
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	
	private static String getNowDateStr() {
        return sdf.format(new Date());
    }
	
	private static String now = null;
	
	public synchronized String GenerateOrder() {
		String datastr = getNowDateStr();
		if (datastr.equals(now)) {
		    count++;
		} else {
		    count = 1;
		    now = datastr;
		}
		
//		int countInteger = String.valueOf(total).length() - String.valueOf(count).length();
		String bu = "";
//		for (int i = 0; i < countInteger; i++) {
//		    bu += "0";
//	    }
		
	    bu += String.valueOf(count);
//	    if (count >= total) {
//	        count = 0;
//	    }
	    
	    return datastr + bu;
	}
	
}
