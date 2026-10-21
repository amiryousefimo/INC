package ir.tamin.incomeBank.ws.rest.reportingOfficeBasedPayment.department;

import ir.tamin.incomeBank.service.reportingOfficeBasedPayment.department.ReportingOfficeBasedPaymentDepartmentWithParamServiceExcelProviderWithParam;
import ir.tamin.incomeBank.ws.rest.Roles;
import org.apache.poi.ss.usermodel.Workbook;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.OutputStream;


/**
 * @author A_Jamali
 */

@Path("/Reporting-Office-Department-Param-Excel")
@RequestScoped
@Produces("application/vnd.ms-excel")

public class ReportingOfficeBasedPaymentDepartmentWithParamRESTExcelProvider {
    @Inject
    private ReportingOfficeBasedPaymentDepartmentWithParamServiceExcelProviderWithParam service;

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

        try {
            final Workbook workbook = service.loadExcel(startDate, endDate, edareCode);
            StreamingOutput streamingOutput = new StreamingOutput() {
                @Override
                public void write(OutputStream output) throws IOException, WebApplicationException {
                    workbook.write(output);
                }
            };
            return Response.ok(streamingOutput)
                    .header("Content-Type", "application/vnd.ms-excel")
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    @GET
    @Path("/excel-export-date/{startDate}/{endDate}")
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
            @Context HttpServletRequest request,
            @Context SecurityContext sc) throws Exception {

        try {
            final Workbook workbook = service.loadExcel(startDate, endDate,null);
            StreamingOutput streamingOutput = new StreamingOutput() {
                @Override
                public void write(OutputStream output) throws IOException, WebApplicationException {
                    workbook.write(output);
                }
            };
            return Response.ok(streamingOutput)
                    .header("Content-Type", "application/vnd.ms-excel")
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
