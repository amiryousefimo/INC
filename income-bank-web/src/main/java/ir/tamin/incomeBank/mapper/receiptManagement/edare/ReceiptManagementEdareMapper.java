package ir.tamin.incomeBank.mapper.receiptManagement.edare;



import ir.tamin.incomeBank.dto.receiptManagement.edare.ReceiptManagementEdareDto;
import ir.tamin.incomeBank.model.receiptManagement.edare.ReceiptManagementEdare;

import java.util.ArrayList;
import java.util.List;

public class ReceiptManagementEdareMapper {

    public static ReceiptManagementEdare mapTpEntity(ReceiptManagementEdareDto dto, ReceiptManagementEdare entity) {
        entity.setRowNum(dto.getRowNum());
        entity.setEdareCode(dto.getEdareCode());
        entity.setEdareName(dto.getEdareName());
        entity.setSoratDate(dto.getSoratDate());
        entity.setSabaPrc(dto.getSabaPrc());
        entity.setPrc3070(dto.getPrc3070());
        entity.setOraghPrc(dto.getOraghPrc());
        entity.setNaghdiPrc(dto.getNaghdiPrc());
        entity.setKarkonanPrc(dto.getKarkonanPrc());
        entity.setIntljPrc(dto.getIntljPrc());
        entity.setSumPrc(dto.getSumPrc());
        return entity;

    }

    public static ReceiptManagementEdareDto mapToDto(ReceiptManagementEdare entity, ReceiptManagementEdareDto dto) {
        dto.setRowNum(entity.getRowNum());
        dto.setEdareCode(entity.getEdareCode());
        dto.setEdareName(entity.getEdareName());
        dto.setSoratDate(entity.getSoratDate());
        dto.setKarkonanPrc(entity.getKarkonanPrc());
        dto.setSabaPrc(entity.getSabaPrc());
        dto.setIntljPrc(entity.getIntljPrc());
        dto.setOraghPrc(entity.getOraghPrc());
        dto.setNaghdiPrc(entity.getNaghdiPrc());
        dto.setPrc3070(entity.getPrc3070());
        dto.setSumPrc(entity.getSumPrc());
        return dto;
    }

    public static List<ReceiptManagementEdare> mapToListEntity(List<ReceiptManagementEdareDto> dtoList) {
        List<ReceiptManagementEdare> entityList = new ArrayList<>();
        for (ReceiptManagementEdareDto dto : dtoList) {
            ReceiptManagementEdare entity = new ReceiptManagementEdare();
            entity = mapTpEntity(dto, entity);
            entityList.add(entity);
        }
        return entityList;
    }

    public static List<ReceiptManagementEdareDto> mapToDtoList(List<ReceiptManagementEdare> entities) {
        List<ReceiptManagementEdareDto> dtos = new ArrayList<>();
        for (ReceiptManagementEdare entity : entities) {
            ReceiptManagementEdareDto dto = new ReceiptManagementEdareDto();
            dto = mapToDto(entity, dto);
            dtos.add(dto);
        }
        return dtos;
    }



}
