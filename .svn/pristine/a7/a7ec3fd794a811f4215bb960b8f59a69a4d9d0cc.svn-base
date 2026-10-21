package ir.tamin.incomeBank.ws.rest.daramadBank;

import ir.tamin.framework.ws.rest.ResponseHelper;
import ir.tamin.incomeBank.model.daramadBank.DrmdLetterOK;
import ir.tamin.incomeBank.service.daramadBank.LetterOKService;
import ir.tamin.incomeBank.service.identityManager.UserBean;
import ir.tamin.incomeBank.ws.rest.Roles;
import ir.tamin.portal.model.org.User;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 * @author A_Jamali
 */

@Path("letterOK")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class LetterOKRestService {

    @Inject
    LetterOKService service;

    @Inject
    UserBean userBean;

    @GET
    @Path("getLetterStat")
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
    public Response getLetterStat(@QueryParam("letterSerial") String letterSerial, @Context UriInfo uriInfo, @Context SecurityContext securityContext, @Context HttpServletRequest request) throws WebApplicationException {
        return ResponseHelper.ok(service.getLetterStat(letterSerial));
    }

    @POST
    @Path("saveLetterStat")
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
    public Response saveLetterStat(DrmdLetterOK inputModel, @Context UriInfo uriInfo, @Context SecurityContext securityContext, @Context HttpServletRequest request) throws Exception {
        User user = userBean.getUserByUserName(securityContext.getUserPrincipal().getName());
        return ResponseHelper.ok(service.save(inputModel, user));
    }

    @PUT
    @Path("updateLetterStat")
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
    public Response updateLetterStat(DrmdLetterOK inputModel, @Context UriInfo uriInfo, @Context SecurityContext securityContext, @Context HttpServletRequest request) throws Exception {
        User user = userBean.getUserByUserName(securityContext.getUserPrincipal().getName());
        return ResponseHelper.ok(service.update(inputModel, user));
    }

    @GET
    @Path("sendToBank/{letterSerial}/{letterDate}/{brchCode}/{status}")
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
    public Response sendToBranch(@PathParam("letterSerial") String letterSerial, @PathParam("letterDate") String letterDate, @PathParam("brchCode") String brchCode,@PathParam("status") String status, @Context UriInfo uriInfo, @Context SecurityContext securityContext,
            @Context HttpServletRequest request) throws WebApplicationException {
        return ResponseHelper.ok(service.sendToBranch(letterSerial, letterDate, brchCode,status));
    }
}
