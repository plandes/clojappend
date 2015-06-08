package com.zensol.clojure.log;

import java.io.Serializable;

import com.zensol.clojure.util.ClojurePrint;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;

@Plugin(name = "Clojure", category = "Core", elementType = "appender", printObject = true)
public class ClojureAppender extends AbstractAppender {
    private ClojureAppender(String name, Filter filter, Layout<? extends Serializable> layout) {
        super(name, filter, layout);
    }

    private ClojureAppender(String name, Filter filter, Layout<? extends Serializable> layout, boolean ignoreExceptions) {
        super(name, filter, layout, ignoreExceptions);
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

	ClojurePrint.print(logLine);
    }
}
