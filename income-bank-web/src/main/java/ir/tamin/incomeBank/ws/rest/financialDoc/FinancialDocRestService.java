package ir.tamin.incomeBank.ws.rest.financialDoc;

import ir.tamin.framework.ws.rest.ResponseHelper;
import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.service.financialDoc.FinancialDoc3070Service;
import ir.tamin.incomeBank.service.financialDoc.FinancialDocService;
import ir.tamin.incomeBank.ws.rest.Roles;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author A_Jamali
 */
@Path("/financial-doc")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class FinancialDocRestService {

    @Inject
    FinancialDocService service;

    @GET
    @Path("/get-all")
    @RolesAllowed({
            Roles.MALI_SETAD_EDARE_KOL_AMAR_USER,
            Roles.MALI_SETAD_EDARE_KOL_MALI_USER,
            Roles.MALI_SETAD_CHIEF_INSURANCE_OFFICER,
            Roles.MALI_SETAD_DRMD_USER,
            Roles.SUPPORTER
    })
    public Response getAll(@QueryParam("filter") FilterWrapper filterWrapper,
                           @QueryParam("start") Integer start,
                           @QueryParam("limit") Integer limit,
                           @QueryParam("sort") SortWrapper sort,
                           @Context UriInfo ui,
                           @Context HttpServletRequest request,
                           @Context SecurityContext sc)
            throws Exception {

        return ResponseHelper.ok(service.getAll(filterWrapper, start, limit, sort));
    }

    @GET
    @Path("/get-sum")
    @RolesAllowed({
            Roles.MALI_SETAD_EDARE_KOL_AMAR_USER,
            Roles.MALI_SETAD_EDARE_KOL_MALI_USER,
            Roles.MALI_SETAD_CHIEF_INSURANCE_OFFICER,
            Roles.MALI_SETAD_DRMD_USER,
            Roles.SUPPORTER
    })
    public Response getAll(@QueryParam("filters") FilterWrapper filterWrapper,
                           @Context UriInfo ui,
                           @Context HttpServletRequest request,
                           @Context SecurityContext sc)
            throws Exception {

        return ResponseHelper.ok(service.getSummery(filterWrapper));
    }

    @GET
    @Path("/fin-issuance/{year}/{month}")
    @RolesAllowed({
            Roles.MALI_SETAD_EDARE_KOL_AMAR_USER,
            Roles.MALI_SETAD_EDARE_KOL_MALI_USER,
            Roles.MALI_SETAD_CHIEF_INSURANCE_OFFICER,
            Roles.MALI_SETAD_DRMD_USER,
            Roles.SUPPORTER
    })
    public Response issuance(@PathParam("year") String year,
                             @PathParam("month") String month,
                             @Context UriInfo ui,
                             @Context HttpServletRequest request,
                             @Context SecurityContext sc)
            throws WebApplicationException, SQLException {

        return ResponseHelper.ok(service.importFinancialDocs(year, month));
    }

    @DELETE
    @Path("/fin-delete/{year}/{month}")
    @RolesAllowed({
            Roles.MALI_SETAD_EDARE_KOL_AMAR_USER,
            Roles.MALI_SETAD_EDARE_KOL_MALI_USER,
            Roles.MALI_SETAD_CHIEF_INSURANCE_OFFICER,
            Roles.MALI_SETAD_DRMD_USER,
            Roles.SUPPORTER
    })
    public Response submit(@PathParam("year") String year,
                           @PathParam("month") String month,
                           @Context UriInfo ui,
                           @Context HttpServletRequest request,
                           @Context SecurityContext sc)
            throws WebApplicationException, SQLException {

        return ResponseHelper.ok(service.removeFinancialDocs(year, month));
    }

    @POST
    @Path("/send-data-to-temp")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({
            Roles.MALI_SETAD_EDARE_KOL_AMAR_USER,
            Roles.MALI_SETAD_EDARE_KOL_MALI_USER,
            Roles.MALI_SETAD_CHIEF_INSURANCE_OFFICER,
            Roles.MALI_SETAD_DRMD_USER,
            Roles.SUPPORTER
    })
    public Response sendDataToTemp(FilterWrapper filterWrapper,
                                   @Context UriInfo ui,
                                   @Context HttpServletRequest request,
                                   @Context SecurityContext sc) throws Exception {

        return ResponseHelper.ok(service.sendDataToTemp(filterWrapper));
    }
    @GET
    @Path("/confirm-disagreements")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({
            Roles.MALI_SETAD_EDARE_KOL_MALI_USER,
            Roles.SUPPORTER
    })
    public Response confrimDisagreements(@QueryParam(value = "filter") FilterWrapper filterWrapper,
                                         @Context HttpServletRequest request,
                                         @Context SecurityContext sc)
            throws Exception {

        Object result = null;
        try{
            result = service.confrimDisagreements(filterWrapper);
        } catch (IOException ex) {
            Logger.getLogger(FinancialDoc3070Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ResponseHelper.ok(result);
    }

    @GET
    @Path("/get-Excel")
    @RolesAllowed({
            Roles.MALI_SETAD_EDARE_KOL_AMAR_USER,
            Roles.MALI_SETAD_EDARE_KOL_MALI_USER,
            Roles.MALI_SETAD_CHIEF_INSURANCE_OFFICER,
            Roles.MALI_SETAD_DRMD_USER,
            Roles.SUPPORTER
    })
    public Response getAll(@QueryParam("filters") FilterWrapper filterWrapper, @Context HttpServletRequest request,
                           @Context SecurityContext sc)
            throws Exception {

        return Response.ok(service.loadExcel(filterWrapper))
                .header("Content-Type", "application/vnd.ms-excel")
                .build();
    }

    @GET
    @Path("/send-disagreements")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({
            Roles.MALI_SETAD_EDARE_KOL_MALI_USER,//OK
            Roles.SUPPORTER//OK

    })
    public Response sendDisagreements(@QueryParam(value = "filter") FilterWrapper filterWrapper,
                                      @Context HttpServletRequest request,
                                      @Context SecurityContext sc)
            throws Exception {

        Object result = null;
        try{
            result = service.discrepancyPreparation(filterWrapper);
        } catch (IOException ex) {
            Logger.getLogger(FinancialDocService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ResponseHelper.ok(result);
    }
}
