package ir.tamin.incomeBank.ws.rest.financialDoc.newFinancialDocSpec;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import ir.tamin.framework.ws.rest.ResponseHelper;
import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.service.financialDoc.newFinancialDocSpec.SheetsWithoutBankCardsDocSpecService;
import ir.tamin.incomeBank.service.sheetsWithoutBankCards.bankCardsSmartList.SheetsWithoutBankCardsSmartListService;
import ir.tamin.incomeBank.ws.rest.Roles;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 * @author m_goharriz
 * @date 2025.06.30
 */
@Path("/sheets-without-bank-cards-doc-spec")
@RequestScoped
//@Produces({MediaType.APPLICATION_JSON, "application/vnd.ms-excel"})//TODO-----------
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SheetsWithoutBankCardsDocSpecREST {

    @Inject
    SheetsWithoutBankCardsDocSpecService service;


    @GET
    @Path("/get-all")
    @Operation(
            summary = " Get A List From PaymentWorkersBranch \"VW_PAYKIND_BLD_BRCH  \"",
            description = "Get Search report from  PaymentWorkersBranch with Filter"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Http Status OK with FilterWrapper"),
            @ApiResponse(responseCode = "401", description = "client has invalid or expired token"),
            @ApiResponse(responseCode = "500", description = "Internal server error may be happen  ")
    })
    @RolesAllowed({

            Roles.MALI_SETAD_DRMD_USER,
            Roles.MALI_SETAD_CHIEF_INSURANCE_OFFICER,

            Roles.MALI_BRANCH_CHIEF_INSURANCE_OFFICER,

            Roles.MALI_BRANCH_DRMD_USER,
            Roles.MALI_BRANCH_ACCOUNTANT_USER,
            Roles.INCOME_BANK_BRANCH_NAMNEVISI,
            Roles.MALI_BRANCH_BOSS,
            Roles.MALI_BRANCH_ACCOUNTANT,

            Roles.INCOME_BANK_OSTAN_NAMNEVISI,

            Roles.MALI_SETAD_EDARE_KOL_MALI_USER,
            Roles.MALI_SETAD_EDARE_KOL_AMAR_USER,
            Roles.MALI_EDARE_KOL_AMAR_USER,
            Roles.MALI_EDARE_KOL_PROVINCE_MANAGER,
            Roles.INCOME_BANK_EDARE_KOL_NAMNEVISI,
            Roles.MALI_EDARE_KOL_MALI_BOSS,
            Roles.MALI_EDARE_KOL_DRMD_BOSS,
            Roles.MALI_EDARE_KOL_CHIEF_INSURANCE_PROVINCE,
            Roles.INCOME_BANK_EDARE_KOL_FANI,

            Roles.SUPPORTER
    })
    public Response getAll(@QueryParam("filter") FilterWrapper filterWrapper,
                                   @QueryParam("start") Integer start,
                                   @QueryParam("limit") Integer limit,
                                   @QueryParam("sort") SortWrapper sort,
                                   @Context SecurityContext sc) throws Exception {
        return ResponseHelper.ok(service.getAll(filterWrapper, start, limit, sort));
    }


   /* @GET
    @Path("/get-sum")
    @RolesAllowed({
            Roles.MALI_SETAD_DRMD_USER,
            Roles.MALI_SETAD_CHIEF_INSURANCE_OFFICER,

            Roles.MALI_BRANCH_CHIEF_INSURANCE_OFFICER,

            Roles.MALI_BRANCH_DRMD_USER,
            Roles.MALI_BRANCH_ACCOUNTANT_USER,
            Roles.INCOME_BANK_BRANCH_NAMNEVISI,
            Roles.MALI_BRANCH_BOSS,
            Roles.MALI_BRANCH_ACCOUNTANT,

            Roles.INCOME_BANK_OSTAN_NAMNEVISI,

            Roles.MALI_SETAD_EDARE_KOL_MALI_USER,
            Roles.MALI_SETAD_EDARE_KOL_AMAR_USER,
            Roles.MALI_EDARE_KOL_AMAR_USER,
            Roles.MALI_EDARE_KOL_PROVINCE_MANAGER,
            Roles.INCOME_BANK_EDARE_KOL_NAMNEVISI,
            Roles.MALI_EDARE_KOL_MALI_BOSS,
            Roles.MALI_EDARE_KOL_DRMD_BOSS,
            Roles.MALI_EDARE_KOL_CHIEF_INSURANCE_PROVINCE,
            Roles.INCOME_BANK_EDARE_KOL_FANI,

            Roles.SUPPORTER

    })
    public Response getAll(@QueryParam("filters") FilterWrapper filterWrapper,
                           @Context UriInfo ui,
                           @Context HttpServletRequest request,
                           @Context SecurityContext sc)
            throws Exception {

        return ResponseHelper.ok(service.getSummary(filterWrapper));
    }


    @GET
    @Path("/get-Excel")
    @RolesAllowed({
            Roles.MALI_SETAD_DRMD_USER,
            Roles.MALI_SETAD_CHIEF_INSURANCE_OFFICER,

            Roles.MALI_BRANCH_CHIEF_INSURANCE_OFFICER,

            Roles.MALI_BRANCH_DRMD_USER,
            Roles.MALI_BRANCH_ACCOUNTANT_USER,
            Roles.INCOME_BANK_BRANCH_NAMNEVISI,
            Roles.MALI_BRANCH_BOSS,
            Roles.MALI_BRANCH_ACCOUNTANT,

            Roles.INCOME_BANK_OSTAN_NAMNEVISI,

            Roles.MALI_SETAD_EDARE_KOL_MALI_USER,
            Roles.MALI_SETAD_EDARE_KOL_AMAR_USER,
            Roles.MALI_EDARE_KOL_AMAR_USER,
            Roles.MALI_EDARE_KOL_PROVINCE_MANAGER,
            Roles.INCOME_BANK_EDARE_KOL_NAMNEVISI,
            Roles.MALI_EDARE_KOL_MALI_BOSS,
            Roles.MALI_EDARE_KOL_DRMD_BOSS,
            Roles.MALI_EDARE_KOL_CHIEF_INSURANCE_PROVINCE,
            Roles.INCOME_BANK_EDARE_KOL_FANI,

            Roles.SUPPORTER

    })
    public Response getAll(@QueryParam("filters") FilterWrapper filterWrapper,
                           @Context HttpServletRequest request,
                           @Context SecurityContext sc)
            throws Exception {

        return Response.ok(service.loadExcel(filterWrapper))
                .header("Content-Type", "application/vnd.ms-excel")
                .build();
    }*/

}
