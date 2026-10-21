package ir.tamin.incomeBank.ws.rest.smartlist.edare;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import ir.tamin.framework.data.validation.Validated;
import ir.tamin.incomeBank.service.smartlist.edare.ExcelServiceForSmartListEdare;
import ir.tamin.incomeBank.ws.rest.Roles;
import org.apache.poi.ss.usermodel.Workbook;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Pattern;
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

@Path("/Smart-List-Edare-Excel")
@RequestScoped
@Produces("application/vnd.ms-excel")
@Validated
@Tag(name = "Create Excel Report SmartListEdare",
        description = "this REST Create A list as Excel format from SmartListEdare object")
public class SmartListEdareWithParamExcelProviderREST {
    @Inject
    private ExcelServiceForSmartListEdare service;


    /**
     * @param startDate given the date from user for AFTER operator
     * @param endDate   given the date from user for BEFORE operator
     * @param edareCode give the Branch Code From User
     * @return a new excel report of a dto list
     */

    @GET
    @Path("/excel-export/{startDate}/{endDate}/{edareCode}")
    @Produces("application/vnd.ms-excel")
    @Operation(
            summary = " Excel Report SmartListEdare \"vwdrmd_gl_dailyedkol\"",
            description = "Get Search report from  SmartListEdare with Parameter"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Http Status OK with FilterWrapper"),
            @ApiResponse(responseCode = "401", description = "client has invalid or expired token"),
            @ApiResponse(responseCode = "500", description = "Internal server error may be happen  ")
    })
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
            Roles.INCOME_BANK_EDARE_KOL_FANI,
            Roles.SUPPORTER
    })

    public Response excelReportHeader(
            @PathParam("startDate") @Pattern(regexp = "(^$|[0-9]{8})", message = "Date must be 8 digits") String startDate,
            @PathParam("endDate") @Pattern(regexp = "(^$|[0-9]{8})", message = "Date must be 8 digits") String endDate,
            @PathParam("edareCode") @Pattern(regexp = "^\\d{4}$|^(-1)$", message = "Edare Code must be 4 digits") String edareCode,
            @Context HttpServletRequest request,
            @Context SecurityContext sc) throws Exception {

        try {
            final Workbook workbook;
            workbook = service.loadExcel(startDate, endDate, edareCode);
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
