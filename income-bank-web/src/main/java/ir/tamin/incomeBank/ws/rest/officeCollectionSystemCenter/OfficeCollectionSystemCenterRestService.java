package ir.tamin.incomeBank.ws.rest.officeCollectionSystemCenter;

import ir.tamin.framework.ws.rest.ResponseHelper;
import ir.tamin.incomeBank.service.officeCollectionSystemCenter.OfficeCollectionSystemCenterService;
import ir.tamin.incomeBank.ws.rest.Roles;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 * @author f_mousavi
 */
@Path("/office-collection-system-center")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class OfficeCollectionSystemCenterRestService {
    @Inject
    OfficeCollectionSystemCenterService officeCollectionSystemCenterService;

    @POST
    @Path("/get-all/{startDate}/{endDate}/{edareCode}/{brchCode}/{brhFlag}/{daysFlag}/{bnkFlag}/{bankCode}/{orderNumber}")

    @RolesAllowed({
            Roles.MALI_BRANCH_ACCOUNTANT,
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
    public Response getAll(@PathParam("startDate") String startDate,
                           @PathParam("endDate") String endDate,
                           @PathParam("edareCode") String edareCode,
                           @PathParam("brchCode") String brchCode,
                           @PathParam("brhFlag") String brhFlag,
                           @PathParam("daysFlag") String daysFlag,
                           @PathParam("bnkFlag") String bnkFlag,
                           @PathParam("bankCode") String bankCode,
                           @PathParam("orderNumber") String orderNumber,
                           @Context UriInfo ui,
                           @Context HttpServletRequest request,
                           @Context SecurityContext sc)
            throws Exception {
        return ResponseHelper.ok(officeCollectionSystemCenterService.getAll(startDate, endDate, edareCode,brchCode, brhFlag, daysFlag, bnkFlag, bankCode, orderNumber));
    }


    @GET
    @Path("/excel-export/{startDate1}/{endDate}/{orderNumber}/{edareCode}/{brchCode}/{bankCode}/{brhFlag}/{daysFlag}/{bnkFlag}")
    @RolesAllowed({
            Roles.MALI_BRANCH_ACCOUNTANT,
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
    public Response getAll(@PathParam("startDate1") String startDate,
                    @PathParam("endDate") String endDate,
                    @PathParam("orderNumber") String orderNumber,
                    @PathParam("edareCode") String edareCode,
                    @PathParam("brchCode") String brchCode,
                    @PathParam("bankCode") String bankCode,
                    @PathParam("brhFlag") String brhFlag,
                    @PathParam("daysFlag") String daysFlag,
                    @PathParam("bnkFlag") String bnkFlag,
                    @Context HttpServletRequest request,
                    @Context SecurityContext sc)
            throws Exception {
 return Response.ok(officeCollectionSystemCenterService.loadExcel(startDate, endDate, orderNumber, edareCode,brchCode, bankCode, brhFlag, daysFlag, bnkFlag))
        .header("Content-Type", "application/vnd.ms-excel")
                .build();
}

    }