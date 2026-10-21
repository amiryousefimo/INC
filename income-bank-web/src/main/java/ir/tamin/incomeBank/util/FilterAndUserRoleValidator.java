package ir.tamin.incomeBank.util;

import ir.tamin.framework.domain.proxy.ProxyProcessingException;
import ir.tamin.framework.ws.rest.json.Filter;
import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.security.TokenContext;
import ir.tamin.incomeBank.service.identityManager.UserBean;
import ir.tamin.portal.model.org.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;


/**
 * @author A_Jamali
 */

@ApplicationScoped
public class FilterAndUserRoleValidator {
    @Inject
    private TokenContext tokenContext;
    @Inject
    private UserBean userBean;


    public void filterIsValid(FilterWrapper filterWrapper) throws Exception {
        if (filterWrapper == null || filterWrapper.isEmpty()) {
            throw new IllegalArgumentException("Filter Can Not Be Null");
        }
        User user = this.userBean.getUserByUserName(this.tokenContext.getCurrentUser().getName());
        switch (user.getOrganization().getOrganizationCustomerType()) {
            case "Branch": {
                validateBranchCode(user, filterWrapper);
                break;
            }
            case "Department": {
                validateDepartmentCode(user, filterWrapper);
                break;
            }
            case "Company":
                break;
            default:
                throw new ProxyProcessingException("403 forbidden", new String[0]);
        }

    }

    private void validateBranchCode(User user, FilterWrapper filterWrapper) {
        String branchCode = user.getOrganization().getCode();
        String edareCode = user.getOrganization().getParent().getCode();
        for (Filter filter : filterWrapper.getFilters()) {
            Object value = filter.getValue();
            String field = filter.getProperty();
            if ("brchCode".equalsIgnoreCase(field)) {
                if (!branchCode.equals(value)) {
                    throw new IllegalArgumentException("You Do Not Have Permission For Other Branches");
                }
            }
            if ("brhCode".equalsIgnoreCase(field)) {
                if (!branchCode.equals(value)) {
                    throw new IllegalArgumentException("You Do Not Have Permission For Other Branches");
                }
            }
            if ("branchCode".equalsIgnoreCase(field)) {
                if (!branchCode.equals(value)) {
                    throw new IllegalArgumentException("You Do Not Have Permission For Other Branches");
                }
            }
            if ("edareCode".equalsIgnoreCase(field)) {
                if (!edareCode.equals(value)) {
                    throw new IllegalArgumentException("You Do Not Have Permission For Other Department");
                }


            }
        }
    }

    private void validateDepartmentCode(User user, FilterWrapper filterWrapper) {
        String edareCode = user.getOrganization().getCode();
        for (Filter filter : filterWrapper.getFilters()) {
            Object value = filter.getValue();
            String field = filter.getProperty();
            if ("edareCode".equalsIgnoreCase(field)) {
                if (!edareCode.equals(value)) {
                    throw new IllegalArgumentException("You Do Not Have Permission For Other Department");
                }
            }
        }
    }


    public String startDateFromFilter(FilterWrapper filterWrapper) {

        if (filterWrapper.getFilters().toString().contains("AFTER")) {
            return filterWrapper.getFilter("soratDate").getValue();
        }
        return null;
    }
    public String endDateFromFilter(FilterWrapper filterWrapper) {

        if (filterWrapper.getFilters().toString().contains("BEFORE")) {
            return filterWrapper.getFilter("soratDate").getValue();
        }
        return null;
    }


//    public FilterWrapper getFilterWrapperForBrch(FilterWrapper filterWrapper) throws Exception {
//        if (filterWrapper == null) {
//            filterWrapper = new FilterWrapper();
//            filterWrapper.setFilters(new HashSet<>());
//        }
//
//        User user = this.userBean.getUserByUserName(this.tokenContext.getCurrentUser().getName());
//        switch (user.getOrganization().getOrganizationCustomerType()) {
//            case "Branch":
//                filterWrapper.removeFilter("brchCode", Operator._EQUAL);
//                filterWrapper.removeFilter("edareCode", Operator._EQUAL);
//                filterWrapper.addFilter("brchCode", Operator.EQUAL, user.getOrganization().getCode());
//                break;
//            case "Department":
//                filterWrapper.removeFilter("edareCode", Operator._EQUAL);
//                filterWrapper.addFilter("edareCode", Operator.EQUAL, user.getOrganization().getCode());
//            case "Company":
//                break;
//            default:
//                throw new ProxyProcessingException("403 forbidden", new String[0]);
//        }
//
//        return filterWrapper;
//    }

}
