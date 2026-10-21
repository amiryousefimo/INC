package ir.tamin.incomeBank.ws.rest.smartListBank.effectiveDate;

import ir.tamin.framework.ws.rest.ResponseHelper;
import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.service.smartListBank.effectiveDate.SmartListBankEffectiveDateService;
import ir.tamin.incomeBank.ws.rest.Roles;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.*;


/**
 * @author  A_Jamali
 */

@Path("/SmartListBank-EffectiveDate")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class SmartListBankEffectiveDateRESTService {

    @Inject
    private SmartListBankEffectiveDateService service;

    @GET
    @Path("/get-all")
    @RolesAllowed({
            Roles.MALI_SETAD_EDARE_KOL_AMAR_USER,
            Roles.MALI_SETAD_EDARE_KOL_MALI_USER,
            Roles.MALI_SETAD_CHIEF_INSURANCE_OFFICER,
            Roles.MALI_SETAD_DRMD_USER,
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
            Roles.MALI_SETAD_EDARE_KOL_AMAR_USER,
            Roles.MALI_SETAD_EDARE_KOL_MALI_USER,
            Roles.MALI_SETAD_CHIEF_INSURANCE_OFFICER,
            Roles.MALI_SETAD_DRMD_USER,
            Roles.SUPPORTER
    })
    public Response getSum(@QueryParam("filters") FilterWrapper filterWrapper,
                           @Context UriInfo ui,
                           @Context HttpServletRequest request,
                           @Context SecurityContext sc)
            throws Exception {

        return ResponseHelper.ok(service.getSummery(filterWrapper));
    }

    @GET
    @Path("/get-Excel")
    @RolesAllowed({
            Roles.MALI_SETAD_EDARE_KOL_AMAR_USER,
            Roles.MALI_SETAD_EDARE_KOL_MALI_USER,
            Roles.MALI_SETAD_CHIEF_INSURANCE_OFFICER,
            Roles.MALI_SETAD_DRMD_USER,
            Roles.SUPPORTER
    })
    public Response getExcel(@QueryParam("filters") FilterWrapper filterWrapper,
                           @Context HttpServletRequest request,
                           @Context SecurityContext sc)
            throws Exception {

        return Response.ok(service.loadExcel(filterWrapper))
                .header("Content-Type", "application/vnd.ms-excel")
                .build();
    }
}
