package com.data;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lujun on 2017/9/1.
 */
public class Utils {

    public static File createFile(String filePath)throws Exception{
        File file = new File(filePath);
        if(!file.exists()){
            file.createNewFile();
        }
        return file;
    }

    public static void writeFile(String filePath,String content)throws Exception{
        File file = createFile(filePath);
        FileWriter fileWriter = new FileWriter(file,true);
        content += "\r\n";
        fileWriter.write(content);
        fileWriter.close();
    }

    public static String data2Str(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HHmmss");
        return sdf.format(date);
    }

    public static Long getCurrentSecond(){
        return System.currentTimeMillis();
    }
}
