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

import java.io.PrintWriter;
import java.io.Serializable;

import clojure.lang.IFn;
import com.zensols.clojure.util.ClojurePrint;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

/**
 * <p>The Log4j2 appender class that dumps log events to the grabbed REPL {@link
 * PrintWriter} (<tt>*out*</tt> symbol) class.  Log4j2 only uses one instance
 * of this class, so once {@link #setWriter(PrintWriter)} is called, it
 * forwards the log events only to this writer instance.</p>
 *
 * <p>You can also call this class with the Clojure <tt>println</tt> function
 * (or any function that takes a single string argument), which will be invoked
 * for each incoming log event from Log4j2 if set.</p>
 *
 * <p>To use this class, either call {@link #setWriter} and/or {@link
 * #setPrintFn}.</p>
 *
 * To use this class, you have to call it from the REPL.
 *
 * @see <a href="https://github.com/plandes/clj-append#repl">REPL configuration</a>
 * @see com.zensols.clojure.util.ClojurePrint
 * @author Paul Landes
 */
@Plugin(name = "Clojure", category = "Core", elementType = "appender", printObject = true)
public class ClojureAppender extends AbstractAppender {
    private static PrintWriter writer;
    private static IFn printFn;

    private ClojureAppender(String name, Filter filter,
			    Layout<? extends Serializable> layout) {
        super(name, filter, layout);
    }

    private ClojureAppender(String name, Filter filter,
			    Layout<? extends Serializable> layout,
			    boolean ignoreExceptions) {
        super(name, filter, layout, ignoreExceptions);
    }

    /** @return the writer used to dump log events. */
    public static PrintWriter getWriter() {
	return writer;
    }

    /**
     * @param writer used to dump log events
     */
    public static void setWriter(PrintWriter writer) {
	ClojureAppender.writer = writer;
    }

    /** @return the Clojure println function used for log event outuput */
    public static IFn getPrintFn() {
	return printFn;
    }

    /** @param printFn the Clojure println function used for log event outuput */
    public static void setPrintFn(IFn printFn) {
	ClojureAppender.printFn = printFn;
    }

    @PluginFactory
    public static ClojureAppender createAppender(
            @PluginElement("Layout") Layout<? extends Serializable> layout,
            @PluginElement("Filters") final Filter filter,
            @PluginAttribute("name") final String name) {

        if (name == null) {
            LOGGER.error("No name provided for ClojureAppender");
            return null;
        }

        if (layout == null) {
            LOGGER.error("Pattern layout not provided");
            return null;
        }

        return new ClojureAppender(name, filter, layout);
    }

    public void append(LogEvent logEvent) {
        Layout layout = getLayout();
	String logLine = getLayout().toSerializable(logEvent).toString();
	PrintWriter writer = ClojureAppender.writer;

	if (writer != null) {
	    writer.print(logLine);
	    writer.flush();
	} else if (printFn == null) {
	    ClojurePrint.print(logLine);
	}

	if (printFn != null) {
	    printFn.invoke(logLine);
	}
    }
}
