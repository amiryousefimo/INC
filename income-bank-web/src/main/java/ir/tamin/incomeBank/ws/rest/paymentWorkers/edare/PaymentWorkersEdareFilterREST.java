package ir.tamin.incomeBank.ws.rest.paymentWorkers.edare;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import ir.tamin.framework.ws.rest.ResponseHelper;
import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.service.paymentWorkers.branch.PaymentWorkersBranchService;
import ir.tamin.incomeBank.service.paymentWorkers.edare.PaymentWorkersEdareService;
import ir.tamin.incomeBank.ws.rest.Roles;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.*;


/**
 * @author m_goharriz
 */


@Path("/Payment-Workers-Edare-Filter")
@RequestScoped
@Produces({MediaType.APPLICATION_JSON, "application/vnd.ms-excel"})
public class PaymentWorkersEdareFilterREST {
    @Inject
    private PaymentWorkersEdareService service;




    @GET
    @Path("/get-all")
    /*@Operation(
            summary = " Get A List From PaymentWorkersBranch \"VW_PAYKIND_BLD_BRCH  \"",
            description = "Get Search report from  PaymentWorkersBranch with Filter"
    )*/
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
            Roles.INCOME_BANK_EDARE_KOL_NAMNEVISI


  /*          Roles.ALL_USERS*/

    })
    public Response getTelSumDaily(@QueryParam("filter") FilterWrapper filterWrapper,
                                   @QueryParam("start") Integer start,
                                   @QueryParam("limit") Integer limit,
                                   @QueryParam("sort") SortWrapper sort,
                                   @Context SecurityContext sc) throws Exception {
        return ResponseHelper.ok(service.getAll(filterWrapper, start, limit, sort));
    }


    @GET
    @Path("/get-sum")
    @Operation(
            summary = " Get A Size List From SmartListEdare \"vwletter_brch \"",
            description = "Get A Size List From  SmartListEdare with Filter"
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

            Roles.MALI_BRANCH_ACCOUNTANT,
            Roles.MALI_BRANCH_BOSS,
            Roles.MALI_BRANCH_CHIEF_INSURANCE_OFFICER,
            Roles.MALI_BRANCH_ACCOUNTANT_USER,
            Roles.MALI_BRANCH_DRMD_USER,

            Roles.INCOME_BANK_EDARE_KOL_FANI,
            Roles.INCOME_BANK_BRANCH_NAMNEVISI,
            Roles.INCOME_BANK_OSTAN_NAMNEVISI,
            Roles.INCOME_BANK_EDARE_KOL_NAMNEVISI
    })

    public Response getAll(@QueryParam("filters") FilterWrapper filterWrapper,
                           @Context UriInfo ui,
                           @Context HttpServletRequest request,
                           @Context SecurityContext sc)
            throws Exception {

        return ResponseHelper.ok(service.getSummery(filterWrapper));
    }

    @GET
    @Produces("application/vnd.ms-excel")
    @Path("/excel-export")
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
            Roles.INCOME_BANK_EDARE_KOL_NAMNEVISI
    })
    public Response excelReportHeader(@QueryParam("filters") FilterWrapper filterWrapper,
                                      @QueryParam("start") Integer start,
                                      @QueryParam("limit") Integer limit,
                                      @QueryParam("sort") SortWrapper sort,
                                      @Context UriInfo ui,
                                      @Context SecurityContext sc
    ) throws Exception {

        return Response.ok(service.loadExcel(filterWrapper)).build();
    }
}
