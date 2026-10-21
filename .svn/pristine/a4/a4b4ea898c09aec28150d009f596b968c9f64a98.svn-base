package ir.tamin.incomeBank.mapper.pey3070.account503;



import ir.tamin.incomeBank.dto.pey3070.account503.Account503Dto;
import ir.tamin.incomeBank.model.payment3070.account503.Account503;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Account503Mapper {

    public static Account503 mapToEntity(Account503Dto dto, Account503 entity) throws InvocationTargetException, IllegalAccessException {

        entity.setRowNum(dto.getRowNum());
        entity.setEdareCode(dto.getEdareCode());
        entity.setEdareName(dto.getEdareName());
        entity.setBrchCode(dto.getBrchCode());
        entity.setBrchName(dto.getBrchName());
        entity.setOrdOrderNumber(dto.getOrdOrderNumber());
        entity.setOrdDate(dto.getOrdDate());
        entity.setNationalCode(dto.getNationalCode());
        entity.setNameOfTheInsured(dto.getNameOfTheInsured());
        entity.setDebitReasonDesc(dto.getDebitReasonDesc());
        entity.setStartDateOfPeriod(dto.getStartDateOfPeriod());
        entity.setEndDateOfPeriod(dto.getEndDateOfPeriod());
        entity.setSendDateOfInfoToIncoming(dto.getSendDateOfInfoToIncoming());
        entity.setDebitReasonCode(dto.getDebitReasonCode());
        entity.setGovernmentFlag(dto.getGovernmentFlag());
        entity.setSystemType(dto.getSystemType());
        entity.setOrderAmount(dto.getOrderAmount());
        entity.setGovernmentAmount(dto.getGovernmentAmount());
        entity.setSoratDate(dto.getSoratDate());
        entity.setDocumentStatus(dto.getDocumentStatus());
        return entity;
    }


    public static Account503Dto mapToDto(Account503 entity, Account503Dto dto) throws InvocationTargetException, IllegalAccessException {
        dto.setRowNum(entity.getRowNum());
        dto.setEdareCode(entity.getEdareCode());
        dto.setEdareName(entity.getEdareName());
        dto.setBrchCode(entity.getBrchCode());
        dto.setBrchName(entity.getBrchName());
        dto.setOrderAmount(entity.getOrderAmount());
        dto.setOrdOrderNumber(entity.getOrdOrderNumber());
        dto.setOrdDate(entity.getOrdDate());
        dto.setNationalCode(entity.getNationalCode());
        dto.setNameOfTheInsured(entity.getNameOfTheInsured());
        dto.setDebitReasonDesc(entity.getDebitReasonDesc());
        dto.setStartDateOfPeriod(entity.getStartDateOfPeriod());
        dto.setEndDateOfPeriod(entity.getEndDateOfPeriod());
        dto.setSendDateOfInfoToIncoming(entity.getSendDateOfInfoToIncoming());
        dto.setDebitReasonCode(entity.getDebitReasonCode());
        dto.setGovernmentFlag(entity.getGovernmentFlag());
        dto.setSystemType(entity.getSystemType());
        dto.setOrderAmount(entity.getOrderAmount());
        dto.setGovernmentAmount(entity.getGovernmentAmount());
        dto.setSoratDate(entity.getSoratDate());
        dto.setDocumentStatus(entity.getDocumentStatus());
        return dto;

    }

    public static List<Account503> mapToEntityList(List<Account503Dto> dtoList) throws InvocationTargetException, IllegalAccessException {
        List<Account503> entityList = new ArrayList<>();
        for (Account503Dto dto : dtoList) {
            Account503 entity = new Account503();
            entity = mapToEntity(dto, entity);
            entityList.add(entity);
        }
        return entityList;
    }

    public static List<Account503Dto> mapToDtoList(List<Account503> entities) throws InvocationTargetException, IllegalAccessException {
        List<Account503Dto> dtos = new ArrayList<>();
        for (Account503 entity : entities) {
            Account503Dto dto = new Account503Dto();
            dto = mapToDto(entity, dto);
            dtos.add(dto);
        }
        return dtos;
    }

}
