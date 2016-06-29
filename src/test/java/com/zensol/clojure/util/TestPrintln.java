package com.zensols.clojure.util;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestPrintln {
    @Test
    public void testPrintln() {
	ClojurePrint.print("<<<test of println>>>");
    }
}
