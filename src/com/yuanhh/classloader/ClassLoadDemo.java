package com.yuanhh.classloader;

import java.io.IOException;
import java.io.InputStream;

/**
 * 自定义类加载器
 * 
 * @author yuanhuihui
 */
public class ClassLoadDemo{

    public static void main(String[] args) throws Exception {
        
        ClassLoader clazzLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String clazzName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    
                    InputStream is = getClass().getResourceAsStream(clazzName);
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };
        
        String currentClass = "com.yuanhh.classloader.ClassLoadDemo";
        Class<?> clazz = clazzLoader.loadClass(currentClass);
        Object obj = clazz.newInstance();
        
        System.out.println(obj.getClass());
        System.out.println(obj instanceof com.yuanhh.classloader.ClassLoadDemo);
        System.out.println(obj.getClass().getClassLoader());
        System.out.println(ClassLoadDemo.class.getClassLoader());
    }
}