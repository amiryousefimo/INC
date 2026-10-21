package ir.tamin.incomeBank.ws.rest.saba.dateEdkol;

import ir.tamin.framework.ws.rest.ResponseHelper;
import ir.tamin.incomeBank.service.saba.dateEdkol.SabaDateEdkolParamService;
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

@Path("/Saba-Edare-Param")
@RequestScoped
@Produces({MediaType.APPLICATION_JSON})
public class SabaDateEdkolParamREST {

    @Inject
    private SabaDateEdkolParamService service;

    /************************Search By Parameters************************************************/


    @GET
    @Path("/get-all/{startDate}/{endDate}/{edareCode}")
    @RolesAllowed({
            Roles.MALI_SETAD_EDARE_KOL_AMAR_USER,
            Roles.MALI_EDARE_KOL_AMAR_USER,
            Roles.MALI_EDARE_KOL_CHIEF_INSURANCE_PROVINCE,
            Roles.MALI_EDARE_KOL_DRMD_BOSS,
            Roles.MALI_EDARE_KOL_MALI_BOSS,
            Roles.MALI_SETAD_EDARE_KOL_MALI_USER,
            Roles.MALI_EDARE_KOL_PROVINCE_MANAGER,
            Roles.MALI_SETAD_CHIEF_INSURANCE_OFFICER,
            Roles.MALI_SETAD_DRMD_USER,
            Roles.SUPPORTER

    })

    public Response getAll(
            @PathParam("startDate") String startDate,
            @PathParam("endDate") String endDate,
            @PathParam("edareCode") String edareCode,
            @Context HttpServletRequest request,
            @Context SecurityContext sc) throws Exception {

        return ResponseHelper.ok(service.getSetadByDateRange(startDate, endDate, edareCode));
    }


}
