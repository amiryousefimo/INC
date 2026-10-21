package ir.tamin.incomeBank.service.daramadBank;

import com.healthmarketscience.jackcess.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import ir.tamin.framework.ws.rest.json.Filter;
import ir.tamin.framework.ws.rest.json.FilterWrapper;
import ir.tamin.framework.ws.rest.json.Sort;
import ir.tamin.framework.ws.rest.json.SortWrapper;
import ir.tamin.incomeBank.model.daramadBank.Vwvogenlist;
import ir.tamin.incomeBank.service.identityManager.UserBean;
import ir.tamin.incomeBank.util.DateUtils;
import ir.tamin.incomeBank.util.ServiceUtils;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author m_salami
 * {@code @developer A_Jamali}
 */
public class VwvogenlistService {

    @Inject
    private EntityManager em;
    @Inject
    UserBean userBean;
    @Inject
    private ServiceUtils<Vwvogenlist> serviceUtils;

    public Map<String, Object> getAll(FilterWrapper filter, Integer start, Integer limit, SortWrapper sort) {

        Map<String, Object> map = new HashMap<>();
        map.put("list", getList(filter, start, limit, sort));
        map.put("total", getCount(filter));
        map.put("fullSumPrice", getSumPrice(filter, null, null, sort));
        map.put("sumPrice", start != null && start > 0 ? getSumPrice(filter, 0, start, sort) : 0);
        return map;
    }

    public List<Vwvogenlist> getList(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sort) {

        TypedQuery<Vwvogenlist> createQuery = em.createQuery(getQuery(filterWrapper, sort));

        List<Vwvogenlist> bankRcvList = serviceUtils.getListWithConditions(start, limit, createQuery);
        for (Vwvogenlist card : bankRcvList) {
            card.setDatHavTimeStamp(DateUtils.convertDateToTimestampString(DateUtils.convertPersianDateStringToDate(card.getBankrcvDathav())));
        }
        return bankRcvList;
    }

    public Integer getCount(FilterWrapper filter) {
        TypedQuery<Vwvogenlist> createQuery = em.createQuery(getQuery(filter, null));
        return createQuery.getResultList().size();

    }

