package ir.tamin.incomeBank.ws.rest.daramadBank;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.tamin.framework.ws.rest.ResponseHelper;
import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.model.daramadBank.PoursantajView;
import ir.tamin.incomeBank.service.daramadBank.CoWorkersPoursantajService;
import ir.tamin.incomeBank.ws.rest.Roles;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author e_shoghi
 */
@Path("co-workers-poursantaj")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
public class CoWorkersPoursantajRESTService {

    @Inject
    CoWorkersPoursantajService coWorkersPoursantajService;

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
    public Response getAll(@QueryParam("filter") FilterWrapper filterWrapper, @QueryParam("start") Integer start, @QueryParam("limit") Integer limit, @QueryParam("sort") SortWrapper sortWrapper, @Context UriInfo uriInfo, @Context SecurityContext securityContext, @Context HttpServletRequest request) throws WebApplicationException {
        return ResponseHelper.ok(coWorkersPoursantajService.getAll(filterWrapper, start, limit, sortWrapper));
    }

    @GET
    @Path("/from-view")
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
    public Response getAllFromView(@QueryParam("filter") FilterWrapper filterWrapper, @QueryParam("start") Integer start, @QueryParam("limit") Integer limit, @QueryParam("sort") SortWrapper sortWrapper, @Context UriInfo uriInfo, @Context SecurityContext securityContext, @Context HttpServletRequest request) throws WebApplicationException {
        return ResponseHelper.ok(coWorkersPoursantajService.getAllFromView(filterWrapper, start, limit, sortWrapper));
    }

    @PUT
    @Path("/export-vouchers")
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
    public Response exportVouchers(List<Object> bajListHeaders, @Context UriInfo uriInfo, @Context SecurityContext securityContext, @Context HttpServletRequest request) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<PoursantajView> headerLst = new ArrayList<>();
        for (Object bajListHeader : bajListHeaders) {
            PoursantajView header = mapper.convertValue(bajListHeader, PoursantajView.class);
            headerLst.add(header);
        }
//        List<BajListHeader> headerLst
//                = mapper.readValue(bajListHeaders.toString(), new TypeReference<List<BajListHeader>>() {
//                });
//        List<BajListHeader> headerLst = mapper.convertValue(bajListHeaders, new TypeReference<List<BajListHeader>>(){});
//        List<BajListHeader> headerLst = (ArrayList<BajListHeader>) bajListHeaders;
        Map result = coWorkersPoursantajService.exportVouchers(headerLst, securityContext);
//        Response response = Response.status((Response.Status) result.get("status"))
//                .entity((List<Voucher>) result.get("list"))
//                .build();
//        return ResponseHelper.ok(response);
           return ResponseHelper.ok(result);
    }

    @GET
    @Path("/get-current-vouchers/{uuid}")
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
    public Response getCurrentVouchers(@PathParam("uuid") String uuid, @Context UriInfo uriInfo, @Context SecurityContext securityContext, @Context HttpServletRequest request) throws Exception {
        Map pageData = coWorkersPoursantajService.getCurrentVouchers(uuid, securityContext);
        return ResponseHelper.ok(pageData);
    }

    @DELETE
    @Path("/revert-vouchers")
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
    public Response revertVouchers(List<Object> bajListHeaders, @Context UriInfo uriInfo, @Context SecurityContext securityContext, @Context HttpServletRequest request) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<PoursantajView> headerLst = new ArrayList<>();
        for (Object o : bajListHeaders) {
            PoursantajView header = mapper.convertValue(o, PoursantajView.class);
            headerLst.add(header);
        }
        Map result = coWorkersPoursantajService.revertVouchers(headerLst, securityContext);
        Response response = Response.status((Response.Status) result.get("status"))
                .entity((String) result.get("message"))
                .build();
        return ResponseHelper.ok(response);
    }
}
