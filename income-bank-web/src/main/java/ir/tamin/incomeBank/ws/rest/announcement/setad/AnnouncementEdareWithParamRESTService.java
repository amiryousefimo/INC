package ir.tamin.incomeBank.ws.rest.announcement.setad;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import ir.tamin.framework.data.validation.Validated;
import ir.tamin.framework.ws.rest.ResponseHelper;
import ir.tamin.incomeBank.service.announcement.edare.AnnouncementEdareKolWithParamService;
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
import javax.ws.rs.core.*;


/**
 * @author A_Jamali
 */

@Path("/Announcement-Edare-Param")
@RequestScoped
@Produces({MediaType.APPLICATION_JSON})
@Validated
@Tag(name = "READ FROM AnnouncementEdare",
        description = "this REST Give Information from AnnouncementEdare object With Parameters")
public class AnnouncementEdareWithParamRESTService {

    @Inject
    private AnnouncementEdareKolWithParamService service;



    @GET
    @Path("/get-all/{startDate}/{endDate}/{edareCode}")
    @Produces(MediaType.APPLICATION_JSON)
    @Validated
    @Operation(
            summary = "Search on view as \"vwletter_edkol \"",
            description = "Search on AnnouncementEdareKol with parameters"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status Search with Parameters as starDate ,endDate and edareCode which include list of entity and total summary of them "
    )
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
            @PathParam("startDate") @Pattern(regexp = "(^$|[0-9]{8})", message = "Date must be 8 digits") String startDate,
            @PathParam("endDate") @Pattern(regexp = "(^$|[0-9]{8})", message = "Date must be 8 digits") String endDate,
            @PathParam("edareCode") @Pattern(regexp ="^\\d{4}$|^(-1)$", message = "Edare Code must be 4 digits") String edareCode,
            @Context HttpServletRequest request,
            @Context SecurityContext sc) throws Exception {

        return ResponseHelper.ok(service.getSetadByDateRange(startDate, endDate, edareCode));
    }





}
