/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.tamin.incomeBank.ws.rest.daramadBank;

import ir.tamin.framework.ws.rest.ResponseHelper;
import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.model.daramadBank.TelInfo;
import ir.tamin.incomeBank.service.daramadBank.TelInfoService;
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author A_Jamali
 */
@Path("/telInfo")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
public class TelInfoRestService implements ResourceRESTService<TelInfo> {

    @Inject
    TelInfoService telInfoService;

    @GET
    @Path("/getAllBankTelInfo")
    @RolesAllowed({
            Roles.MALI_SETAD_EDARE_KOL_AMAR_USER,
            Roles.MALI_SETAD_EDARE_KOL_MALI_USER,
            Roles.MALI_SETAD_CHIEF_INSURANCE_OFFICER,
            Roles.MALI_SETAD_DRMD_USER,
            Roles.SUPPORTER
    })
    public Response getAllCalcOPBank(@QueryParam("filters") FilterWrapper filterWrapper, @QueryParam("sort") SortWrapper sort, @Context UriInfo ui, @Context HttpServletRequest request, @Context SecurityContext sc) throws WebApplicationException, SQLException {

        return ResponseHelper.ok(telInfoService.getAllBankTelInfo(filterWrapper, sort));

    }

    @Override
    public Response get(String id, UriInfo uriInfo, SecurityContext securityContext, HttpServletRequest request) throws WebApplicationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @RolesAllowed({
            Roles.MALI_SETAD_EDARE_KOL_AMAR_USER,
            Roles.MALI_SETAD_EDARE_KOL_MALI_USER,
            Roles.MALI_SETAD_CHIEF_INSURANCE_OFFICER,
            Roles.MALI_SETAD_DRMD_USER,
            Roles.SUPPORTER
    })
    public Response getAll(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sortWrapper, UriInfo uriInfo, SecurityContext securityContext, HttpServletRequest request) throws WebApplicationException {
        return ResponseHelper.ok(telInfoService.getAll(filterWrapper, sortWrapper));
    }

    @Override
    public Response save(TelInfo t, UriInfo uriInfo, SecurityContext securityContext, HttpServletRequest request) throws WebApplicationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Response edit(String id, TelInfo t, UriInfo uriInfo, SecurityContext securityContext, HttpServletRequest request) throws WebApplicationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Response remove(String id, UriInfo uriInfo, SecurityContext securityContext, HttpServletRequest request) throws WebApplicationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @GET
    @Produces("application/pdf")
    @Path("/telReport")
    public Response doGet(@QueryParam("filters") FilterWrapper filterWrapper, @QueryParam("sort") SortWrapper sort, @Context HttpServletRequest request, @Context SecurityContext sc) throws WebApplicationException, SQLException {

        byte[] byteStream = null;
        try {
            byteStream = telInfoService.doGet(filterWrapper, sort);
        } catch (IOException ex) {
            Logger.getLogger(TelInfoRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response
                .ok(byteStream)
                .header("Content-Type", "application/pdf")
                .build();
    }
}
