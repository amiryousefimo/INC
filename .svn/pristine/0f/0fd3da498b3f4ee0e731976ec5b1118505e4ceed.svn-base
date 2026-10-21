package ir.tamin.incomeBank.ws.rest.daramadBank;

import ir.tamin.framework.ws.rest.ResponseHelper;
import ir.tamin.framework.ws.rest.json.Filter;
import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.service.daramadBank.CustomerCodeService;
import ir.tamin.incomeBank.ws.rest.Roles;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 *
 * @author A_Jamali
 */
@Path("/customerCode")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class CustomerCodeRestService {

    @Inject
    CustomerCodeService customerCodeService;

    @GET
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
    public Response getAll(@QueryParam(value = "query") FilterWrapper query, @QueryParam(value = "filter") FilterWrapper fw, @QueryParam(value = "start") Integer start, @QueryParam(value = "limit") Integer limit, @QueryParam(value = "sort") SortWrapper sw, @Context UriInfo ui, @Context SecurityContext sc) throws WebApplicationException {

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
        return ResponseHelper.ok(customerCodeService.getAll(fw, start, limit, sw));
    }

}
