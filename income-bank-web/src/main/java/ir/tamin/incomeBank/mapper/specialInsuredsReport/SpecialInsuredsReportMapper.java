package ir.tamin.incomeBank.mapper.specialInsuredsReport;

import ir.tamin.incomeBank.dto.smartlist.branch.SmartListBranchDto;
import ir.tamin.incomeBank.dto.specialInsuredsReport.SpecialInsuredsReportDto;
import ir.tamin.incomeBank.model.smartlist.branch.SmartListBranch;
import ir.tamin.incomeBank.model.specialInsuredsReport.SpecialInsuredsReport;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author m_goharriz
 */
public class SpecialInsuredsReportMapper {


    public static SpecialInsuredsReportDto mapToDto(SpecialInsuredsReport entity, SpecialInsuredsReportDto dto)  {
        dto.setRowNum(entity.getRowNum());
        dto.setSoratOrdPayment(entity.getSoratOrdPayment());
        dto.setSoratDate1(entity.getSoratDate());
        dto.setSoratPrice1(entity.getSoratPrice1());
        dto.setSoratPaymentSeq(entity.getSoratPaymentSeq());
        dto.setSoratEfDate(entity.getSoratEfDate());
        dto.setSystemType(entity.getSystemType());
        dto.setSystemDesc(entity.getSystemDesc());

        return dto;

    }


    public static List<SpecialInsuredsReportDto> mapToDtoList(List<SpecialInsuredsReport> entities) throws InvocationTargetException, IllegalAccessException {
        List<SpecialInsuredsReportDto> dtos = new ArrayList<>();
        for (SpecialInsuredsReport entity : entities) {
            SpecialInsuredsReportDto dto = new SpecialInsuredsReportDto();
            dto = mapToDto(entity, dto);
            dtos.add(dto);
        }
        return dtos;
    }


}
