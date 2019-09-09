package com.topKurl.FileCreate;

import lombok.Data;
import org.apache.commons.lang.time.FastDateFormat;

import java.util.Date;

/**
 * @program: topk
 * @description: create 100GB url
 * @author: wangtengke
 * @create: 2019-09-04
 **/
public class FileCreateBatch {
    public static void main(String[] args) {
        String fileName = "data/testData.txt";
        System.out.println(args[0]);
        System.out.println(args.length);
        int count = 10;
        int batchLen = 50;
        if(args.length>0) {
            if(args.length!=3) {
                System.err.println("param number is error!");
                return;
            } else {
                try {
                    fileName = args[0];
                    count = Integer.valueOf(args[1]);
                    batchLen = Integer.valueOf(args[2]);
                }catch (Exception e) {
                    System.err.println("param input is error!");
                    return;
                }
            }
        }

        FastDateFormat fastDateFormat = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss.SSS");
        System.out.println("create begin at  ------> " +  fastDateFormat.format(new Date()));
        for (int i = 0; i < count; i++) {
            WriteFile writeFile = new WriteFile(fileName, batchLen);
            Thread  thread = new Thread(writeFile);
            thread.start();
        }
    }

}
