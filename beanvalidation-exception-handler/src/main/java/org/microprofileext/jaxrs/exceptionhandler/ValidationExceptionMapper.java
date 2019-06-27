package org.microprofileext.jaxrs.exceptionhandler;

import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import lombok.extern.java.Log;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 * Translate Java validation exceptions to HTTP response
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 * 
 * From version 1.0.1 the default responseCode will be 422: Unprocessable Entity - 
 * The request was well-formed but was unable to be followed due to semantic errors
 * 
 * The initial (version 1.0.0) default version was: 412: Precondition Failed (RFC 7232) - 
 * The server does not meet one of the preconditions that the requester put on the request.
 *
 * See: https://en.wikipedia.org/wiki/List_of_HTTP_status_codes#4xx_Client_errors
 * 
 * You can also now configure the responseCode (overriding the default) by setting the jaxrs-ext.responseCode config value.
 */
@Log
@Provider
@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.TEXT_PLAIN})
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    
    @Inject @ConfigProperty(name = "jaxrs-ext.includeClassName", defaultValue = "false")
    private boolean includeClassName;
    
    @Inject @ConfigProperty(name = "jaxrs-ext.stacktraceLogLevel", defaultValue = "FINEST")
    private String stacktraceLogLevel;
    
    @Inject @ConfigProperty(name = "jaxrs-ext.responseCode", defaultValue = "422")
    private int responseCode;
    
    @Override
    public Response toResponse(ConstraintViolationException constraintViolationException) {
        
        Set<ConstraintViolation<?>> violations = constraintViolationException.getConstraintViolations();
        
        ValidationErrors errors = new ValidationErrors();
        if(violations!=null && !violations.isEmpty()){
            Iterator<ConstraintViolation<?>> i = violations.iterator();
            while (i.hasNext()) {
                ConstraintViolation<?> violation = i.next();
                if(violation!=null){
                    ValidationError ve = new ValidationError();
                    ve.setMessage(violation.getMessage());
                    if(violation.getPropertyPath()!=null)
                        ve.setPath(violation.getPropertyPath().toString());
                    if(violation.getInvalidValue()!=null)
                        ve.setInvalidValue(violation.getInvalidValue().toString());
                    errors.getValidationError().add(ve);
                }
            }
        }else{
           String message = constraintViolationException.getMessage();
           errors.getValidationError().add(new ValidationError(message,EMPTY,EMPTY));
        }
        
        String reason = constructReason(constraintViolationException);
        
        log.log(getLevel(), reason, constraintViolationException);
        
        return Response.status(responseCode)
                .header(REASON, reason).entity(errors).build();
    }
    
    private String constructReason(Throwable exception){
        String premessage = EMPTY;
        if(includeClassName)premessage = OPEN_BRACKET + exception.getClass().getName() + CLOSE_BRACKET;
        return premessage + exception.getMessage();
    }
    
    private Level getLevel(){
        return Level.parse(stacktraceLogLevel);
    }
    
    private static final String REASON = "reason";
    private static final String EMPTY = "";
    private static final String OPEN_BRACKET = "[";
    private static final String CLOSE_BRACKET = "]";
}
