package ir.tamin.incomeBank.mapper.announcement.edare;

import ir.tamin.incomeBank.dto.announcement.edare.AnnouncementEdareKolDto;
import ir.tamin.incomeBank.model.announcement.setad.AnnouncementEdareKol;

import java.util.ArrayList;
import java.util.List;
/**
 * @author A_Jamali
 */
public class AnnouncementEdareKolMapper {

    public static AnnouncementEdareKol mapTpEntity(AnnouncementEdareKolDto dto, AnnouncementEdareKol entity) {
        entity.setRowNum(dto.getRowNum());
        entity.setEdareCode(dto.getEdareCode());
        entity.setEdareName(dto.getEdareName());
        entity.setLetterDate(dto.getLetterDate());
        entity.setEmployees(dto.getEmployees());
        entity.setEmployeesCount(dto.getEmployeesCount());
        entity.setPeyman(dto.getPeyman());
        entity.setPeymanCount(dto.getPeymanCount());
        entity.setSumCount(dto.getSumCount());
        entity.setSumAmount(dto.getSumAmount());
        return entity;

    }

    public static AnnouncementEdareKolDto mapToDto(AnnouncementEdareKol entity, AnnouncementEdareKolDto dto) {
        dto.setRowNum(entity.getRowNum());
        dto.setEdareCode(entity.getEdareCode());
        dto.setEdareName(entity.getEdareName());
        dto.setLetterDate(entity.getLetterDate());
        dto.setEmployees(entity.getEmployees());
        dto.setEmployeesCount(entity.getEmployeesCount());
        dto.setPeyman(entity.getPeyman());
        dto.setPeymanCount(entity.getPeymanCount());
        dto.setSumCount(entity.getSumCount());
        dto.setSumAmount(entity.getSumAmount());
        return dto;
    }

    public static List<AnnouncementEdareKol> mapToListEntity(List<AnnouncementEdareKolDto> dtoList) {
        List<AnnouncementEdareKol> entityList = new ArrayList<>();
        for (AnnouncementEdareKolDto dto : dtoList) {
            AnnouncementEdareKol entity = new AnnouncementEdareKol();
            entity = mapTpEntity(dto, entity);
            entityList.add(entity);
        }
        return entityList;
    }

    public static List<AnnouncementEdareKolDto> mapToDtoList(List<AnnouncementEdareKol> entities) {
        List<AnnouncementEdareKolDto> dtos = new ArrayList<>();
        for (AnnouncementEdareKol entity : entities) {
            AnnouncementEdareKolDto dto = new AnnouncementEdareKolDto();
            dto = mapToDto(entity, dto);
            dtos.add(dto);
        }
        return dtos;
    }

}
