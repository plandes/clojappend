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
