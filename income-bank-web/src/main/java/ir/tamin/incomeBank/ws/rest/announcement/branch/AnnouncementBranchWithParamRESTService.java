package ir.tamin.incomeBank.ws.rest.announcement.branch;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import ir.tamin.framework.data.validation.Validated;
import ir.tamin.framework.ws.rest.ResponseHelper;
import ir.tamin.incomeBank.service.announcement.branch.AnnouncementBranchWithParamService;
import ir.tamin.incomeBank.ws.rest.Roles;
import javax.validation.constraints.Pattern;

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

@Path("/Announcement-Branch-Param")
@RequestScoped
@Produces({MediaType.APPLICATION_JSON})
@Validated
@Tag(name = "READ From AnnouncementBranch",
        description = "this REST Can Give Information from AnnouncementBranch object With Parameters")
public class AnnouncementBranchWithParamRESTService {

    @Inject
    private AnnouncementBranchWithParamService service;

    /************************Search By Parameters************************************************/

    @GET
    @Path("/get-all/{startDate}/{endDate}/{edareCode}/{brchCode}")
    @Operation(
            summary = " Search AnnouncementBranch \"vwletter_brch \"",
            description = "Search from  AnnouncementBranch with Parameter"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Http Status OK with FilterWrapper"),
            @ApiResponse(responseCode = "401", description = "client has invalid or expired token"),
            @ApiResponse(responseCode = "500", description = "Internal server error may be happen  ")
    })
    @RolesAllowed({
            Roles.MALI_BRANCH_ACCOUNTANT,
            Roles.MALI_BRANCH_ACCOUNTANT_USER,
            Roles.MALI_BRANCH_BOSS,
            Roles.MALI_BRANCH_DRMD_USER,
            Roles.MALI_BRANCH_CHIEF_INSURANCE_OFFICER,
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
            @PathParam("startDate")@Pattern(regexp = "(^$|[0-9]{8})", message = "Date must be 8 digits") String startDate,
            @PathParam("endDate")@Pattern(regexp = "(^$|[0-9]{8})", message = "Date must be 8 digits") String endDate,
            @PathParam("edareCode")@Pattern(regexp ="^\\d{4}$|^(-1)$", message = "Edare Code must be 4 digits") String edareCode,
            @PathParam("brchCode")@Pattern(regexp = "^\\d{4}$|^(-1)$", message = "Branch Code must be 4 digits") String brchCode,
            @Context HttpServletRequest request,
            @Context SecurityContext sc) throws Exception {

        return ResponseHelper.ok(service.getBranchByDateRange(startDate, endDate, edareCode, brchCode));
    }


}
