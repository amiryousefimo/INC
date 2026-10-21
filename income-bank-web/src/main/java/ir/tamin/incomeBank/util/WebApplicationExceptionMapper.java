package ir.tamin.incomeBank.util;

import ir.tamin.framework.cdi.util.MessageBundle;
import ir.tamin.framework.core.util.Bundle;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {

    @Inject
    @MessageBundle
    Bundle messageBundle;

    @Override
    public Response toResponse(WebApplicationException exception) {
        String message = null;
        if (!"".equals(exception.getMessage())) {
            message = exception.getMessage();
        } else {
            message = messageBundle.getProperty("financialDoc.filterIsEmpty");
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .header("X-Internal-Server-Error", message)
                .entity(exception.getMessage())
                .build();
    }
}
