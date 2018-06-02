package com.data;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lujun on 2017/9/1.
 */
public class CreateData {

    private final int rowNum = 10000;
    private Map mapDate = new HashMap();
    private String filePath;
    private String failLogPath;

    public CreateData(String filePath,String failLogPath){
        this.filePath = filePath;
        this.failLogPath = failLogPath;
    }

    public void createData()throws Exception{
        for(int i=0;i<rowNum;i++){
            String content = "";
            mapDate.put("id",i);
            mapDate.put("name","name" + i);
            mapDate.put("age",Integer.valueOf(1 + i));
            mapDate.put("sex",i % 2 == 0 ? "男" : "女");
            mapDate.put("address","广东-广州-天河区" + i);
            mapDate.put("phoneNum",1234567890);
            mapDate.put("city","广东-广州" + i);
            mapDate.put("email","qwertyuiop@qq.com");
            mapDate.put("qq","123456qwerty");

            try{
                Utils.writeFile(filePath,mapDate.toString());

            }catch (Exception e){
                Utils.writeFile(Utils.getCurrentSecond()+failLogPath,mapDate.toString());
            }

        }
    }
}
