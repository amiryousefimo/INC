package ir.tamin.incomeBank.mapper.pey3070.account503branch;


import ir.tamin.incomeBank.dto.pey3070.account503branch.Account503BranchDto;
import ir.tamin.incomeBank.model.payment3070.account503branch.Account503Branch;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Account503BranchMapper {

    public static Account503Branch mapToEntity(Account503BranchDto dto, Account503Branch entity) throws InvocationTargetException, IllegalAccessException {

        entity.setRowNum(dto.getRowNum());
        entity.setEdareCode(dto.getEdareCode());
        entity.setEdareName(dto.getEdareName());
        entity.setBrchCode(dto.getBrchCode());
        entity.setBrchName(dto.getBrchName());
        entity.setOrdDate(dto.getOrdDate());
        entity.setOrdDate1(dto.getOrdDate1());
        entity.setSumAmount(dto.getSumAmount());
        entity.setMashAmount(dto.getMashAmount());
        entity.setEkhtAmount(dto.getKasrMahAmt());
        entity.setSarbaziAmt(dto.getSarbaziAmt());
        entity.setSakhtemanAmt(dto.getSakhtemanAmt());
        entity.setKasrMahAmt(dto.getKasrMahAmt());
        entity.setCountAmt(dto.getCountAmt());
        entity.setDolatAmt(dto.getDolatAmt());
        return entity;
    }


    public static Account503BranchDto mapToDto(Account503Branch entity, Account503BranchDto dto) throws InvocationTargetException, IllegalAccessException {
        dto.setRowNum(entity.getRowNum());
        dto.setEdareCode(entity.getEdareCode());
        dto.setEdareName(entity.getEdareName());
        dto.setBrchCode(entity.getBrchCode());
        dto.setBrchName(entity.getBrchName());
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

    public static List<Account503Branch> mapToEntityList(List<Account503BranchDto> dtoList) throws InvocationTargetException, IllegalAccessException {
        List<Account503Branch> entityList = new ArrayList<>();
        for (Account503BranchDto dto : dtoList) {
            Account503Branch entity = new Account503Branch();
            entity = mapToEntity(dto, entity);
            entityList.add(entity);
        }
        return entityList;
    }

    public static List<Account503BranchDto> mapToDtoList(List<Account503Branch> entities) throws InvocationTargetException, IllegalAccessException {
        List<Account503BranchDto> dtos = new ArrayList<>();
        for (Account503Branch entity : entities) {
            Account503BranchDto dto = new Account503BranchDto();
            dto = mapToDto(entity, dto);
            dtos.add(dto);
        }
        return dtos;
    }

}
