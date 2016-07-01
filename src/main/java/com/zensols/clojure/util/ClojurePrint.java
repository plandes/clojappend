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

import clojure.java.api.Clojure;
import clojure.lang.IFn;

/**
 * Utility to class to print a string with the <tt>println</tt> function.  This
 * is called by {@link com.zensols.clojure.log.ClojureAppender} for Log4j2 log
 * events when the writer isn't set.
 */
public class ClojurePrint {
    private static final String NAMESPACE = "clojure.core";
    private static final IFn printlnFunc = Clojure.var(NAMESPACE, "print");
    private static final IFn flushFunc = Clojure.var(NAMESPACE, "flush");

    private ClojurePrint() {}

    public static void print(String string) {
	printlnFunc.invoke(string);
	flushFunc.invoke();
    }
}
