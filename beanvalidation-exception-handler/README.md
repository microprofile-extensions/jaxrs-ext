[Back to jaxrs-ext](https://github.com/microprofile-extensions/jaxrs-ext/blob/master/README.md)

# Bean validation Exception Handler

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.microprofile-ext.jaxrs-ext/beanvalidation-exception-handler/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.microprofile-ext.jaxrs-ext/beanvalidation-exception-handler)
[![Javadocs](https://www.javadoc.io/badge/org.microprofile-ext.jaxrs-ext/beanvalidation-exception-handler.svg)](https://www.javadoc.io/doc/org.microprofile-ext.jaxrs-ext/beanvalidation-exception-handler)

This Handler handles all Bean validation Exceptions in JAX-RS. It maps Exceptions to HTTP 422 Response code. 
It will add the exception message in the reason header, and the validation errors in the body (XML / JSON)

### Response Codes

From version 1.0.1 the default `responseCode` will be `422`: 
**Unprocessable Entity**:
The request was well-formed but was unable to be followed due to semantic errors

The initial (version 1.0.0) default version was: `412`:
**Precondition Failed (RFC 7232):** 
The server does not meet one of the preconditions that the requester put on the request.

See: https://en.wikipedia.org/wiki/List_of_HTTP_status_codes#4xx_Client_errors

You can also now configure the responseCode (overriding the default) by setting the `jaxrs-ext.responseCode` config value.

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
* **jaxrs-ext.responseCode = 422 (default)** - the HTTP responseCode to use.