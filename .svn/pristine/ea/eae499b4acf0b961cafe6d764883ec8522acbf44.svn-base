package ir.tamin.incomeBank.ws.rest.asnad;

import ir.tamin.framework.ws.rest.ResponseHelper;
import ir.tamin.incomeBank.model.asnad.AsnadCalcInputModel;
import ir.tamin.incomeBank.service.asnad.AsnadPayCalcService;
import ir.tamin.incomeBank.ws.rest.Roles;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 * @author A_Jamali
 */
@Path("/asnadPayCalc")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
public class AsnadPayCalcRestService {

    @Inject
    AsnadPayCalcService asnadPayCalcService;

    @POST
    @Path("/totalAmount")
    @Consumes(MediaType.APPLICATION_JSON)
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
    public Response totalAmount(AsnadCalcInputModel asnadCalcInputModel, @Context UriInfo uriInfo, @Context SecurityContext securityContext, @Context HttpServletRequest request) throws WebApplicationException {
        return ResponseHelper.ok(asnadPayCalcService.totalAmount(asnadCalcInputModel));
    }

    @POST
    @Path("/totalWithGroupByCustType")
    @Consumes(MediaType.APPLICATION_JSON)
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
    public Response totalWithGroupByCustType(AsnadCalcInputModel asnadCalcInputModel, @Context UriInfo uriInfo, @Context SecurityContext securityContext, @Context HttpServletRequest request) throws WebApplicationException {
        return ResponseHelper.ok(asnadPayCalcService.totalWithGroupByCustType(asnadCalcInputModel));
    }

    @POST
    @Path("/totalWithGroupByBank")
    @Consumes(MediaType.APPLICATION_JSON)
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
    public Response totalWithGroupByBank(AsnadCalcInputModel asnadCalcInputModel, @Context UriInfo uriInfo, @Context SecurityContext securityContext, @Context HttpServletRequest request) throws WebApplicationException {
        return ResponseHelper.ok(asnadPayCalcService.totalWithGroupByBank(asnadCalcInputModel));
    }

}
