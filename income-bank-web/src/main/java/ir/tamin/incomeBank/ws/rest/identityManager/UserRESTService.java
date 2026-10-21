package ir.tamin.incomeBank.ws.rest.identityManager;


import ir.tamin.framework.ws.rest.ResponseHelper;
import ir.tamin.incomeBank.service.RestServices;
import ir.tamin.incomeBank.service.identityManager.UserBean;
import ir.tamin.incomeBank.ws.rest.Roles;

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
 * @author L_Eivazi
 */
@Path("/users")
@RequestScoped
public class UserRESTService {
    @Inject
    UserBean userBean;

    @Inject
    private RestServices restService;

    @GET
    @Path("/current-user")
    @Produces({MediaType.APPLICATION_JSON})
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
            Roles.INCOME_BANK_EDARE_KOL_FANI,
            Roles.INCOME_BANK_EDARE_KOL_NAMNEVISI,
            Roles.SUPPORTER

    })
    public Response getUser(@Context SecurityContext sc) throws Exception {
        return ResponseHelper.ok(userBean.getUserByUserName(sc.getUserPrincipal().getName()));
    }

    @GET
    @Path("/reload-user1/{user-name}")
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
    @Produces({MediaType.APPLICATION_JSON})
    public Response reloadUser(@PathParam("user-name") String userName, @Context HttpServletRequest request) throws Exception {
        userBean.reloadUser(userName);
        return ResponseHelper.ok();
    }

    @GET
    @Path("/reload-user/{user-name}")
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
    @Produces({MediaType.APPLICATION_JSON})
    public Response reloadUserAllServers(@PathParam("user-name") String userName, @Context HttpServletRequest request) {
        String url;
        String result;

        url = "http://eservice-sso-n1.ssodc.ir:9090/api/users/reload-user1/" + userName;
        result = restService.getReloadAllUserResponse(url, null);

        url = "http://eservice-sso-n1.ssodc.ir:9091/api/users/reload-user1/" + userName;
        result = restService.getReloadAllUserResponse(url, null);

        url = "http://eservice-sso-n2.ssodc.ir:9090/api/users/reload-user1/" + userName;
        result = restService.getReloadAllUserResponse(url, null);

        url = "http://eservice-sso-n2.ssodc.ir:9091/api/users/reload-user1/" + userName;
        result = restService.getReloadAllUserResponse(url, null);

        return ResponseHelper.ok(result);
    }
}

