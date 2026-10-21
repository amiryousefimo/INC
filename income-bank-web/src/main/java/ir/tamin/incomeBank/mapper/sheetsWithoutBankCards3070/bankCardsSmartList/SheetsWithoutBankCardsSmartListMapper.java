package ir.tamin.incomeBank.mapper.sheetsWithoutBankCards3070.bankCardsSmartList;

import ir.tamin.incomeBank.dto.sheetsWithoutBankCards3070.bankCards3070.SheetsWithoutBankCards3070Dto;
import ir.tamin.incomeBank.dto.sheetsWithoutBankCards3070.bankCardsSmartList.SheetsWithoutBankCardsSmartListDto;
import ir.tamin.incomeBank.model.sheetsWithoutBankCards.bankCards3070.SheetsWithoutBankCards3070;
import ir.tamin.incomeBank.model.sheetsWithoutBankCards.bankCardsSmartList.SheetsWithoutBankCardsSmartList;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class SheetsWithoutBankCardsSmartListMapper {
    public static List<SheetsWithoutBankCardsSmartListDto> mapToDtoList(List<SheetsWithoutBankCardsSmartList> entities) throws InvocationTargetException, IllegalAccessException {
        List<SheetsWithoutBankCardsSmartListDto> dtos = new ArrayList<>();
        for (SheetsWithoutBankCardsSmartList entity : entities) {
            SheetsWithoutBankCardsSmartListDto dto = new SheetsWithoutBankCardsSmartListDto();
            dto = mapToDto(entity, dto);
            dtos.add(dto);
        }
        return dtos;
    }


    public static SheetsWithoutBankCardsSmartListDto mapToDto(SheetsWithoutBankCardsSmartList entity, SheetsWithoutBankCardsSmartListDto dto)  {
        dto.setRowId(entity.getRowId());

        dto.setEdareCode(entity.getEdareCode());
        dto.setEdareName(entity.getEdareName());

        dto.setBrchCode(entity.getBrchCode());
        dto.setBrchName(entity.getBrchName());

        dto.setOrderNumber(entity.getOrderNumber());

        dto.setGlAmount(entity.getGlAmount());

        dto.setGlDate(entity.getGlDate());
        dto.setGlDate2(entity.getGlDate2());

        dto.setGlBank(entity.getGlBank());

        dto.setCustomerId(entity.getCustomerId());
        dto.setCustomerName(entity.getCustomerName());


        return dto;

    }
}
