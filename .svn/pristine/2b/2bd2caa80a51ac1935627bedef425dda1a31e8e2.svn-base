package ir.tamin.incomeBank.ws.rest.payment3070.account503branch;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import ir.tamin.framework.data.validation.Validated;
import ir.tamin.incomeBank.service.payment3070.account503branch.Account503BranchParamExcelService;
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

@Path("/Account-503-Branch-Param-Excel")
@RequestScoped
@Produces("application/vnd.ms-excel")
@Validated
@Tag(name = "Create Excel Report Account503Branch",
        description = "this REST Create A list as Excel format from Account503Branch object")
public class Account503BranchWithParamExcelProviderREST {
@Inject
    private Account503BranchParamExcelService service;


    /**
     *
     * @param startDate given the date from user for AFTER operator
     * @param endDate given the date from user for BEFORE operator
     * @param edareCode give the Branch Code From User
     * @param brchCode give the Branch Code From User
     * @return a new excel report of a dto list
     */

    @GET
    @Path("/excel-export/{startDate}/{endDate}/{edareCode}/{brchCode}")
    @Produces("application/vnd.ms-excel")
    @Operation(
            summary = " Excel Report Account503Branch \"vwdrmd_gl3070_dailyBRCH \"",
            description = "Get Search report from  Account503Branch with Parameter"
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
            Roles.SUPPORTER,
            Roles.INCOME_BANK_BRANCH_FANI,
            Roles.INCOME_BANK_OSTAN_FANI,
            Roles.INCOME_BANK_EDARE_KOL_FANI,
            Roles.INCOME_BANK_BRANCH_NAMNEVISI,
            Roles.INCOME_BANK_OSTAN_NAMNEVISI,
            Roles.INCOME_BANK_EDARE_KOL_NAMNEVISI
    })

    public Response excelReportHeader(
            @PathParam("startDate") @Pattern(regexp = "(^$|[0-9]{8})", message = "Date must be 8 digits") String startDate,
            @PathParam("endDate") @Pattern(regexp = "(^$|[0-9]{8})", message = "Date must be 8 digits") String endDate,
            @PathParam("edareCode") @Pattern(regexp = "^\\d{4}$|^(-1)$", message = "Edare Code must be 4 digits") String edareCode,
            @PathParam("brchCode") @Pattern(regexp = "^\\d{4}$|^(-1)$", message = "Branch Code must be 4 digits") String brchCode,
            @Context HttpServletRequest request,
            @Context SecurityContext sc) throws Exception {

        try {
            final Workbook workbook;
            workbook = service.loadExcel(startDate, endDate, edareCode, brchCode);
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
