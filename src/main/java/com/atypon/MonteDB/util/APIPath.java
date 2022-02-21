package com.atypon.MonteDB.util;

import java.util.ArrayList;
import java.util.List;

public class APIPath {
    static public String lockedPaths(){
        return "src/main/java/com/atypon/MonteDB/data/lockedPath.json";
    }

    static public String temp(){
        return "src/main/java/com/atypon/MonteDB/data/temp.json";
    }

    static public String database(Character partitionSymbol){
        String path=partitionSymbol+":/database/";
        return path;
    }
    static public String generateWritePath(String filePath){
        String databaseLocation=database('G');
        if(LoadBalancer.noFreeSpace(databaseLocation)){
            databaseLocation=database('F');
        }
        String path=databaseLocation+filePath+".json";
        return path;
    }

    static public List<String> generateReadPaths(String filePath){
       List<String> paths=new ArrayList<String>();
       String path1=database('G')+filePath+".json";
       String path2=database('F')+filePath+".json";
       paths.add(path1);
       paths.add(path2);
        return paths;
    }


    static public String generateDirPath(String filePath){
        String path=filePath.replace(filePath.substring(filePath.lastIndexOf("/")),"")+".json";
        return path;
    }

    static public String generateFilename(String filePath){
        String fileName=filePath.substring(filePath.lastIndexOf("/"));
        fileName=fileName.substring(1);
        return fileName;
    }

}
