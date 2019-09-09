package com.topKurl.FileMerge;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class FileMergeTest {

    @Test
    public void main() {
        FileMerge.main(new String[]{"data/sort","data/merge"});
    }

    @Test
    public void mergeFile() {
        FileMerge fileMerge = new FileMerge();
        try {
            fileMerge.mergeFile("data/sort", "data/merge");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}