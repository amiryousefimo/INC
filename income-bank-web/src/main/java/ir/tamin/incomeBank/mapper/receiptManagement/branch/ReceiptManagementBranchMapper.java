package ir.tamin.incomeBank.mapper.receiptManagement.branch;




import ir.tamin.incomeBank.dto.receiptManagement.branch.ReceiptManagementBranchDto;
import ir.tamin.incomeBank.model.receiptManagement.branch.ReceiptManagementBranch;

import java.util.ArrayList;
import java.util.List;

public class ReceiptManagementBranchMapper {

    public static ReceiptManagementBranch mapTpEntity(ReceiptManagementBranchDto dto, ReceiptManagementBranch entity) {
        entity.setRowNum(dto.getRowNum());
        entity.setEdareCode(dto.getEdareCode());
        entity.setEdareName(dto.getEdareName());
        entity.setBrchName(dto.getBrchName());
        entity.setBrchCode(dto.getBrchCode());
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

    public static ReceiptManagementBranchDto mapToDto(ReceiptManagementBranch entity, ReceiptManagementBranchDto dto) {
        dto.setRowNum(entity.getRowNum());
        dto.setEdareCode(entity.getEdareCode());
        dto.setEdareName(entity.getEdareName());
        dto.setBrchCode(entity.getBrchCode());
        dto.setBrchName(entity.getBrchName());
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

    public static List<ReceiptManagementBranch> mapToListEntity(List<ReceiptManagementBranchDto> dtoList) {
        List<ReceiptManagementBranch> entityList = new ArrayList<>();
        for (ReceiptManagementBranchDto dto : dtoList) {
            ReceiptManagementBranch entity = new ReceiptManagementBranch();
            entity = mapTpEntity(dto, entity);
            entityList.add(entity);
        }
        return entityList;
    }

    public static List<ReceiptManagementBranchDto> mapToDtoList(List<ReceiptManagementBranch> entities) {
        List<ReceiptManagementBranchDto> dtos = new ArrayList<>();
        for (ReceiptManagementBranch entity : entities) {
            ReceiptManagementBranchDto dto = new ReceiptManagementBranchDto();
            dto = mapToDto(entity, dto);
            dtos.add(dto);
        }
        return dtos;
    }


}
