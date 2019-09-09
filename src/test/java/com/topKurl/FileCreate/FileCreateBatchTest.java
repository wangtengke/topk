package com.topKurl.FileCreate;

import org.junit.Test;

import static org.junit.Assert.*;

public class FileCreateBatchTest {

    @Test
    public void main() {
        FileCreateBatch.main(new String[]{"data/testData.txt", "10", "50"});
    }
}