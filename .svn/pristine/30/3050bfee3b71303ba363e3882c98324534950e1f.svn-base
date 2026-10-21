package ir.tamin.incomeBank.ws.rest.daramadBank;

import com.healthmarketscience.jackcess.Database;
import ir.tamin.framework.ws.rest.ResponseHelper;
import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.service.daramadBank.ComperrVw17Service;
import ir.tamin.incomeBank.ws.rest.Roles;
import org.apache.poi.ss.usermodel.Workbook;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author h_riazat
 */
@Path("/comperrVw17")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class ComperrVw17RestService {

    @Inject
    ComperrVw17Service comperrVw17Service;

    @GET
    @Path("/getAll")
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
    public Response getAll(@QueryParam("filter") FilterWrapper filterWrapper, @QueryParam("start") Integer start, @QueryParam("limit") Integer limit, @QueryParam("sort") SortWrapper sort, @Context UriInfo ui, @Context HttpServletRequest request, @Context SecurityContext sc) throws WebApplicationException, IOException, SQLException, SQLException {
        Map<String, Object> map = new HashMap<>();
        map = comperrVw17Service.getAll(filterWrapper, start, limit, sort);
        return ResponseHelper.ok(map);
    }

    @GET
    @Path("/view17Excel")
    @Produces("application/vnd.ms-excel")
    public Response writeToExcel(@QueryParam("filter") FilterWrapper filterWrapper, @Context UriInfo ui, @Context HttpServletRequest request, @Context SecurityContext sc) throws WebApplicationException, IOException {
        final Workbook workbook = comperrVw17Service.writeToExcel(filterWrapper, null, null, null);
        StreamingOutput output = new StreamingOutput() {
            @Override
            public void write(java.io.OutputStream output) throws IOException, WebApplicationException {
                workbook.write(output);
                output.close();
            }
        };
        String fileName = "comperrReport17.xlsx";
        return Response.ok(output).header("Content-Disposition", "attachment; filename=" + fileName).build();
    }

    @GET
    @Path("/view17Xml")
    @Produces("application/xml")
    public Response writeToXml(@QueryParam("filter") FilterWrapper filterWrapper, @Context HttpServletRequest request, @Context SecurityContext sc) throws WebApplicationException, IOException {
        final String xml = comperrVw17Service.writeToXml(filterWrapper, null, null, null);
        StreamingOutput output = new StreamingOutput() {
            @Override
            public void write(OutputStream output) throws IOException, WebApplicationException {
                output.write(xml.getBytes("Unicode"));
                output.close();
            }
        };
        String fileName = "comperrReport17.xml";
        return Response.ok(output).header("Content-Disposition", "attachment; filename=" + fileName).build();
    }

    @GET
    @Path("/view17Access")
    @Produces("application/vnd.ms-access")
    public Response writeToAccess(@QueryParam("filter") FilterWrapper filterWrapper, @Context HttpServletRequest request, @Context SecurityContext sc) throws WebApplicationException, IOException, ClassNotFoundException, SQLException {
        Database db = comperrVw17Service.writeToAccess(filterWrapper, null, null, null);
        final FileInputStream fis = new FileInputStream(db.getFile());
        final byte[] buffer = new byte[1024];
        StreamingOutput output = new StreamingOutput() {
            @Override
            public void write(java.io.OutputStream output) throws IOException, WebApplicationException {
                int byteRead = 0;
                while ((byteRead = fis.read(buffer)) != -1) {
                    output.write(buffer, 0, byteRead);
                }
                output.close();
            }
        };
        String fileName = "comperrReport17.accdb";
        return Response.ok(output).header("Content-Disposition", "attachment; filename=" + fileName).build();
    }

}
