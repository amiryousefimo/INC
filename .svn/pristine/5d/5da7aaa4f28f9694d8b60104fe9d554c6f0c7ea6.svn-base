/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.tamin.incomeBank.ws.rest.baseinfo;

import ir.tamin.framework.ws.rest.ResponseHelper;
import ir.tamin.framework.ws.rest.json.Filter;
import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.service.baseinfo.BranchService;
import ir.tamin.incomeBank.service.identityManager.UserBean;
import ir.tamin.incomeBank.ws.rest.Roles;
import ir.tamin.portal.model.org.User;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 *
 * @author s_maknooni
 */
@Path("/branches")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class BranchRestService {

    @Inject
    BranchService service;

    @Inject
    UserBean userBean;


    @GET
    @Path("/getAll")
    @RolesAllowed({
            Roles.MALI_BRANCH_ACCOUNTANT ,
            Roles.MALI_BRANCH_ACCOUNTANT_USER,
            Roles.MALI_BRANCH_BOSS ,
            Roles.MALI_BRANCH_DRMD_USER,
            Roles.MALI_BRANCH_CHIEF_INSURANCE_OFFICER,
            Roles.MALI_SETAD_EDARE_KOL_AMAR_USER ,
            Roles.MALI_EDARE_KOL_AMAR_USER,
            Roles.MALI_EDARE_KOL_CHIEF_INSURANCE_PROVINCE,
            Roles.MALI_EDARE_KOL_DRMD_BOSS,
            Roles.MALI_EDARE_KOL_MALI_BOSS,
            Roles.MALI_SETAD_EDARE_KOL_MALI_USER ,
            Roles.MALI_EDARE_KOL_PROVINCE_MANAGER,
            Roles.MALI_SETAD_CHIEF_INSURANCE_OFFICER,
            Roles.MALI_SETAD_DRMD_USER,
            Roles.SUPPORTER
    })

    public Response getAll(@QueryParam(value = "query") FilterWrapper query,@QueryParam(value = "filter") FilterWrapper fw, @QueryParam(value = "start") Integer start, @QueryParam(value = "limit") Integer limit, @QueryParam(value = "sort") SortWrapper sw, @Context UriInfo ui, @Context SecurityContext sc) throws Exception {
        User user = userBean.getUserByUserName(sc.getUserPrincipal().getName());

        if (query != null) { // search in Combo
            if (fw == null) {
                fw = query;
            } else if (fw.getFilters() != null) {
                for (Filter queryFilter : query.getFilters()) {
                    fw.getFilters().add(queryFilter);
                }
            } else {
                fw.setFilters(query.getFilters());
            }
        }
        return ResponseHelper.ok(service.getAll(fw, start, limit, sw,user));
    }

    @GET
    @Path("/item-page")
    @RolesAllowed({
            Roles.MALI_BRANCH_ACCOUNTANT ,
            Roles.MALI_BRANCH_ACCOUNTANT_USER,
            Roles.MALI_BRANCH_BOSS ,
            Roles.MALI_BRANCH_DRMD_USER,
            Roles.MALI_BRANCH_CHIEF_INSURANCE_OFFICER,
            Roles.MALI_SETAD_EDARE_KOL_AMAR_USER ,
            Roles.MALI_EDARE_KOL_AMAR_USER,
            Roles.MALI_EDARE_KOL_CHIEF_INSURANCE_PROVINCE,
            Roles.MALI_EDARE_KOL_DRMD_BOSS,
            Roles.MALI_EDARE_KOL_MALI_BOSS,
            Roles.MALI_SETAD_EDARE_KOL_MALI_USER ,
            Roles.MALI_EDARE_KOL_PROVINCE_MANAGER,
            Roles.MALI_SETAD_CHIEF_INSURANCE_OFFICER,
            Roles.MALI_SETAD_DRMD_USER,
            Roles.SUPPORTER
    })
    public Response getItemPage(@QueryParam(value = "brhCode") String code, @QueryParam(value = "filter") FilterWrapper filters, @QueryParam(value = "start") Integer start, @QueryParam(value = "limit") Integer limit, @QueryParam(value = "sort") SortWrapper sort, @Context UriInfo ui, @Context SecurityContext sc) {
        //if (limit == null || limit < 1 || limit > 100) {
        limit = 10;
        // }
        return ResponseHelper.ok(service.getItemPage(code, filters, start, limit, sort));
    }
    
}
