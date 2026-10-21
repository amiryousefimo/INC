package ir.tamin.incomeBank.mapper.sheetsWithoutBankCards3070.bankCards3070;

import ir.tamin.incomeBank.dto.paymentWorkers.branch.PaymentWorkersBranchDto;
import ir.tamin.incomeBank.dto.sheetsWithoutBankCards3070.bankCards3070.SheetsWithoutBankCards3070Dto;
import ir.tamin.incomeBank.model.paymentWorkers.branch.PaymentWorkersBranch;
import ir.tamin.incomeBank.model.sheetsWithoutBankCards.bankCards3070.SheetsWithoutBankCards3070;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class SheetsWithoutBankCards3070Mapper {
    public static List<SheetsWithoutBankCards3070Dto> mapToDtoList(List<SheetsWithoutBankCards3070> entities) throws InvocationTargetException, IllegalAccessException {
        List<SheetsWithoutBankCards3070Dto> dtos = new ArrayList<>();
        for (SheetsWithoutBankCards3070 entity : entities) {
            SheetsWithoutBankCards3070Dto dto = new SheetsWithoutBankCards3070Dto();
            dto = mapToDto(entity, dto);
            dtos.add(dto);
        }
        return dtos;
    }


    public static SheetsWithoutBankCards3070Dto mapToDto(SheetsWithoutBankCards3070 entity, SheetsWithoutBankCards3070Dto dto)  {
        dto.setRowId(entity.getRowId());

        dto.setEdareCode(entity.getEdareCode());
        dto.setEdareName(entity.getEdareName());

        dto.setBrchCode(entity.getBrchCode());
        dto.setBrchName(entity.getBrchName());

        dto.setOrderNumber(entity.getOrderNumber());

        dto.setGlAmount(entity.getGlAmount());

        dto.setGlDate(entity.getGlDate());
        dto.setGlDate2(entity.getGlDate2());

        dto.setSystemType(entity.getSystemType());
        dto.setSystemDesc(entity.getSystemDesc());

        dto.setCustomerId(entity.getCustomerId());
        dto.setCustomerName(entity.getCustomerName());


        return dto;

    }
}
