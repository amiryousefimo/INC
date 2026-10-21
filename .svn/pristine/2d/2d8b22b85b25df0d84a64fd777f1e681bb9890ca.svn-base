package ir.tamin.incomeBank.ws.rest.paymentWorkers.edare;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import ir.tamin.framework.ws.rest.ResponseHelper;
import ir.tamin.incomeBank.service.paymentWorkers.branch.PaymentWorkersBranchWithParamService;
import ir.tamin.incomeBank.service.paymentWorkers.edare.PaymentWorkersEdareWithParamService;
import ir.tamin.incomeBank.ws.rest.Roles;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Pattern;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 * @author m_goharriz
 */

@Path("/Payment-Workers-Edare-Param")
@RequestScoped
@Produces({MediaType.APPLICATION_JSON})
public class PaymentWorkersEdareWithParamREST {

    @Inject
    private PaymentWorkersEdareWithParamService service;

    @GET
    @Path("/get-all/{startDate}/{endDate}/{edareCode}")

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

            Roles.MALI_BRANCH_ACCOUNTANT,
            Roles.MALI_BRANCH_BOSS,
            Roles.MALI_BRANCH_CHIEF_INSURANCE_OFFICER,
            Roles.MALI_BRANCH_ACCOUNTANT_USER,
            Roles.MALI_BRANCH_DRMD_USER,

            Roles.INCOME_BANK_EDARE_KOL_FANI,
            Roles.INCOME_BANK_BRANCH_NAMNEVISI,
            Roles.INCOME_BANK_OSTAN_NAMNEVISI,
            Roles.INCOME_BANK_EDARE_KOL_NAMNEVISI,
            //Roles.ALL_USERS   //TODO---------------------

    })

    public Response getAll(
            @PathParam("startDate") @Pattern(regexp = "(^$|[0-9]{8})", message = "Date must be 8 digits") String startDate,
            @PathParam("endDate") @Pattern(regexp = "(^$|[0-9]{8})", message = "Date must be 8 digits") String endDate,
            @PathParam("edareCode") @Pattern(regexp = "^\\d{4}$|^(-1)$", message = "Edare Code must be 4 digits") String edareCode,

            @Context HttpServletRequest request,
            @Context SecurityContext sc) throws Exception {

        return ResponseHelper.ok(service.getBranchByDateRange(startDate, endDate, edareCode));
    }
}
