package ir.tamin.incomeBank.ws.rest.soratEfDate;

import ir.tamin.framework.ws.rest.ResponseHelper;
import ir.tamin.incomeBank.service.soratEfDate.SoratEfDateDailyService;
import ir.tamin.incomeBank.ws.rest.Roles;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;


/**
 * @author A_Jamali
 */

@Path("/Sorat-Ef-Date-Daily")
@RequestScoped
@Produces({MediaType.APPLICATION_JSON})
public class SoratEfDateDailyREST {

    @Inject
    private SoratEfDateDailyService service;

    @GET
    @Path("/get-all/{startDate}/{endDate}")
    @RolesAllowed({
            Roles.MALI_SETAD_EDARE_KOL_AMAR_USER,
            Roles.MALI_SETAD_EDARE_KOL_MALI_USER,
            Roles.MALI_SETAD_CHIEF_INSURANCE_OFFICER,
            Roles.MALI_SETAD_DRMD_USER,
            Roles.SUPPORTER
    })

    public Response getAll(
            @PathParam("startDate") String startDate,
            @PathParam("endDate") String endDate,
            @Context HttpServletRequest request,
            @Context SecurityContext sc) throws Exception {

        return ResponseHelper.ok(service.getBranchByDateRange(startDate, endDate));
    }

}
