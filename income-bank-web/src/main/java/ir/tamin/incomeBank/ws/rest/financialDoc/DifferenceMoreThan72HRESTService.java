package ir.tamin.incomeBank.ws.rest.financialDoc;

import ir.tamin.framework.ws.rest.ResponseHelper;
import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.service.financialDoc.Collection3070Service;
import ir.tamin.incomeBank.service.financialDoc.DifferenceMoreThan72HService;
import ir.tamin.incomeBank.ws.rest.Roles;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/differenceMoreThan72H")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class DifferenceMoreThan72HRESTService {

    @Inject
    DifferenceMoreThan72HService service;

    @GET
    @Path("/get-all")
    @RolesAllowed({
            Roles.MALI_SETAD_EDARE_KOL_MALI_USER,
            Roles.SUPPORTER
    })
    public Response getAll(@QueryParam("filter") FilterWrapper filterWrapper,
                           @QueryParam("start") Integer start,
                           @QueryParam("limit") Integer limit,
                           @QueryParam("sort") SortWrapper sort,
                           @Context UriInfo ui,
                           @Context HttpServletRequest request,
                           @Context SecurityContext sc)
            throws Exception {

        return ResponseHelper.ok(service.getAll(filterWrapper, start, limit, sort));
    }



    @GET
    @Path("/get-sum")
    @RolesAllowed({
            Roles.MALI_SETAD_EDARE_KOL_MALI_USER,
            Roles.SUPPORTER
    })
    public Response getSum(@QueryParam("filter") FilterWrapper filterWrapper,
                           @Context UriInfo ui,
                           @Context HttpServletRequest request,
                           @Context SecurityContext sc)
            throws Exception {

        return ResponseHelper.ok(service.getSummary(filterWrapper));
    }


}
