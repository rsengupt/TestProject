package com.demo.test.bu.data;

public class DataUtil {
    private static DataUtil inst;
    
    private DataUtil() { }
    
    
    public static DataUtil getInstance() {
        if (inst == null) {
            inst = new DataUtil();
        }
        return inst;
    }
    
    
    public String getLocatorTypeValue(final String logicalName) {
        return ReadObjectRepoFile.getInstance().getLocatorTypeValue(logicalName);
    }

}
