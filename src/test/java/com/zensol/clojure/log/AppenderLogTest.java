/*
This file is part of the Zenos Solutions Clojure Append Library (ZSCAL).

ZSCAL is free software: you can redistribute it and/or modify it under the
terms of the GNU Lesser General Public License as published by the Free
Software Foundation, either version 3 of the License, or (at your option) any
later version.

ZSCAL is distributed in the hope that it will be useful, but WITHOUT ANY
WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with
ZSCAL.  If not, see http://www.gnu.org/licenses/.
*/

package com.zensols.clojure.log;

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
