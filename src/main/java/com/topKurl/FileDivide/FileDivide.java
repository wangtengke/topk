package com.topKurl.FileDivide;

import org.apache.commons.lang.time.FastDateFormat;

import java.io.*;
import java.util.Date;

/**
 * @program: topk
 * @description: divide big file into many small file
 * @author: wangtengke
 * @create: 2019-09-04
 **/
public class FileDivide {
    public static void main(String[] args) throws IOException {
        String sourceFile = "data/testdata";
        int subFileNum = 200;
        if(args.length>0) {
            if(args.length!=2) {
                System.err.println("param num is error!");
                return;
            }else {
                try {
                    sourceFile = args[0];
                    subFileNum = Integer.valueOf(args[1]);
                }catch (Exception e) {
                    System.err.println("param input is error!");
                    return;
                }
            }
        }
        FastDateFormat fastDateFormat = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss.SSS");
        System.out.println("create subfile at ------> " +  fastDateFormat.format(new Date()));
        for (int i = 0; i < subFileNum; i++) {
            File file = new File("data/sub/subfile+"+i);
            if(!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                    file.delete();
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("end subfile at ------> " +  fastDateFormat.format(new Date()));
        FileInputStream inputStream = null;
        System.out.println("start read source file at ------> " +  fastDateFormat.format(new Date()));
        try {
            inputStream = new FileInputStream(sourceFile);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String url = null;
            int count = 0;
            while((url = bufferedReader.readLine()) != null)
            {
                String fileName = "data/sub/subfile+";
                FileOutputStream fos = new FileOutputStream(fileName + String.valueOf(Math.abs(url.hashCode()%subFileNum)), true);
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
                writer.write(url+"\n");
                writer.flush();
                writer.close();
                fos.close();
                count++;
                System.out.println("read source file at line "+ count+" ------> " +  fastDateFormat.format(new Date()));
            }

            //close
            inputStream.close();
            bufferedReader.close();
            System.out.println("start read source file at ------> " +  fastDateFormat.format(new Date()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }






        
    }

}
