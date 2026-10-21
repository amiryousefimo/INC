package ir.tamin.incomeBank.service.baseinfo;


import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.portal.model.baseinfo.geography.GeoUnit;
import ir.tamin.portal.service.implementation.ClientService;

import javax.ejb.Stateless;
import java.util.Map;

import static ir.tamin.portal.enumeration.ParameterNameEnum.*;


/**
 * @author s_maknooni
 * {@code @developer A_Jamali}
 */
@Stateless
public class ProvinceService extends ClientService {

        public Map<String, Object> getAll(FilterWrapper filter, Integer start, Integer limit, SortWrapper sort) throws Exception {

        String url = getUrl("province.base");
        String parametersString = getParametersString(
                getNameValuePairs(
                        filter != null ? getNameValuePair(FILTER.getName(), filter.getFilters()) : null,
                        start != null ? getNameValuePair(START.getName(), start) : null,
                        limit != null ? getNameValuePair(LIMIT.getName(), limit) : null,
                        sort != null ? getNameValuePair(SORT.getName(), sort.getSortSet()) : null
                )
        );
        url = url + "?" + parametersString;
        String output = getResponse(url, null);
        return getDataPageListAndTotal(output, GeoUnit.class);
    }



}
