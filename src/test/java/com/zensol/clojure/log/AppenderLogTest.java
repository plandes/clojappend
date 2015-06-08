package com.zensol.clojure.log;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@RunWith(JUnit4.class)
public class AppenderLogTest {
    private static final Log log = LogFactory.getLog(AppenderLogTest.class);

    @Test
    public void testPrintln() {
	if (log.isDebugEnabled()) {
	    log.debug(String.format("testing logger (should have one from console and another from clojure)"));
	}
    }
}
