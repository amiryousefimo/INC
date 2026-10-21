package ir.tamin.incomeBank.ws.rest.contradictionReport;

import ir.tamin.framework.ws.rest.ResponseHelper;
import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.service.contradictionReport.ContradictionReport1Service;
import ir.tamin.incomeBank.ws.rest.Roles;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 *
 * @author A_Jamali
 */
@Path("/contradiction-report1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class ContradictionReport1REST {

    @Inject
    ContradictionReport1Service service;

    @GET
    @Path("/get-all")
    @RolesAllowed({
            Roles.MALI_SETAD_DRMD_USER,
            Roles.MALI_SETAD_CHIEF_INSURANCE_OFFICER,

            Roles.MALI_BRANCH_ACCOUNTANT,
            Roles.MALI_BRANCH_ACCOUNTANT_USER,
            Roles.MALI_BRANCH_BOSS,
            Roles.MALI_BRANCH_DRMD_USER,

            Roles.MALI_BRANCH_CHIEF_INSURANCE_OFFICER,

            Roles.MALI_SETAD_EDARE_KOL_MALI_USER,
            Roles.MALI_SETAD_EDARE_KOL_AMAR_USER,
            Roles.MALI_EDARE_KOL_AMAR_USER,
            Roles.MALI_EDARE_KOL_CHIEF_INSURANCE_PROVINCE,
            Roles.MALI_EDARE_KOL_DRMD_BOSS,
            Roles.MALI_EDARE_KOL_MALI_BOSS,
            Roles.MALI_EDARE_KOL_PROVINCE_MANAGER,

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
            Roles.MALI_SETAD_DRMD_USER,
            Roles.MALI_SETAD_CHIEF_INSURANCE_OFFICER,

            Roles.MALI_BRANCH_ACCOUNTANT,
            Roles.MALI_BRANCH_ACCOUNTANT_USER,
            Roles.MALI_BRANCH_BOSS,
            Roles.MALI_BRANCH_DRMD_USER,

            Roles.MALI_BRANCH_CHIEF_INSURANCE_OFFICER,

            Roles.MALI_SETAD_EDARE_KOL_MALI_USER,
            Roles.MALI_SETAD_EDARE_KOL_AMAR_USER,
            Roles.MALI_EDARE_KOL_AMAR_USER,
            Roles.MALI_EDARE_KOL_CHIEF_INSURANCE_PROVINCE,
            Roles.MALI_EDARE_KOL_DRMD_BOSS,
            Roles.MALI_EDARE_KOL_MALI_BOSS,
            Roles.MALI_EDARE_KOL_PROVINCE_MANAGER,

            Roles.SUPPORTER

    })

    public Response getAll(@QueryParam("filters") FilterWrapper filterWrapper,
                           @Context UriInfo ui,
                           @Context HttpServletRequest request,
                           @Context SecurityContext sc)
            throws Exception {

        return ResponseHelper.ok(service.getSummery(filterWrapper));
    }

    @GET
    @Path("/get-Excel")
    @RolesAllowed({
            Roles.MALI_SETAD_DRMD_USER,
            Roles.MALI_SETAD_CHIEF_INSURANCE_OFFICER,

            Roles.MALI_BRANCH_ACCOUNTANT,
            Roles.MALI_BRANCH_ACCOUNTANT_USER,
            Roles.MALI_BRANCH_BOSS,
            Roles.MALI_BRANCH_DRMD_USER,

            Roles.MALI_BRANCH_CHIEF_INSURANCE_OFFICER,

            Roles.MALI_SETAD_EDARE_KOL_MALI_USER,
            Roles.MALI_SETAD_EDARE_KOL_AMAR_USER,
            Roles.MALI_EDARE_KOL_AMAR_USER,
            Roles.MALI_EDARE_KOL_CHIEF_INSURANCE_PROVINCE,
            Roles.MALI_EDARE_KOL_DRMD_BOSS,
            Roles.MALI_EDARE_KOL_MALI_BOSS,
            Roles.MALI_EDARE_KOL_PROVINCE_MANAGER,

            Roles.SUPPORTER

    })
    public Response getAll(@QueryParam("filters") FilterWrapper filterWrapper,
                           @Context HttpServletRequest request,
                           @Context SecurityContext sc)
            throws Exception {

        return Response.ok(service.loadExcel(filterWrapper))
                .header("Content-Type", "application/vnd.ms-excel")
                .build();
    }
}
