package ir.tamin.incomeBank.ws.rest.daramadBank;

import ir.tamin.framework.cdi.util.MessageBundle;
import ir.tamin.framework.core.util.Bundle;
import ir.tamin.framework.ws.rest.ResponseHelper;
import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.model.daramadBank.ClmOrder;
import ir.tamin.incomeBank.model.daramadBank.ClmOrderPK;
import ir.tamin.incomeBank.service.daramadBank.ClmOrderService;
import ir.tamin.incomeBank.service.identityManager.UserBean;
import ir.tamin.incomeBank.ws.rest.ResourceRESTService;
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
@Path("/clmOrder")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class ClmOrderRestService implements ResourceRESTService<ClmOrder> {

    @Inject
    UserBean userBean;

    @Inject
    @MessageBundle
    Bundle messageBundle;

    @Inject
    ClmOrderService service;

    @Override
    public Response get(String id, UriInfo uriInfo, SecurityContext securityContext, HttpServletRequest request) throws WebApplicationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @GET
    @Path("getClmOrder/{ordOrdno}/{brchCode}")
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
    public Response getClmOrder(@PathParam("ordOrdno") String ordOrdno, @PathParam("brchCode") String brchCode, @Context UriInfo uriInfo, @Context SecurityContext securityContext,
            @Context HttpServletRequest request) throws WebApplicationException {
        ordOrdno = ordOrdno.substring(0, 13);
        ClmOrderPK clmOrderPK = new ClmOrderPK(ordOrdno, brchCode);
        return ResponseHelper.ok(service.get(clmOrderPK));
    }

    @GET
    @Path("getTejaratBankInquiry/{ordOrdno}/{payAmt}/{brchCode}")
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
    public Response getTejaratBankInquiry(@PathParam("ordOrdno") String ordOrdno, @PathParam("payAmt") Long payAmt, @PathParam("brchCode") String brchCode, @Context UriInfo uriInfo, @Context SecurityContext securityContext,
            @Context HttpServletRequest request) throws WebApplicationException {
        return ResponseHelper.ok(service.getTejaratBankInquiry(ordOrdno, payAmt, brchCode));
    }

    @GET
    @Path("getRefahBankInquiry/{ordOrdno}/{payAmt}/{orpCarddate}/{brchCode}")
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
    public Response getRefahBankInquiry(@PathParam("ordOrdno") String ordOrdno, @PathParam("payAmt") Long payAmt, @PathParam("orpCarddate") String orpCarddate, @PathParam("brchCode") String brchCode, @Context UriInfo uriInfo, @Context SecurityContext securityContext,
            @Context HttpServletRequest request) throws WebApplicationException {
        return ResponseHelper.ok(service.getRefahBankInquiry(ordOrdno, payAmt, orpCarddate, brchCode));
    }

    @Override
    public Response getAll(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sortWrapper, UriInfo uriInfo, SecurityContext securityContext, HttpServletRequest request) throws WebApplicationException {
        return ResponseHelper.ok(service.getAll(filterWrapper, start, limit, sortWrapper));
    }

    @Override
    public Response save(ClmOrder clmOrder, UriInfo uriInfo, SecurityContext securityContext, HttpServletRequest request) throws Exception {
        User user = userBean.getUserByUserName(securityContext.getUserPrincipal().getName());
        Response response;
        service.save(clmOrder, user);
        response = Response.status(Response.Status.ACCEPTED).tag(messageBundle.getProperty("coreaccount.daramadbank.SAVE_SUCCESSFULLY")).entity(service.get(clmOrder.getClmOrderPK())).build();
        return ResponseHelper.ok(response);
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
    public Response edit(String id, ClmOrder clmOrder, UriInfo uriInfo, SecurityContext securityContext, HttpServletRequest request) throws WebApplicationException {
//        User user = userBean.getUserByUserName(securityContext.getUserPrincipal().getName());
        User user = new User();
        user.setNationalCode("0077777700");
        Response response;
        service.update(clmOrder, user);
        response = Response.status(Response.Status.ACCEPTED).tag(messageBundle.getProperty("coreaccount.daramadbank.UPDATE_SUCCESSFULLY")).entity(service.get(clmOrder.getClmOrderPK())).build();
        return ResponseHelper.ok(response);
    }

    @Override
    public Response remove(String id, UriInfo uriInfo, SecurityContext securityContext, HttpServletRequest request) throws WebApplicationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
