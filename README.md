Log4j2 Logger that appends using print and println.
===================================================

Adding this package to your project enables
[Log4j2](http://logging.apache.org/log4j/2.x/) to log to the
[Cider](https://github.com/clojure-emacs/cider) REPL.


Obtaining
---------
In your `project.clj` file, add:

```clojure
[com.zensols/clojappend "1.0.2"]
```


Using
-----

# Configuration

Create a log4j2.xml in your `resources` or `test-resources` directory:
```xml
<?xml version="1.0" encoding="UTF-8"?>

<configuration packages="com.zensols.clojure.log"
               status="OFF" monitorInterval="5">
    <appenders>
        <clojure name="repl">
            <!--patternLayout pattern="%c{1}: %m%n"/-->
	    <patternLayout pattern="%d{yyyy-MM-dd HH:mm:ss.S}: %c{1}: %m%n"/>
        </clojure>
    </appenders>

    <loggers>
        <logger name="com.example" level="info"/>
        <root level="warn">
            <appenderRef ref="repl"/>
        </root>
    </loggers>
</configuration>
```

# REPL
Each time you invoke the REPL you need to give the logger the REPL's `*out*` reference:
```clojure
user> (com.zensols.clojure.log.ClojureAppender/setWriter *out*)
```


License
-------
GNU Lesser General Public License, Version 3.0
