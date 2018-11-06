[Back to jaxrs-ext](https://github.com/microprofile-extensions/jaxrs-ext/blob/master/README.md)

# Bean validation Exception Handler

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.microprofile-ext.jaxrs-ext/beanvalidation-exception-handler/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.microprofile-ext.jaxrs-ext/beanvalidation-exception-handler)
[![Javadocs](https://www.javadoc.io/badge/org.microprofile-ext.jaxrs-ext/beanvalidation-exception-handler.svg)](https://www.javadoc.io/doc/org.microprofile-ext.jaxrs-ext/beanvalidation-exception-handler)

This Handler handles all Bean validation Exceptions in JAX-RS. It maps Exceptions to HTTP 412 Response code. 
It will add the exception message in the reason header, and the validation errors in the body (XML / JSON)

## Adding Configurable Exception Handler

In ```pom.xml```:
    
```xml

    <dependency>
        <groupId>org.microprofile-ext.jaxrs-ext</groupId>
        <artifactId>beanvalidation-exception-handler</artifactId>
        <version>XXXXX</version>
        <scope>runtime</scope>
    </dependency>

```

## Configuration options

* **jaxrs-ext.includeClassName = false (default)** - if set to true this will include the classname in the reason header message. Default to false, so only the exception message will be in the reason header
* **jaxrs-ext.stacktraceLogLevel = FINEST (default)** - the log level in the system log. Default to FINEST.
