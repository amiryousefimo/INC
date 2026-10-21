package ir.tamin.incomeBank.mapper.smartlist.edare;

import ir.tamin.incomeBank.dto.smartlist.edare.SmartListEdareDto;
import ir.tamin.incomeBank.model.smartlist.edare.SmartListEdare;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
/**
 * @author a_jamali
 */
public class SmartListEdareMapper {

    public static SmartListEdare mapToEntity(SmartListEdareDto dto, SmartListEdare entity) throws InvocationTargetException, IllegalAccessException {
        entity.setRowNum(dto.getRowNum());
        entity.setEdareCode(dto.getEdareCode());
        entity.setEdareName(dto.getEdareName());
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


    public static SmartListEdareDto mapToDto(SmartListEdare entity, SmartListEdareDto dto) throws InvocationTargetException, IllegalAccessException {
        dto.setRowNum(entity.getRowNum());
        dto.setEdareCode(entity.getEdareCode());
        dto.setEdareName(entity.getEdareName());
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

    public static List<SmartListEdare> mapToEntityList(List<SmartListEdareDto> dtoList) throws InvocationTargetException, IllegalAccessException {
        List<SmartListEdare> entityList = new ArrayList<>();
        for (SmartListEdareDto dto : dtoList) {
            SmartListEdare entity = new SmartListEdare();
            entity = mapToEntity(dto, entity);
            entityList.add(entity);
        }
        return entityList;
    }

    public static List<SmartListEdareDto> mapToDtoList(List<SmartListEdare> entities) throws InvocationTargetException, IllegalAccessException {
        List<SmartListEdareDto> dtos = new ArrayList<>();
        for (SmartListEdare entity : entities) {
            SmartListEdareDto dto = new SmartListEdareDto();
            dto = mapToDto(entity, dto);
            dtos.add(dto);
        }
        return dtos;
    }

}
