## 题目
100GB url 文件，使用 1GB 内存计算出出现次数 top100 的 url 和出现的次数

## 解题思路
topk问题，首先将大文件中的url进行hash，分割成若干个小于内存的小文件，然后对每个小文件进行top100url的堆排序，然后将这些结果依次读入内存进行比较，最后得到总体top100的url。

## FileaCreate.java 
随机生成100G的大文件，约76亿条url

## FileDivide.java
参考 https://github.com/daoqidelv/filespilt-demo 中的拆分思路，利用多线程的方式加速文件拆分

## FindTopK
利用TreeMap，建立100大小的堆，并对数据进行堆排序，得到每个小文件的top100 url

## FileMerge
依次读取，排序后的小文件，同样用堆排序，获得总体的top100 url

