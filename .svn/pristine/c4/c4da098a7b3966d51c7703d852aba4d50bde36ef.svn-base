package ir.tamin.incomeBank.mapper.monthlyComparisonReportBankAndOfficeStatement;


import ir.tamin.incomeBank.dto.monthlyComparisonReportBankAndOfficeStatement.MonthlyComparisonReportBankAndOfficeStatementDto;
import ir.tamin.incomeBank.model.announcement.monthlyComparisonReportBankAndOfficeStatement.MonthlyComparisonReportBankAndOfficeStatement;

import java.util.ArrayList;
import java.util.List;

public class MonthlyComparisonReportBankAndOfficeStatementMapper {

    public static MonthlyComparisonReportBankAndOfficeStatement mapTpEntity(MonthlyComparisonReportBankAndOfficeStatementDto dto, MonthlyComparisonReportBankAndOfficeStatement entity) {
        entity.setRowNum(dto.getRowNum());
        entity.setDate(dto.getDate());
        entity.setSumCard(dto.getSumCard());
        entity.setSumLetter(dto.getSumLetter());
        return entity;

    }

    public static MonthlyComparisonReportBankAndOfficeStatementDto mapToDto(MonthlyComparisonReportBankAndOfficeStatement entity, MonthlyComparisonReportBankAndOfficeStatementDto dto) {
        dto.setRowNum(entity.getRowNum());
        dto.setDate(entity.getDate());
        dto.setSumCard(entity.getSumCard());
        dto.setSumLetter(entity.getSumLetter());
        return dto;
    }

    public static List<MonthlyComparisonReportBankAndOfficeStatement> mapToListEntity(List<MonthlyComparisonReportBankAndOfficeStatementDto> dtoList) {
        List<MonthlyComparisonReportBankAndOfficeStatement> entityList = new ArrayList<>();
        for (MonthlyComparisonReportBankAndOfficeStatementDto dto : dtoList) {
            MonthlyComparisonReportBankAndOfficeStatement entity = new MonthlyComparisonReportBankAndOfficeStatement();
            entity = mapTpEntity(dto, entity);
            entityList.add(entity);
        }
        return entityList;
    }

    public static List<MonthlyComparisonReportBankAndOfficeStatementDto> mapToDtoList(List<MonthlyComparisonReportBankAndOfficeStatement> entities) {
        List<MonthlyComparisonReportBankAndOfficeStatementDto> dtos = new ArrayList<>();
        for (MonthlyComparisonReportBankAndOfficeStatement entity : entities) {
            MonthlyComparisonReportBankAndOfficeStatementDto dto = new MonthlyComparisonReportBankAndOfficeStatementDto();
            dto = mapToDto(entity, dto);
            dtos.add(dto);
        }
        return dtos;
    }


}
