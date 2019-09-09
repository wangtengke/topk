package com.topKurl.FileMerge;

import java.io.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @program: topk
 * @description: merge small file top100 url, find the total top 100 url
 * @author: wangtengke
 * @create: 2019-09-05
 **/
public class FileMerge {
    public static void main(String[] args) {
        String subSortDir = "data/sort";
        String targetDir = "data/merge";
        System.out.println(args.toString()+" "+args.length);
        if(args.length>0) {
            if(args.length!=2) {
                System.err.println("param num is error!");
                return;
            }else {
                try {
                    subSortDir = args[0];
                    targetDir = args[1];
                }catch (Exception e) {
                    System.err.println("param input is error!");
                    return;
                }
            }
        }
        FileMerge fileMerge = new FileMerge();
        try {
            fileMerge.mergeFile(subSortDir, targetDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mergeFile(String subSortDir, String targetDir) throws IOException {
        TreeMap<Long, String> top100map = new TreeMap<Long, String>(new Comparator<Long>() {
            @Override
            public int compare(Long o1, Long o2) {
                return o1 > o2? -1: 1;
            }
        });
        File file = new File(subSortDir);
        File[] files = file.listFiles();
        for(File f: files) {
            FileInputStream fis = new FileInputStream(f);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));
            String str = null;
            while((str = bufferedReader.readLine()) != null) {
                String[] strs = str.split(" ");
                String url = strs[0];
                Long count = Long.valueOf(strs[1]);
                top100map.put(count, url);
                if(top100map.size()>100) {
                    top100map.pollLastEntry();
                }
            }
        }
        FileOutputStream fos = new FileOutputStream(new File(targetDir, "merge"));
        top100map.forEach((k, v)-> {
            try {
                fos.write((v + " " + k + "\n").getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        fos.flush();
        fos.close();
    }

}
