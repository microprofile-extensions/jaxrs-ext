[Back to jaxrs-ext](https://github.com/microprofile-extensions/jaxrs-ext/blob/master/README.md)

# Configurable Exception Handler

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.microprofile-ext.jaxrs-ext/configurable-exception-handler/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.microprofile-ext.jaxrs-ext/configurable-exception-handler)
[![Javadocs](https://www.javadoc.io/badge/org.microprofile-ext.jaxrs-ext/configurable-exception-handler.svg)](https://www.javadoc.io/doc/org.microprofile-ext.jaxrs-ext/configurable-exception-handler)

This Handler handles all Runtime Exceptions in JAX-RS. It maps Exceptions to HTTP Response codes. It will add the exception message in the reason header.

## Adding Configurable Exception Handler

In ```pom.xml```:
    
```xml

    <dependency>
        <groupId>org.microprofile-ext.jaxrs-ext</groupId>
        <artifactId>configurable-exception-handler</artifactId>
        <version>XXXXX</version>
        <scope>runtime</scope>
    </dependency>

```

## Configuration options

* **jaxrs-ext.includeClassName = false (default)** - if set to true this will include the classname in the reason header message. Default to false, so only the exception message will be in the reason header
* **jaxrs-ext.includeStacktrace = false (default)** - if set to true this will include the stacktrace in the body of the response. Default to false, so the body is empty.
* **jaxrs-ext.stacktraceLogLevel = FINEST (default)** - the log level in the system log. Default to FINEST.

## Default mappings

Here some default mappings. You can add more mappings in your own config, or override these with your own config.(See https://en.wikipedia.org/wiki/List_of_HTTP_status_codes)

- **503 Service Unavailable** The server is currently unavailable (because it is overloaded or down for maintenance). Generally, this is a temporary state.
  - ```org.eclipse.microprofile.faulttolerance.exceptions.CircuitBreakerOpenException/mp-jaxrs-ext/statuscode=503```

- **504 Gateway Timeout** The server was acting as a gateway or proxy and did not receive a timely response from the upstream server.
  - ```org.eclipse.microprofile.faulttolerance.exceptions.TimeoutException/mp-jaxrs-ext/statuscode=504```

- **401 Unauthorized (RFC 7235)** Similar to 403 Forbidden, but specifically for use when authentication is required and has failed or has not yet been provided. The response must include a WWW-Authenticate header field containing a challenge applicable to the requested resource.
  - ```javax.ejb.AccessLocalException/mp-jaxrs-ext/statuscode=401```
  - ```javax.ws.rs.NotAuthorizedException/mp-jaxrs-ext/statuscode=401```

- **409 Conflict** Indicates that the request could not be processed because of conflict in the request, such as an edit conflict between multiple simultaneous updates.
  - ```javax.persistence.NonUniqueResultException/mp-jaxrs-ext/statuscode=409```

## Adding your own mappings

In your config (MicroProfile Config API) add your own mapping (examples above)

*exceptionClassName/mp-jaxrs-ext/statuscode = XXX*
