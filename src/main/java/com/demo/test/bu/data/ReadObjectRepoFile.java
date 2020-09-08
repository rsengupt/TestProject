package com.demo.test.bu.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class ReadObjectRepoFile {
    private static ReadObjectRepoFile inst;
    
    private ReadObjectRepoFile() { }
    
    
    static ReadObjectRepoFile getInstance() {
        if (inst == null) {
            inst = new ReadObjectRepoFile();
        }
        return inst;
    }
    
    
    private HashMap<String, String> createOR() {
        HashMap<String, String> orMap = new HashMap<>();
        
        List<String> lineList = new ArrayList<>();
        
        try {
            File f = new File(System.getProperty("user.dir") + "\\ObjectRepo\\tktBook.txt");
            if (f.exists()) {
               lineList = Files.readAllLines(Paths.get(System.getProperty("user.dir") + "\\ObjectRepo\\tktBook.txt"), StandardCharsets.UTF_8);
            }
            
            for (String temp: lineList) {
                String[] tempLine = temp.split("::");
                orMap.put(tempLine[0], tempLine[1] + "::" + tempLine[2]);
            }
            return orMap;
        }
        catch (final SecurityException | InvalidPathException | IOException e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    
    
    
    String getLocatorTypeValue(final String logicalName) {
        HashMap<String, String> loc = createOR();
        try {
            if (loc != null) {
                return loc.get(logicalName);
            }
            else {
                return null;
            }
        }
        catch (final ClassCastException | NullPointerException e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
}
