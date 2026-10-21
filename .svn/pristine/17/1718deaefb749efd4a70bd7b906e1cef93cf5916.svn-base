package ir.tamin.incomeBank.ws.rest.basicTables.debtTypes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import ir.tamin.framework.ws.rest.ResponseHelper;
import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.service.basicTables.debtTypes.DebtTypeService;
import ir.tamin.incomeBank.ws.rest.Roles;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.*;

/**
 * @author m_goharriz
 */
@Path("/Debt-Types-Table")
@RequestScoped
@Produces({MediaType.APPLICATION_JSON, "application/vnd.ms-excel"})
public class DebtTypesRESTService {

    @Inject
    private DebtTypeService debtTypeService;



    @GET
    @Path("/get-all")
    @Operation(
            summary = " Get A List From DebtTypes \" VWTB_DEBITTYPE \"",
            description = "Get Search report from  DebtTypes with Filter"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Http Status OK with FilterWrapper"),
            @ApiResponse(responseCode = "401", description = "client has invalid or expired token"),
            @ApiResponse(responseCode = "500", description = "Internal server error may be happen  ")
    })
    @RolesAllowed({


            Roles.MALI_SETAD_DRMD_USER,
            Roles.MALI_SETAD_CHIEF_INSURANCE_OFFICER,

            Roles.MALI_BRANCH_ACCOUNTANT,
            Roles.MALI_BRANCH_BOSS,
            Roles.INCOME_BANK_BRANCH_FANI,
            Roles.MALI_BRANCH_ACCOUNTANT_USER,
            Roles.MALI_BRANCH_DRMD_USER,
            Roles.INCOME_BANK_BRANCH_NAMNEVISI,

            Roles.MALI_BRANCH_CHIEF_INSURANCE_OFFICER,

            Roles.MALI_EDARE_KOL_PROVINCE_MANAGER,
            Roles.MALI_EDARE_KOL_CHIEF_INSURANCE_PROVINCE,
            Roles.MALI_EDARE_KOL_MALI_BOSS,
            Roles.MALI_EDARE_KOL_DRMD_BOSS,
            Roles.MALI_EDARE_KOL_AMAR_USER,
            Roles.MALI_SETAD_EDARE_KOL_MALI_USER,
            Roles.MALI_SETAD_EDARE_KOL_AMAR_USER,
            Roles.INCOME_BANK_EDARE_KOL_NAMNEVISI,
            Roles.INCOME_BANK_EDARE_KOL_FANI,

            Roles.INCOME_BANK_OSTAN_FANI,
            Roles.INCOME_BANK_OSTAN_NAMNEVISI,

            Roles.SUPPORTER


    })
    public Response getTelSumDaily(@QueryParam("filter") FilterWrapper filterWrapper,
                                   @QueryParam("start") Integer start,
                                   @QueryParam("limit") Integer limit,
                                   @QueryParam("sort") SortWrapper sort,
                                   @Context SecurityContext sc) throws Exception {
        return ResponseHelper.ok(debtTypeService.getAll(filterWrapper, start, limit, sort));
    }

    @GET
    @Produces("application/vnd.ms-excel")
    @Path("/excel-export")
    @RolesAllowed({
            Roles.MALI_SETAD_DRMD_USER,
            Roles.MALI_SETAD_CHIEF_INSURANCE_OFFICER,

            Roles.MALI_BRANCH_ACCOUNTANT,
            Roles.MALI_BRANCH_BOSS,
            Roles.INCOME_BANK_BRANCH_FANI,
            Roles.MALI_BRANCH_ACCOUNTANT_USER,
            Roles.MALI_BRANCH_DRMD_USER,
            Roles.INCOME_BANK_BRANCH_NAMNEVISI,

            Roles.MALI_BRANCH_CHIEF_INSURANCE_OFFICER,

            Roles.MALI_EDARE_KOL_PROVINCE_MANAGER,
            Roles.MALI_EDARE_KOL_CHIEF_INSURANCE_PROVINCE,
            Roles.MALI_EDARE_KOL_MALI_BOSS,
            Roles.MALI_EDARE_KOL_DRMD_BOSS,
            Roles.MALI_EDARE_KOL_AMAR_USER,
            Roles.MALI_SETAD_EDARE_KOL_MALI_USER,
            Roles.MALI_SETAD_EDARE_KOL_AMAR_USER,
            Roles.INCOME_BANK_EDARE_KOL_NAMNEVISI,
            Roles.INCOME_BANK_EDARE_KOL_FANI,

            Roles.INCOME_BANK_OSTAN_FANI,
            Roles.INCOME_BANK_OSTAN_NAMNEVISI,

            Roles.SUPPORTER
    })
    public Response excelReportHeader(@QueryParam("filters") FilterWrapper filterWrapper,
                                      @QueryParam("start") Integer start,
                                      @QueryParam("limit") Integer limit,
                                      @QueryParam("sort") SortWrapper sort,
                                      @Context UriInfo ui,
                                      @Context SecurityContext sc
    ) throws Exception {

        return Response.ok(debtTypeService.loadExcel(filterWrapper)).build();
    }
}
