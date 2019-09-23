package com.example.demo;

import com.datacanvas.aps.pipes.dao.entity.Permission;
import com.datacanvas.aps.pipes.dao.entity.User;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Map;

public class JavaSerializerTest {

    @Test
    public void test() throws Exception {
        JdkSerializationRedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();
//        jdkSerializationRedisSerializer.

        String content = FileUtils.readFileToString(new File("d:/cache-user.txt"),"utf-8");
        int index = 0;
        List<Byte> byteList = new java.util.ArrayList<Byte>();
        while(index < content.length()){
            String s = content.substring(index, index + 1);
            if(s.equals("\\")){    //需要转换的
                s = content.substring(index, index + 2);
                if(s.equals("\\x")){  //十六进制
                    index+=2;   //一般占两个字符
                    String str = content.substring(index,index+2);
                    byte devBin = (byte) Integer.parseInt(str, 16);   //转成字节
                    byteList.add(devBin);
                    index+=2;
                }else if(s.startsWith("\\r") || s.startsWith("\\n") || s.startsWith("\\t") || s.startsWith("\\b")){  //特殊转义字符
                    String str = content.substring(index,index+2);
                    if(s.startsWith("\\r")){
                        str = "0D";
                    }
                    if(s.startsWith("\\n")){
                        str = "0A";
                    }
                    if(s.startsWith("\\t")){
                        str = "09";
                    }
                    if(s.startsWith("\\b")){
                        str = "08";
                    }
                    byte devBin = (byte) Integer.parseInt(str, 16);
                    byteList.add(devBin);
                    index+=2;
                }
            }else{   //直接转成字节
                s = content.substring(index, index + 1);
                byte bytes []= s.getBytes("UTF-8");
                for(byte b : bytes){
                    byteList.add(b);
                }
                index++;
            }
        }
        byte [] bytes = new byte[byteList.size()];
        int n = 0;
        for(byte b : byteList){
            bytes[n] = b;
            n++;
        }
        ByteInputStream bis = new ByteInputStream(bytes,byteList.size());
        ObjectInputStream os = new ObjectInputStream(bis);
        User user = (User) os.readObject();
        System.out.println(user);
        Map<String, Permission> permissionMap = user.getPermissionMap();
        for(String key : permissionMap.keySet()) {
            Permission permission = permissionMap.get(key);
            System.out.println("permission-id："+permission.getId()+";"+"name："+permission.getName()+"key："+permission.getPermissionKey()+"type："+permission.getPermissionType());
        }
    }
}
