package com.atypon.MonteDB.util;

import java.io.File;

public class LoadBalancer {
    public static boolean noFreeSpace(String dir){
        File partition=new File(dir);
        return partition.getFreeSpace()==0;
    }
}
