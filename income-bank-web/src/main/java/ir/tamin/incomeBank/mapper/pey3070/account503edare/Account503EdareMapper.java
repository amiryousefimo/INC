package ir.tamin.incomeBank.mapper.pey3070.account503edare;


import ir.tamin.incomeBank.dto.pey3070.account503edare.Account503EdareDto;
import ir.tamin.incomeBank.model.payment3070.account503edare.Account503Edare;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Account503EdareMapper {

    public static Account503Edare mapToEntity(Account503EdareDto dto, Account503Edare entity) throws InvocationTargetException, IllegalAccessException {

        entity.setRowNum(dto.getRowNum());
        entity.setEdareCode(dto.getEdareCode());
        entity.setEdareName(dto.getEdareName());
        entity.setOrdDate(dto.getOrdDate());
        entity.setOrdDate1(dto.getOrdDate1());
        entity.setSumAmount(dto.getSumAmount());
        entity.setMashAmount(dto.getMashAmount());
        entity.setEkhtAmount(dto.getEkhtAmount());
        entity.setSarbaziAmt(dto.getSarbaziAmt());
        entity.setSakhtemanAmt(dto.getSakhtemanAmt());
        entity.setKasrMahAmt(dto.getKasrMahAmt());
        entity.setCountAmt(dto.getCountAmt());
        entity.setDolatAmt(dto.getDolatAmt());
        return entity;
    }


    public static Account503EdareDto mapToDto(Account503Edare entity, Account503EdareDto dto) throws InvocationTargetException, IllegalAccessException {
        dto.setRowNum(entity.getRowNum());
        dto.setEdareCode(entity.getEdareCode());
        dto.setEdareName(entity.getEdareName());
        dto.setOrdDate(entity.getOrdDate());
        dto.setOrdDate1(entity.getOrdDate1());
        dto.setSumAmount(entity.getSumAmount());
        dto.setMashAmount(entity.getMashAmount());
        dto.setEkhtAmount(entity.getEkhtAmount());
        dto.setSarbaziAmt(entity.getSarbaziAmt());
        dto.setSakhtemanAmt(entity.getSakhtemanAmt());
        dto.setKasrMahAmt(entity.getKasrMahAmt());
        dto.setCountAmt(entity.getCountAmt());
        dto.setDolatAmt(entity.getDolatAmt());
        return dto;

    }

    public static List<Account503Edare> mapToEntityList(List<Account503EdareDto> dtoList) throws InvocationTargetException, IllegalAccessException {
        List<Account503Edare> entityList = new ArrayList<>();
        for (Account503EdareDto dto : dtoList) {
            Account503Edare entity = new Account503Edare();
            entity = mapToEntity(dto, entity);
            entityList.add(entity);
        }
        return entityList;
    }

    public static List<Account503EdareDto> mapToDtoList(List<Account503Edare> entities) throws InvocationTargetException, IllegalAccessException {
        List<Account503EdareDto> dtos = new ArrayList<>();
        for (Account503Edare entity : entities) {
            Account503EdareDto dto = new Account503EdareDto();
            dto = mapToDto(entity, dto);
            dtos.add(dto);
        }
        return dtos;
    }

}
