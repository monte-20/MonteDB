package com.atypon.MonteDB.services;

import com.atypon.MonteDB.util.APIPath;
import com.atypon.MonteDB.util.WriteUtil;

import java.io.IOException;

public class WriteService {

    public boolean write(String filePath, Object data) throws IOException {
        String path= APIPath.generateWritePath(filePath);
        if(LockManager.addToLockedPath(path)){
            LockManager.addToTemp(path,data);

        new Thread(() -> {
            try {
                new WriteUtil().writeFile(path,data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                new WriteUtil().writeCollection(path,data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

            LockManager.removeLockedPath(path);
            return true;
        }else{
            System.out.println("file is currently being update");
            return false;
        }

    }
}