    private CriteriaQuery<Vwvogenlist> getQuery(FilterWrapper filterWrapper, SortWrapper sortWrapper) {
        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Vwvogenlist> criteriaQuery = criteriaBuilder.createQuery(Vwvogenlist.class);
            Root<Vwvogenlist> vwvogenlistForm = criteriaQuery.from(Vwvogenlist.class);
            List<Predicate> predicates = new ArrayList<>();
            Metamodel metamodel = em.getMetamodel();
            EntityType<Vwvogenlist> vwvogenlistEntityType = metamodel.entity(Vwvogenlist.class);
            if (filterWrapper != null && filterWrapper.getFilters() != null) {
                for (Filter filter : filterWrapper.getFilters()) {

                    Object value = filter.getValue();
                    String field = filter.getProperty();
                    Filter.Operator operator = filter.getOperator();
                    String[] f = field.split("\\.");
                    if (f[0].equals("bankrcvDathavFrom") || f[0].equals("bankrcvDathavTo")) {
                        f[0] = "bankrcvDathav";
                        value = DateUtils.format(DateUtils.convertTimestampStringToDate(value.toString())).replace("/", "");
                    } else if (f[0].equals("yearmon")) {
                        value = DateUtils.format(DateUtils.convertTimestampStringToDate(value.toString())).replace("/", "").substring(0, 6);
                    } else if (f[0].equals("bankrcvPriceFrom") || f[0].equals("bankrcvPriceTo")) {
                        f[0] = "bankrcvPrice";
                    }
                    Predicate predicate = null;
                    Path path = null;
                    switch (operator) {

                        case _EQUAL:
                        case EQUAL:
                            path = vwvogenlistForm.get(f[0]);
                            for (int j = 1; j < f.length; j++) {
                                path = path.get(f[j]);
                            }
                            predicate = criteriaBuilder.equal(path, value);
                            predicates.add(predicate);
                            break;
                        case LIKE:

                            predicates.add(criteriaBuilder.like(vwvogenlistForm.get(vwvogenlistEntityType.getDeclaredSingularAttribute(field, String.class)), "%" + value + "%"));

                            break;
                        case BEFORE:
                            path = vwvogenlistForm.get(f[0]);
                            for (int j = 1; j < f.length; j++) {
                                path = path.get(f[j]);
                            }
                            predicate = criteriaBuilder.lessThanOrEqualTo(path, (Comparable) value);
                            predicates.add(predicate);
                            break;
                        case AFTER:
                            path = vwvogenlistForm.get(f[0]);
                            for (int j = 1; j < f.length; j++) {
                                path = path.get(f[j]);
                            }
                            predicate = criteriaBuilder.greaterThanOrEqualTo(path, (Comparable) value);
                            predicates.add(predicate);
                            break;
                        default:
                            break;
                    }
                }
                criteriaQuery.where(predicates.toArray(new Predicate[]{}));
            }

            if (sortWrapper != null) {
                List<Order> orders = new ArrayList<>();
                for (Sort sortSet : sortWrapper.getSortSet()) {
                    Order order = null;
                    String[] sortProperties = sortSet.getProperty().split("\\.");

                    Path<String> path = vwvogenlistForm.get(sortProperties[0]);
                    for (int j = 1; j < sortProperties.length; j++) {
                        path = path.get(sortProperties[j]);
                    }
                    if (sortProperties.length == 1) {
                        if (sortSet.getDirection().getName().equals(Sort.Direction.DESC.getName())) {
                            order = criteriaBuilder.desc(vwvogenlistForm.get(sortProperties[0]));
                        } else {
                            order = criteriaBuilder.asc(vwvogenlistForm.get(sortProperties[0]));
                        }
                    }
                    orders.add(order);
                }

                if (!orders.isEmpty()) {
                    criteriaQuery.orderBy(orders);
                }
            }

            criteriaQuery.select(vwvogenlistForm);
            return criteriaQuery;

        } catch (Exception e) {
            return null;
        }
    }


    private BigInteger getSumPrice(FilterWrapper filter, Integer start, Integer limit, SortWrapper sort) {
        BigInteger sumPrice = BigInteger.ZERO;
        TypedQuery<Vwvogenlist> createQuery = em.createQuery(getQuery(filter, sort));
        List<Vwvogenlist> list = serviceUtils.getListWithConditions(start, limit, createQuery);
        if (list.size() > 0) {
            for (Vwvogenlist vwvogenlist : list) {
                sumPrice = sumPrice.add(vwvogenlist.getBankrcvPrice());
            }
        }
        return sumPrice;
    }

    public Workbook writeToExcel(FilterWrapper filterWrapper, Integer start, Integer limit, SortWrapper sort) throws IOException {

        XSSFWorkbook wb = new XSSFWorkbook();

        Sheet sheet = wb.createSheet();
        sheet.setRightToLeft(true);

        Font font = wb.createFont();

        CellStyle headerStyle = wb.createCellStyle();

        font.setFontName("Arial");
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.BLACK.index);

        headerStyle.setBorderBottom(CellStyle.BORDER_THIN);
        headerStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        headerStyle.setBorderLeft(CellStyle.BORDER_THIN);
        headerStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        headerStyle.setBorderRight(CellStyle.BORDER_THIN);
        headerStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        headerStyle.setBorderTop(CellStyle.BORDER_THIN);
        headerStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        headerStyle.setFont(font);

        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

        CellStyle detailStyle = wb.createCellStyle();
        detailStyle.setBorderBottom(CellStyle.BORDER_THIN);
        detailStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        detailStyle.setBorderLeft(CellStyle.BORDER_THIN);
        detailStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        detailStyle.setBorderRight(CellStyle.BORDER_THIN);
        detailStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        detailStyle.setBorderTop(CellStyle.BORDER_THIN);
        detailStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());

        Row row = sheet.createRow(0);

        Cell cell0 = row.createCell(0);
        cell0.setCellValue("ردیف");
        cell0.setCellStyle(headerStyle);

        Cell cell1 = row.createCell(1);
        cell1.setCellValue("دوره عملکرد");
        cell1.setCellStyle(headerStyle);

        Cell cell2 = row.createCell(2);
        cell2.setCellValue("شماره حواله");
        cell2.setCellStyle(headerStyle);

        Cell cell3 = row.createCell(3);
        cell3.setCellValue("تاریخ حواله");
        cell3.setCellStyle(headerStyle);

        Cell cell4 = row.createCell(4);
        cell4.setCellValue("مبلغ");
        cell4.setCellStyle(headerStyle);

        Cell cell5 = row.createCell(5);
        cell5.setCellValue("ردیف بانک");
        cell5.setCellStyle(headerStyle);


        try {
            Map<String, Object> map = getAll(filterWrapper, null, null, null);
            List<Vwvogenlist> comperrList = (List<Vwvogenlist>) map.get("list");
            int rownum = 1;
            for (Vwvogenlist item : comperrList) {
                String bankrcvDathav = (item.getBankrcvDathav() != null ? item.getBankrcvDathav().substring(0, 4) + "/" + item.getBankrcvDathav().substring(4, 6) + "/" + item.getBankrcvDathav().substring(6, 8) : " ");
                String yearmon = item.getYearmon();
                String bankRadif = item.getBankRadif();
                Long bankrcvPrice = item.getBankrcvPrice().longValue();
                String bankrcvNohav = item.getBankrcvNohav();

                row = sheet.createRow(rownum++);
                int cellnum = 0;

                Cell cell = row.createCell(cellnum++);
                cell.setCellStyle(detailStyle);
                cell.setCellValue(rownum - 1);

                cell = row.createCell(cellnum++);
                cell.setCellStyle(detailStyle);
                cell.setCellValue(yearmon);

                cell = row.createCell(cellnum++);
                cell.setCellStyle(detailStyle);
                cell.setCellValue(bankrcvNohav);

                cell = row.createCell(cellnum++);
                cell.setCellStyle(detailStyle);
                cell.setCellValue(bankrcvDathav);

                cell = row.createCell(cellnum++);
                cell.setCellStyle(detailStyle);
                cell.setCellValue(bankrcvPrice);

                cell = row.createCell(cellnum++);
                cell.setCellStyle(detailStyle);
                cell.setCellValue(bankRadif);

            }

        } catch (Exception ex) {
            Logger.getLogger(VwvogenlistService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return wb;
    }

    public String writeToXml(FilterWrapper filter, Integer start, Integer limit, SortWrapper sort) throws IOException {
        try {

            Map<String, Object> map = getAll(filter, null, null, null);
            List<Vwvogenlist> comperrList = (List<Vwvogenlist>) map.get("list");
            XStream xStream = new XStream(new DomDriver());
            String xml = xStream.toXML(comperrList);
            return xml;
        } catch (Exception ex) {
            Logger.getLogger(VwvogenlistService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Database writeToAccess(FilterWrapper filter, Integer start, Integer limit, SortWrapper sort) throws ClassNotFoundException, SQLException, IOException, NumberFormatException {

        String file = "bankrcv.accdb";
        Map<String, Object> map = getAll(filter, null, null, null);
        List<Vwvogenlist> comperrList = (List<Vwvogenlist>) map.get("list");

        try {
            Database db = (DatabaseBuilder.create(Database.FileFormat.V2010, new File(file)));
            String bankrcv = "bankrcv";
            Table table = new TableBuilder(bankrcv) // Creating table
                    .addColumn(new ColumnBuilder("YEARMON").setSQLType(Types.VARCHAR).toColumn())
                    .addColumn(new ColumnBuilder("BANKRCVNOHAV").setSQLType(Types.VARCHAR).toColumn())
                    .addColumn(new ColumnBuilder("BANKRCVDATHAV").setSQLType(Types.VARCHAR).toColumn())
                    .addColumn(new ColumnBuilder("BANKRCVPRICE").setSQLType(Types.NUMERIC).toColumn())
                    .addColumn(new ColumnBuilder("BANKRADIF").setSQLType(Types.VARCHAR).toColumn())
                    .toTable(db);
            for (Vwvogenlist item : comperrList) {

                String bankrcvDathav = (item.getBankrcvDathav() != null ? item.getBankrcvDathav().substring(0, 4) + "/" + item.getBankrcvDathav().substring(4, 6) + "/" + item.getBankrcvDathav().substring(6, 8) : " ");
                String yearmon = item.getYearmon();
                String bankRadif = item.getBankRadif();
                Long bankrcvPrice = item.getBankrcvPrice().longValue();
                String bankrcvNohav = item.getBankrcvNohav();
                table.addRow(yearmon, bankrcvNohav, bankrcvDathav, bankrcvPrice, bankRadif);//Inserting values into the table
            }
            db.close();
            return db;
        } catch (Exception ex) {
            Logger.getLogger(VwvogenlistService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
