package com.zensol.clojure.util;

import clojure.java.api.Clojure;
import clojure.lang.IFn;

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
