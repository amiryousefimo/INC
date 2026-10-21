package ir.tamin.incomeBank.mapper.paymentWorkers.edare;

import ir.tamin.incomeBank.dto.paymentWorkers.edare.PaymentWorkersEdareDto;
import ir.tamin.incomeBank.model.paymentWorkers.edare.PaymentWorkersEdare;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class PaymentWorkersEdareMapper {
    public static PaymentWorkersEdare mapToEntity(PaymentWorkersEdareDto dto, PaymentWorkersEdare entity)  {
        entity.setRowNum(dto.getRowNum());
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


    public static PaymentWorkersEdareDto mapToDto(PaymentWorkersEdare entity, PaymentWorkersEdareDto dto)  {
        dto.setRowNum(entity.getRowNum());
        dto.setEdareCode(entity.getEdareCode());
        dto.setEdareName(entity.getEdareName());
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

/*    public static List<PaymentWorkersEdare> mapToEntityList(List<PaymentWorkersEdareDto> dtoList) throws InvocationTargetException, IllegalAccessException {
        List<PaymentWorkersEdare> entityList = new ArrayList<>();
        for (PaymentWorkersEdareDto dto : dtoList) {
            PaymentWorkersEdare entity = new PaymentWorkersEdare();
            entity = mapToEntity(dto, entity);
            entityList.add(entity);
        }
        return entityList;
    }*/

    public static List<PaymentWorkersEdareDto> mapToDtoList(List<PaymentWorkersEdare> entities) throws InvocationTargetException, IllegalAccessException {
        List<PaymentWorkersEdareDto> dtos = new ArrayList<>();
        for (PaymentWorkersEdare entity : entities) {
            PaymentWorkersEdareDto dto = new PaymentWorkersEdareDto();
            dto = mapToDto(entity, dto);
            dtos.add(dto);
        }
        return dtos;
    }
}
