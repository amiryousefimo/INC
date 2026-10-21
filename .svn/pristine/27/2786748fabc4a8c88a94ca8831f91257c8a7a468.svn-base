package ir.tamin.incomeBank.mapper.announcement.newspaper;


import ir.tamin.incomeBank.dto.announcement.newspaper.AnnouncementNewspaperDto;
import ir.tamin.incomeBank.model.announcement.newspaper.AnnouncementNewspaper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author A_Jamali
 */
public class AnnouncementNewspaperMapper {
    public static AnnouncementNewspaper mapTpEntity(AnnouncementNewspaperDto dto, AnnouncementNewspaper entity) {
        entity.setRowNum(dto.getRowNum());
        entity.setEdareCode(dto.getEdareCode());
        entity.setEdareName(dto.getEdareName());
        entity.setBrchCode(dto.getBrchCode());
        entity.setBrchName(dto.getBrchName());
        entity.setElamDesc(dto.getElamDesc());
        entity.setLetterPrice1(dto.getLetterPrice1());
        entity.setRwshId(dto.getRwshId());
        entity.setLetterName(dto.getLetterName());
        entity.setLetterDate(dto.getLetterDate());
        entity.setLetterLDate(dto.getLetterLDate());
        entity.setChqNo(dto.getChqNo());
        entity.setOrdNo(dto.getOrdNo());
        entity.setFunctionalDate(dto.getFunctionalDate());
        return entity;
    }

    public static AnnouncementNewspaperDto mapToDto(AnnouncementNewspaper entity, AnnouncementNewspaperDto dto) {
        dto.setRowNum(entity.getRowNum());
        dto.setEdareCode(entity.getEdareCode());
        dto.setEdareName(entity.getEdareName());
        dto.setBrchCode(entity.getBrchCode());
        dto.setBrchName(entity.getBrchName());
        dto.setElamDesc(entity.getElamDesc());
        dto.setLetterPrice1(entity.getLetterPrice1());
        dto.setRwshId(entity.getRwshId());
        dto.setLetterName(entity.getLetterName());
        dto.setLetterDate(entity.getLetterDate());
        dto.setLetterLDate(entity.getLetterLDate());
        dto.setChqNo(entity.getChqNo());
        dto.setOrdNo(entity.getOrdNo());
        dto.setFunctionalDate(entity.getFunctionalDate());
        return dto;
    }

    public static List<AnnouncementNewspaper> mapToListEntity(List<AnnouncementNewspaperDto> dtoList) {
        List<AnnouncementNewspaper> entityList = new ArrayList<>();
        for (AnnouncementNewspaperDto dto : dtoList) {
            AnnouncementNewspaper entity = new AnnouncementNewspaper();
            entity = mapTpEntity(dto, entity);
            entityList.add(entity);
        }
        return entityList;
    }

    public static List<AnnouncementNewspaperDto> mapToDtoList(List<AnnouncementNewspaper> entities) {
        List<AnnouncementNewspaperDto> dtos = new ArrayList<>();
        for (AnnouncementNewspaper entity : entities) {
            AnnouncementNewspaperDto dto = new AnnouncementNewspaperDto();
            dto = mapToDto(entity, dto);
            dtos.add(dto);
        }
        return dtos;
    }
}
