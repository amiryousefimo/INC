package ir.tamin.incomeBank.ws.rest.daramadBank;

import ir.tamin.framework.ws.rest.ResponseHelper;
import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.model.daramadBank.VwDrmdBank;
import ir.tamin.incomeBank.service.daramadBank.VwDrmdBankService;
import ir.tamin.incomeBank.service.identityManager.UserBean;
import ir.tamin.incomeBank.ws.rest.ResourceRESTService;
import ir.tamin.incomeBank.ws.rest.Roles;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

@Path("/vwDrmdBank")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class VwDrmdBankRestService implements ResourceRESTService<VwDrmdBank> {

    @Inject
    VwDrmdBankService vwDrmdBankService;
    

    @Override
    public Response get(String id, UriInfo uriInfo, SecurityContext securityContext, HttpServletRequest request) throws WebApplicationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
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
    public Response getAll(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sortWrapper, UriInfo uriInfo, SecurityContext securityContext, HttpServletRequest request) throws WebApplicationException {
        return ResponseHelper.ok(vwDrmdBankService.getAll(filterWrapper, start, limit, sortWrapper));
    }

    @Override
    public Response save(VwDrmdBank t, UriInfo uriInfo, SecurityContext securityContext, HttpServletRequest request) throws WebApplicationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Response edit(String id, VwDrmdBank t, UriInfo uriInfo, SecurityContext securityContext, HttpServletRequest request) throws WebApplicationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Response remove(String id, UriInfo uriInfo, SecurityContext securityContext, HttpServletRequest request) throws WebApplicationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
