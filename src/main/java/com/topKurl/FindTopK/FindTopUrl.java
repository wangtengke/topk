package com.topKurl.FindTopK;

import java.io.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @program: topk
 * @description: find top 100 url in every small file
 * @author: wangtengke
 * @create: 2019-09-04
 **/
public class FindTopUrl {
    public static void main(String[] args) throws IOException {
        String subFileDir = "data";
        String targetDir = "data/sort";
        if(args.length>0) {
            if(args.length!=2) {
                System.err.println("param num is error!");
                return;
            }else {
                try {
                    subFileDir = args[0];
                }catch (Exception e) {
                    System.err.println("param input is error!");
                    return;
                }
            }
        }
        File file = new File(subFileDir);
        File[] files = file.listFiles();
        int count = 0;
        for(File f: files) {
            if(!f.getName().contains(".part")) continue;
            FileInputStream fis = new FileInputStream(f);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));
            String url = null;
            Map<String, Long> map = new HashMap<>();
            while((url = bufferedReader.readLine()) != null) {
                if(!map.containsKey(url)) {
                    map.put(url, 1L);
                }
                else {
                    map.put(url, map.get(url)+1);
                }
            }
            TreeMap<Long, String> top100map = new TreeMap<Long, String>(new Comparator<Long>() {
                @Override
                public int compare(Long o1, Long o2) {
                    return o1 > o2? -1: 1;
                }
            });
            map.forEach((k,v)-> {
                top100map.put(v, k);
                if(top100map.size()>100) {
                     top100map.pollLastEntry();
                }
            });
            File sortFile = new File(targetDir + f.getName()+".sort");
            try (FileOutputStream fos = new FileOutputStream(new File(targetDir, f.getName()+".sort"))) {
                top100map.forEach((k, v) -> {
                    try {
                        fos.write((v + " " + k).getBytes());
                        fos.write("\n".getBytes());
                    } catch (IOException e) {
                        throw new RuntimeException(e.getMessage(), e);
                    }
                });
                fos.flush();
                count++;
                System.out.println("finish " + count);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
    }
}
