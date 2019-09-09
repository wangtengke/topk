package com.topKurl.FileDivide;

import org.junit.Test;

import static org.junit.Assert.*;

public class AppMainTest {

    @Test
    public void main() {
        AppMain.main(new String[]{"E:\\work\\project\\topk\\data", "testdata.txt", "10", "PRODUCERCONSUMER", "24", "8", "10240"});
    }
}