# JAX-RS Extensions | Bean validation Exception Handler

This Handler handles all Bean validation Exceptions in JAX-RS. It maps Exceptions to HTTP 412 Response codes. 
It will add the exception message in the reason header, and the validation errors in the body (XML / Json)

## Adding Configurable Exception Handler

In ```pom.xml```
    
    ```xml
    <dependency>
        <groupId>org.microprofile-ext.jaxrs-ext</groupId>
        <artifactId>beanvalidation-exception-handler</artifactId>
        <version>XXXXX</version>
    </dependency>
    ```

## Configuration options

* **jaxrs-ext.includeClassName = false (default)** - if set to true this will include the classname in the reason header message. Default to false, so only the exception message will be in the reason header
* **jaxrs-ext.stacktraceLogLevel = FINEST (default)** - the log level in the system log. Default to FINEST.