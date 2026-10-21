package ir.tamin.incomeBank.mapper.smartlist.branch;

import ir.tamin.incomeBank.dto.smartlist.branch.SmartListBranchDto;
import ir.tamin.incomeBank.model.smartlist.branch.SmartListBranch;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
/**
 * @author a_jamali
 */
public class SmartListBranchMapper {

    public static SmartListBranch mapToEntity(SmartListBranchDto dto, SmartListBranch entity)  {
        entity.setRowNum(dto.getRowNum());
        entity.setEdareCode(dto.getEdareCode());
        entity.setEdareName(dto.getEdareName());
        entity.setBrchCode(dto.getBrchCode());
        entity.setBrchName(dto.getBrchName());
        entity.setOrderDate(dto.getOrderDate());
        entity.setOrderDate2(dto.getOrderDate2());
        entity.setSumAmount(dto.getSumAmount());
        entity.setSumAmountGov(dto.getSumAmountGov());
        entity.setSumISu(dto.getSumISu());
        entity.setSumIsuGov(dto.getSumIsuGov());
        entity.setCountOrdNumber(dto.getCountOrdNumber());
        entity.setHagheBimehGov(dto.getHagheBimehGov());
        entity.setHagheBimeh(dto.getHagheBimeh());
        entity.setJarimeh(dto.getJarimeh());
        entity.setBikari(dto.getBikari());
        return entity;
    }


    public static SmartListBranchDto mapToDto(SmartListBranch entity, SmartListBranchDto dto)  {
        dto.setRowNum(entity.getRowNum());
        dto.setEdareCode(entity.getEdareCode());
        dto.setEdareName(entity.getEdareName());
        dto.setBrchCode(entity.getBrchCode());
        dto.setBrchName(entity.getBrchName());
        dto.setOrderDate(entity.getOrderDate());
        dto.setOrderDate2(entity.getOrderDate2());
        dto.setSumAmount(entity.getSumAmount());
        dto.setSumAmountGov(entity.getSumAmountGov());
        dto.setSumISu(entity.getSumISu());
        dto.setSumIsuGov(entity.getSumIsuGov());
        dto.setCountOrdNumber(entity.getCountOrdNumber());
        dto.setHagheBimehGov(entity.getHagheBimehGov());
        dto.setHagheBimeh(entity.getHagheBimeh());
        dto.setJarimeh(entity.getJarimeh());
        dto.setBikari(entity.getBikari());
        return dto;

    }

    public static List<SmartListBranch> mapToEntityList(List<SmartListBranchDto> dtoList) throws InvocationTargetException, IllegalAccessException {
        List<SmartListBranch> entityList = new ArrayList<>();
        for (SmartListBranchDto dto : dtoList) {
            SmartListBranch entity = new SmartListBranch();
            entity = mapToEntity(dto, entity);
            entityList.add(entity);
        }
        return entityList;
    }

    public static List<SmartListBranchDto> mapToDtoList(List<SmartListBranch> entities) throws InvocationTargetException, IllegalAccessException {
        List<SmartListBranchDto> dtos = new ArrayList<>();
        for (SmartListBranch entity : entities) {
            SmartListBranchDto dto = new SmartListBranchDto();
            dto = mapToDto(entity, dto);
            dtos.add(dto);
        }
        return dtos;
    }

}
