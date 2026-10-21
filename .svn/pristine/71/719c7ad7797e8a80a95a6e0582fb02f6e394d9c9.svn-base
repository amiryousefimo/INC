package ir.tamin.incomeBank.mapper.paymentWorkers.branch;

import ir.tamin.incomeBank.dto.paymentWorkers.branch.PaymentWorkersBranchDto;
import ir.tamin.incomeBank.model.paymentWorkers.branch.PaymentWorkersBranch;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class PaymentWorkersBranchMapper {
    public static PaymentWorkersBranch mapToEntity(PaymentWorkersBranchDto dto, PaymentWorkersBranch entity)  {
        entity.setRowNum(dto.getRowNum());
        entity.setBrchCode(dto.getBrchCode());
        entity.setBrchName(dto.getBrchName());
        entity.setEdareCode(dto.getEdareCode());
        entity.setEdareName(dto.getEdareName());
        entity.setOrdDate(dto.getOrderDate());
        entity.setOrderDate2(dto.getOrderDate2());
        entity.setSumAmount(dto.getSumAmount());
        entity.setCountOrderNumber(dto.getCountOrdNumber());
        entity.setBimeh7sakht(dto.getBimeh7sakht());
        entity.setBimeh20sakht(dto.getBimeh20sakht());
        entity.setJarimehSakht(dto.getJarimehSakht());
        entity.setSumAmtGov(dto.getSumAmtGov());
        return entity;
    }


    public static PaymentWorkersBranchDto mapToDto(PaymentWorkersBranch entity, PaymentWorkersBranchDto dto)  {
        dto.setRowNum(entity.getRowNum());
        dto.setEdareCode(entity.getEdareCode());
        dto.setEdareName(entity.getEdareName());
        dto.setBrchCode(entity.getBrchCode());
        dto.setBrchName(entity.getBrchName());
        dto.setOrderDate(entity.getOrdDate());
        dto.setOrderDate2(entity.getOrderDate2());
        dto.setSumAmount(entity.getSumAmount());
        dto.setCountOrdNumber(entity.getCountOrderNumber());
        dto.setBimeh7sakht(entity.getBimeh7sakht());
        dto.setBimeh20sakht(entity.getBimeh20sakht());
        dto.setJarimehSakht(entity.getJarimehSakht());
        dto.setSumAmtGov(entity.getSumAmtGov());

        return dto;

    }

    public static List<PaymentWorkersBranch> mapToEntityList(List<PaymentWorkersBranchDto> dtoList) throws InvocationTargetException, IllegalAccessException {
        List<PaymentWorkersBranch> entityList = new ArrayList<>();
        for (PaymentWorkersBranchDto dto : dtoList) {
            PaymentWorkersBranch entity = new PaymentWorkersBranch();
            entity = mapToEntity(dto, entity);
            entityList.add(entity);
        }
        return entityList;
    }

    public static List<PaymentWorkersBranchDto> mapToDtoList(List<PaymentWorkersBranch> entities) throws InvocationTargetException, IllegalAccessException {
        List<PaymentWorkersBranchDto> dtos = new ArrayList<>();
        for (PaymentWorkersBranch entity : entities) {
            PaymentWorkersBranchDto dto = new PaymentWorkersBranchDto();
            dto = mapToDto(entity, dto);
            dtos.add(dto);
        }
        return dtos;
    }
}
