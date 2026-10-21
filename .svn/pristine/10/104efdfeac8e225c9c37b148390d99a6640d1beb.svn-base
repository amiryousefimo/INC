package ir.tamin.incomeBank.ws.rest.daramadBank;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.tamin.framework.ws.rest.ResponseHelper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.model.daramadBank.IssuedLettersOfficeView;
import ir.tamin.incomeBank.service.daramadBank.ExportIssuedLettersService;
import ir.tamin.incomeBank.ws.rest.Roles;
import ir.tamin.incomeBank.ws.rest.json.FilterWrapper;

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
 * @author A_Jamali
 */

@Path("export-issued-letters")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
public class ExportIssuedLettersRESTService {

    @Inject
    ExportIssuedLettersService exportIssuedLettersService;

    @GET
    @Path("/get-all")
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
        return ResponseHelper.ok(exportIssuedLettersService.getAll(filterWrapper, start, limit, sortWrapper));
    }

    @GET
    @Path("/get-all-from-view")
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
        return ResponseHelper.ok(exportIssuedLettersService.getAllFromView(filterWrapper, start, limit, sortWrapper));
    }

    @PUT
    @Path("/export-letters")
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
    public Response exportLetters(List<Object> letters, @Context UriInfo uriInfo, @Context SecurityContext securityContext, @Context HttpServletRequest request) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<IssuedLettersOfficeView> letterLst = new ArrayList<>();
        for (Object drmdLetter : letters) {
            IssuedLettersOfficeView letter = mapper.convertValue(drmdLetter, IssuedLettersOfficeView.class);
            letterLst.add(letter);
        }
        Map result = exportIssuedLettersService.exportLetters(letterLst, securityContext);
//        Response response = Response.status((Response.Status) result.get("status"))
//                .entity((List<IssuedLettersOfficeView>) result.get("list"))
//                .build();
        return ResponseHelper.ok(result);
    }

    @PUT
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
    @Path("/revert-vouchers")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response revertVouchers(List<Object> letters, @Context UriInfo uriInfo, @Context SecurityContext securityContext, @Context HttpServletRequest request) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<IssuedLettersOfficeView> letterLst = new ArrayList<>();
        for (Object o : letters) {
            IssuedLettersOfficeView letter = mapper.convertValue(o, IssuedLettersOfficeView.class);
            letterLst.add(letter);
        }
        Map result = exportIssuedLettersService.revertVouchers(letterLst, securityContext);
        Response response = Response.status((Response.Status) result.get("status"))
                .entity((String) result.get("message"))
                .build();
        return ResponseHelper.ok(response);
    }
}
