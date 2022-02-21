package com.atypon.MonteDB.services;

import com.atypon.MonteDB.util.APIPath;
import com.atypon.MonteDB.util.ReadUtil;

import java.io.IOException;
import java.util.List;

public class ReadService {

    public static <T> T readFile(String filePath) throws IOException {
        List<String> paths=APIPath.generateReadPaths(filePath);
        Object data=new Object();
        for (String path : paths) {
             data =LockManager.getFromTemp(path);
            if(data.equals(null))
                data=ReadUtil.readFile(path);
        }

        return (T) data;
    }


    public static <T> T readCollection(String filePath) throws IOException {
        List<String> paths=APIPath.generateReadPaths(filePath);
        Object data=new Object();
        for (String path : paths) {
            data=ReadUtil.readCollection(path);
        }
        return (T) data;
    }
}
