package com.atypon.MonteDB.services;

import com.atypon.MonteDB.util.APIPath;
import com.atypon.MonteDB.util.ReadUtil;
import com.atypon.MonteDB.util.WriteUtil;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class LockManager {
    private static final int TEMP_ITEMS_SIZE=10000;

    public static boolean addToLockedPath(String path) throws IOException {
        List<String> lockedPaths=ReadUtil.readFile(APIPath.lockedPaths());
        if(!lockedPaths.contains(path)){
            lockedPaths.add(path);
            updateData(APIPath.lockedPaths(),lockedPaths);
            return true;
        }
        return false;
    }

    public static boolean addToTemp(String filePath,Object data) throws IOException {
        Map<String,Object> tempData=ReadUtil.readFile(APIPath.temp());
        if(tempData.size() >TEMP_ITEMS_SIZE){
            clearTemp(tempData);
        }
        tempData.put(filePath,data);
        updateData(APIPath.temp(),tempData);
        boolean addedToCache=tempData.containsKey(filePath)&&tempData.containsValue(data);
        return addedToCache;
    }
    public static <T> T getFromTemp(String filePath) throws IOException {
        Map<String,Object> temp=ReadUtil.readFile(APIPath.temp());
        if(temp.containsKey(filePath)){
            return (T) temp.get(filePath);
        }
        return null;
    }
    public static void removeLockedPath(String filePath) throws IOException {
        List<String> lockedPaths=ReadUtil.readFile(APIPath.lockedPaths());
         lockedPaths.remove(filePath);
         updateData(APIPath.lockedPaths(),lockedPaths);
    }
    private static void  updateData(String path, Object data) throws IOException {
        WriteUtil writer=new WriteUtil();
        writer.writeFile(path,data);
    }
    private static void  clearTemp(Map map) throws IOException {
      map.clear();
    }
}

