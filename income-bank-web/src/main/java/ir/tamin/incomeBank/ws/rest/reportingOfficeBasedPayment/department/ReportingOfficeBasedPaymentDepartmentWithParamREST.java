package ir.tamin.incomeBank.ws.rest.reportingOfficeBasedPayment.department;

import ir.tamin.framework.ws.rest.ResponseHelper;
import ir.tamin.incomeBank.service.reportingOfficeBasedPayment.department.ReportingOfficeBasedPaymentDepartmentWithParamService;
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

@Path("/Reporting-Office-Department-Param")
@RequestScoped
@Produces({MediaType.APPLICATION_JSON})
public class ReportingOfficeBasedPaymentDepartmentWithParamREST {

    @Inject
    private ReportingOfficeBasedPaymentDepartmentWithParamService service;

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
            Roles.SUPPORTER,

    })

    public Response getAll(
            @PathParam("startDate") String startDate,
            @PathParam("endDate") String endDate,
            @PathParam("edareCode") String edareCode,
            @Context HttpServletRequest request,
            @Context SecurityContext sc) throws Exception {

        return ResponseHelper.ok(service.getBranchByDateRange(startDate, endDate, edareCode));
    }


    /************************Excel Handler************************************************/
//    @GET
//    @Path("/excel-export-date/{startDate}/{endDate}/{edareCode}/{brchCode}")
//    @Produces("application/vnd.ms-excel")
//    @RolesAllowed({
//            Roles.MALI_SETAD_EDARE_KOL_AMAR_USER,
//            Roles.MALI_EDARE_KOL_AMAR_USER,
//            Roles.MALI_EDARE_KOL_CHIEF_INSURANCE_PROVINCE,
//            Roles.MALI_EDARE_KOL_DRMD_BOSS,
//            Roles.MALI_EDARE_KOL_MALI_BOSS,
//            Roles.MALI_SETAD_EDARE_KOL_MALI_USER,
//            Roles.MALI_EDARE_KOL_PROVINCE_MANAGER,
//            Roles.MALI_SETAD_CHIEF_INSURANCE_OFFICER,
//            Roles.MALI_SETAD_DRMD_USER,
//            Roles.SUPPORTER,
//
//    })

//    public Response excelReportHeader(
//            @PathParam("startDate") String startDate,
//            @PathParam("endDate") String endDate,
//            @PathParam("edareCode") String edareCode,
//            @PathParam("brchCode") String brchCode,
//            @Context HttpServletRequest request,
//            @Context SecurityContext sc) throws Exception {
//
//        return ResponseHelper.ok(service.loadExcel(startDate, endDate, edareCode));
//    }

    @GET
    @Path("/excel-export-date/{startDate}/{endDate}/{edareCode}")
    @Produces("application/vnd.ms-excel")
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
            Roles.SUPPORTER,

    })

    public Response excelReportHeader(
            @PathParam("startDate") String startDate,
            @PathParam("endDate") String endDate,
            @PathParam("edareCode") String edareCode,
            @Context HttpServletRequest request,
            @Context SecurityContext sc) throws Exception {

        return ResponseHelper.ok(service.loadExcel(startDate, endDate, edareCode));
    }

//    @GET
//    @Path("/excel-export-date/{startDate}/{endDate}")
//    @Produces("application/vnd.ms-excel")
//    @RolesAllowed({
//            Roles.MALI_SETAD_EDARE_KOL_AMAR_USER,
//            Roles.MALI_EDARE_KOL_AMAR_USER,
//            Roles.MALI_EDARE_KOL_CHIEF_INSURANCE_PROVINCE,
//            Roles.MALI_EDARE_KOL_DRMD_BOSS,
//            Roles.MALI_EDARE_KOL_MALI_BOSS,
//            Roles.MALI_SETAD_EDARE_KOL_MALI_USER,
//            Roles.MALI_EDARE_KOL_PROVINCE_MANAGER,
//            Roles.MALI_SETAD_CHIEF_INSURANCE_OFFICER,
//            Roles.MALI_SETAD_DRMD_USER,
//            Roles.SUPPORTER,
//
//    })
//
//    public Response excelReportHeader(
//            @PathParam("startDate") String startDate,
//            @PathParam("endDate") String endDate,
//            @Context HttpServletRequest request,
//            @Context SecurityContext sc) throws Exception {
//
//        return ResponseHelper.ok(service.loadExcel(startDate, endDate, null));
//    }

}
