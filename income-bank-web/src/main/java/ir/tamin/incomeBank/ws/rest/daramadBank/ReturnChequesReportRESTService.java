package ir.tamin.incomeBank.ws.rest.daramadBank;

import ir.tamin.incomeBank.service.daramadBank.ReturnChequesReportService;
import ir.tamin.incomeBank.ws.rest.Roles;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 * @author A_Jamali
 */
@Path("/return-cheques-report")
@RequestScoped
public class ReturnChequesReportRESTService {

    @Inject
    private org.slf4j.Logger logger;
    @Inject
    private ReturnChequesReportService returnChequesReportService;

    @GET
    @Produces("application/pdf")
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
    public Response loadReport(@QueryParam("branchCode") String branchCode, @QueryParam("from") String dateFrom, @QueryParam("to") String dateTo, @Context SecurityContext security) throws Exception {
        try {
            byte[] byteStream = returnChequesReportService.loadReport(branchCode, dateFrom, dateTo, security);
            logger.info("report log end.");
            return Response
                    .ok(byteStream)
                    .header("Content-Type", "application/pdf")
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
