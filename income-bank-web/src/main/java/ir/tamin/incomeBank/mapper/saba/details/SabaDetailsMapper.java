package ir.tamin.incomeBank.mapper.saba.details;


import ir.tamin.incomeBank.model.saba.details.SabaDetails;
import ir.tamin.incomeBank.dto.saba.details.SabaDetailsDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author A_Jamali
 */
public class SabaDetailsMapper {
    public static SabaDetails mapTpEntity(SabaDetailsDto dto, SabaDetails entity) {

        //    private String rowNum;
        //    private String edareCode;
        //    private String edareName;
        //    private String brchCode;
        //    private String brchName;
        //    private String soratOrdPay;
        //    private String soratDate;
        //    private BigDecimal soratPrice1;
        //    private String soratPrice2;
        //    private String soratEfDate;
        //    private String bankCode;
        //    private String bankName;
        entity.setRowNum(dto.getRowNum());
        entity.setEdareCode(dto.getEdareCode());
        entity.setEdareName(dto.getEdareName());
        entity.setBrchName(dto.getBrchName());
        entity.setBrchCode(dto.getBrchCode());
        entity.setSoratDate(dto.getSoratDate());
        entity.setSoratOrdPay(dto.getSoratOrdPay());
        entity.setSoratPrice1(dto.getSoratPrice1());
        entity.setSoratPrice2(dto.getSoratPrice2());
        entity.setSoratEfDate(dto.getSoratEfDate());
        entity.setBankCode(dto.getBankCode());
        entity.setBankName(dto.getBankName());
        return entity;

    }

    public static SabaDetailsDto mapToDto(SabaDetails entity, SabaDetailsDto dto) {
        dto.setRowNum(entity.getRowNum());
        dto.setEdareCode(entity.getEdareCode());
        dto.setEdareName(entity.getEdareName());
        dto.setBrchCode(entity.getBrchCode());
        dto.setBrchName(entity.getBrchName());
        dto.setSoratDate(entity.getSoratDate());
        dto.setSoratOrdPay(entity.getSoratOrdPay());
        dto.setSoratPrice1(entity.getSoratPrice1());
        dto.setSoratPrice2(entity.getSoratPrice2());
        dto.setSoratEfDate(entity.getSoratEfDate());
        dto.setBankCode(entity.getBankCode());
        dto.setBankName(entity.getBankName());
        return dto;
    }

    public static List<SabaDetails> mapToListEntity(List<SabaDetailsDto> dtoList) {
        List<SabaDetails> entityList = new ArrayList<>();
        for (SabaDetailsDto dto : dtoList) {
            SabaDetails entity = new SabaDetails();
            entity = mapTpEntity(dto, entity);
            entityList.add(entity);
        }
        return entityList;
    }

    public static List<SabaDetailsDto> mapToDtoList(List<SabaDetails> entities) {
        List<SabaDetailsDto> dtos = new ArrayList<>();
        for (SabaDetails entity : entities) {
            SabaDetailsDto dto = new SabaDetailsDto();
            dto = mapToDto(entity, dto);
            dtos.add(dto);
        }
        return dtos;
    }


}
