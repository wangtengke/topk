package com.topKurl.FileCreate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.*;
import org.apache.commons.lang.time.FastDateFormat;

import java.io.*;
import java.util.Date;

/**
 * @program: topk
 * @description: write url into file
 * @author: wangtengke
 * @create: 2019-09-04
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WriteFile implements Runnable {
    private String fileName;
    private int batchLen;
    private static int loopCount = 1024 * 1024 * 1024 / 2;
    public void writeBuffer(File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
        String host = "www";
        String[] domain = {"com", "org", "top"};
        StringBuffer url = new StringBuffer();
        for (int i = 0; i < loopCount; i++) {
            String dm = domain[(int)(Math.random()*3)];
            String mid = RandomStringUtils.randomAlphanumeric(5);
//            String first = RandomStringUtils.randomAlphanumeric(5);
//            String second = RandomStringUtils.randomAlphanumeric(5);
            url.append(host + "." + mid + "." + dm + "\n");
//            System.out.println(url+ "" + i);
//            writer.write(url.toString());
            if (url.toString().getBytes().length % batchLen == 0) {
                byte[] contentInBytes = url.toString().getBytes();
                fos.write(contentInBytes);
                url.setLength(0);
            }
        }
        if (url.length() > 0) {
            byte[] contentInBytes = url.toString().getBytes();
            fos.write(contentInBytes);
        }

        fos.flush();
        fos.close();
        FastDateFormat fastDateFormat = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss.SSS");
        System.out.println("create end at ------> " +  fastDateFormat.format(new Date()));
    }
    public void run() {
        File file = new File(fileName);
        try {
            writeBuffer(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
