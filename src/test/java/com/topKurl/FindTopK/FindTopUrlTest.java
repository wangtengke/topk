package com.topKurl.FindTopK;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class FindTopUrlTest {

    @Test
    public void main() {
        try {
            FindTopUrl.main(new String[]{"data", "data/sort"});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}