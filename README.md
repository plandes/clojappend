Log4j2 Logger that appends using print and println.
===================================================

Adding this package to your project enables
[Log4j2](http://logging.apache.org/log4j/2.x/) to log to the
[Cider](https://github.com/clojure-emacs/cider) REPL.

Obtaining
---------
See [dependencies](https://plandes.github.io/clj-append/dependencies.html)


Using
-----

# Configuration

Create a log4j2.xml in your `resources` or `test-resources` directory
specifying the appender in the configuration element:
```xml
<?xml version="1.0" encoding="UTF-8"?>

<configuration packages="com.zensols.clojure.log"
               status="OFF" monitorInterval="5">
    <appenders>
        <clojure name="repl">
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

This project includes slf4j and could conflict with other dependencies, in
which case you'll want to exclude them (see below).  You also won't need this
package outside the REPL, in which case you could add it as a `dev` profile.
In your `project.clj`:
```clojure
(defproject example/project "0.1.0-SNAPSHOT"
  :exclusions [org.slf4j/slf4j-log4j12
               ch.qos.logback/logback-classic]
  :profiles {:dev {:dependencies
                   [com.zensols/clj-append "1.0.3"]}})
```

# REPL
Each time you invoke the REPL you need to give the logger the REPL's `*out*` reference:
```clojure
user> (com.zensols.clojure.log.ClojureAppender/setWriter *out*)
```

This is a drag, so you could put this in your `~/.emacs.d/init.el` file:
```lisp
(defun clj-append-clojure-connected ()
  (let ((buf (first
          (remove* nil (buffer-list)
               :test-not #'(lambda (a b)
                     (string-match "^\*cider-repl" (buffer-name b)))))))
    (save-excursion
      (set-buffer buf)
      (goto-char (point-max))
      (insert "(com.zensol.clojures.log.ClojureAppender/setWriter *out*)")
      (cider-repl-return))))

(add-hook 'cider-connected-hook 'clj-append-clojure-connected)
```

Documentation
-------------
More [documentation](https://plandes.github.io/clj-append/):
* [Javadoc](https://plandes.github.io/clj-append/apidocs/index.html)
* [Dependencies](https://plandes.github.io/clj-append/dependencies.html)

License
-------
Copyright © 2016 Paul Landes

GNU Lesser General Public License, Version 3.0
