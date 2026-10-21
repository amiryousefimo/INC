package ir.tamin.incomeBank.ws.rest.daramadBank;

import ir.tamin.framework.ws.rest.ResponseHelper;
import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.model.daramadBank.Dftroznameh;
import ir.tamin.incomeBank.service.daramadBank.DftroznamehService;
import ir.tamin.incomeBank.ws.rest.ResourceRESTService;
import ir.tamin.incomeBank.ws.rest.Roles;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author f_fotuhi
 */
@Path("/dftroznameh")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class DftroznamehRestService implements ResourceRESTService<Dftroznameh> {

    @Inject
    DftroznamehService dftroznamehService;

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
        return ResponseHelper.ok(dftroznamehService.getAll(filterWrapper, start, limit, sortWrapper));
    }

    @Override
    public Response save(Dftroznameh t, UriInfo uriInfo, SecurityContext securityContext, HttpServletRequest request) throws WebApplicationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Response edit(String id, Dftroznameh t, UriInfo uriInfo, SecurityContext securityContext, HttpServletRequest request) throws WebApplicationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Response remove(String id, UriInfo uriInfo, SecurityContext securityContext, HttpServletRequest request) throws WebApplicationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @GET
    @Path("/sumAmount")
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
    public Response sumAmount(@QueryParam("filter") FilterWrapper filterWrapper, @QueryParam("start") Integer start, @QueryParam("limit") Integer limit, @QueryParam("sort") SortWrapper sort, @Context UriInfo ui, @Context HttpServletRequest request, @Context SecurityContext sc) throws WebApplicationException {
        List<Dftroznameh> dftRoznamehList = dftroznamehService.getList(filterWrapper, start, limit, sort);
        Long sumAmount = 0L;
        for (Dftroznameh item : dftRoznamehList) {
            sumAmount += item.getOrpPayseqamt();
        }
        return ResponseHelper.ok(sumAmount);
    }

    @GET
    @Produces("application/pdf")
    @Path("/cardReport")
    public Response doGet(@QueryParam("filter") FilterWrapper filterWrapper, @Context HttpServletRequest request, @Context SecurityContext sc) throws WebApplicationException, SQLException {

        byte[] byteStream = null;
        try {
            byteStream = dftroznamehService.doGet(filterWrapper, null, null, null);
        } catch (IOException ex) {
            Logger.getLogger(DftroznamehRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response
                .ok(byteStream)
                .header("Content-Type", "application/pdf")
                .build();
    }

}
