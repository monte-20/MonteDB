package com.atypon.MonteDB.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class WriteUtil<T> {
    private Map<String,T> map=null;

    public void writeFile(String path,T data) throws IOException {
        File file=new File(path);
        file.getParentFile().mkdirs();
        if(file.createNewFile()){
            System.out.println("File created at: "+path);
        }
       updateData(file,data);
    }

    public void writeCollection(String path,T data) throws IOException {
       String collectionPath=APIPath.generateDirPath(path);
       map=getData(collectionPath);
       map.put(APIPath.generateFilename(path),data);
       File collection=new File(collectionPath);
       collection.getParentFile().mkdirs();
       if(collection.createNewFile()){
           System.out.println("Collection created at "+collectionPath);
       }
       updateData(collection,  map);
    }


    private Map<String,T> getData(String path) throws IOException {
        if(new File(path).exists()){
        return ReadUtil.readFile(path);
        }else{
         return new HashMap<String,T>();
        }
    }


    private void updateData(File file,Object data) throws IOException {
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.writeValue(file,data);

    }

}
