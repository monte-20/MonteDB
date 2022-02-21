package com.atypon.MonteDB.services;

import com.atypon.MonteDB.util.APIPath;
import com.atypon.MonteDB.util.ReadUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ReadService {

    public static <T> T readFile(String filePath) throws IOException {
        List<String> paths=APIPath.generateReadPaths(filePath);
        Object data=new Object();
        for (String path : paths) {
            System.out.println(path);
            File file= new File(path);
            if(file.exists()){
              data =LockManager.getFromTemp(path);
              if(data.equals(null))
                data=ReadUtil.readFile(file);
            }
        }

        return (T) data;
    }


    public static <T> T readCollection(String filePath) throws IOException {
        List<String> paths=APIPath.generateReadPaths(filePath);
        Object data=new Object();
        for (String path : paths) {
            System.out.println(path);
            File file= new File(path);
            if(file.exists()){
               data=ReadUtil.readCollection(file);
            }
        }
        return (T) data;
    }
}
