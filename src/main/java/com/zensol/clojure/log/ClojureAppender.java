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

    public static PrintWriter getWriter() {
	return writer;
    }

    public static void setWriter(PrintWriter writer) {
	ClojureAppender.writer = writer;
    }

    public static IFn getPrintFn() {
	return printFn;
    }

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
