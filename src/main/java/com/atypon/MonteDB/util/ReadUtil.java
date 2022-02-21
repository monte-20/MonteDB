package com.atypon.MonteDB.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.Map;


public class ReadUtil {


    private static <T> T read(File file, TypeReference<T> typeReference) throws IOException {
        ObjectMapper objectMapper=new ObjectMapper();
        InputStream inputStream=new FileInputStream(file);
        return  objectMapper.readValue(inputStream,typeReference);
}


    public static <T> T readFile(File file) throws IOException {
        TypeReference<T> typeReference=new TypeReference<T>(){};
        return read(file,typeReference);
    }

    public static <T> T readCollection(File file) throws IOException {
        TypeReference<Map<String,T>> typeReference=new TypeReference<Map<String,T>>(){};
        return (T) read(file,typeReference).values();
    }
}
