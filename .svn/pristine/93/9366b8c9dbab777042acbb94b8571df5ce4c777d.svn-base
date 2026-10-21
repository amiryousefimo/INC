package ir.tamin.incomeBank.ws.rest.daramadBank;

import ir.tamin.framework.ws.rest.ResponseHelper;
import ir.tamin.framework.ws.rest.json.Filter;
import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.model.daramadBank.enums.RcvTypeEnum;
import ir.tamin.incomeBank.ws.rest.Roles;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.HashMap;
import java.util.Map;

/**
 * @author A_Jamali
 */
@Path("/rcvType")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class RcvTypeEnumRestService {

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
    public Response getAll(@QueryParam(value = "query") FilterWrapper query, @QueryParam(value = "filter") FilterWrapper fw, @QueryParam(value = "start") Integer start, @QueryParam(value = "limit") Integer limit, @QueryParam(value = "sort") SortWrapper sw, @Context UriInfo ui, @Context HttpServletRequest request) throws WebApplicationException {
        Map<String, Object> map = new HashMap<>();
        String value = "";
        if (query != null && query.getFilters() != null && query.getFilters().size() == 1) {
            for (Filter filter : query.getFilters()) {
                if ("codeName".equals(filter.getProperty())) {
                    value = filter.getValue().replaceAll("%", "");
                }
            }
        }
        map.put("list", RcvTypeEnum.getEnum(value));
        map.put("total", RcvTypeEnum.values().length);
        return ResponseHelper.ok(map);
    }

}
