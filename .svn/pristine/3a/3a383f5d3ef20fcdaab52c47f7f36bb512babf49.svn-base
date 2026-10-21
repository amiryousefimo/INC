package ir.tamin.incomeBank.mapper.financialDoc;

import ir.tamin.incomeBank.dto.financialDoc.DisagreementViewDto;
import ir.tamin.incomeBank.dto.smartlist.edare.SmartListEdareDto;
import ir.tamin.incomeBank.model.financialDoc.DisagreementView;
import ir.tamin.incomeBank.model.smartlist.edare.SmartListEdare;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class DisagreementViewMapper {
    public static DisagreementView mapToEntity(DisagreementViewDto dto, DisagreementView entity) throws InvocationTargetException, IllegalAccessException {
        entity.setRowId(dto.getRowId());
        entity.setMoghDate(dto.getMoghDate());
        entity.setJaryan_cnt(dto.getJaryanCount());
        entity.setJaryan_amt(dto.getJaryanAmount());
        entity.setNashenas_cnt(dto.getNashenasCount());
        entity.setNashenas_amt(dto.getNashenasAmount());
        entity.setTekrari_cnt(dto.getTekrariCount());
        entity.setTekrari_amt(dto.getTekrariAmount());
        entity.setConfirmUserId(dto.getConfirmUserId());
        entity.setConfirmDate(dto.getConfirmDate());

        return entity;
    }

    public static DisagreementViewDto mapToDto(DisagreementView entity, DisagreementViewDto dto) throws InvocationTargetException, IllegalAccessException {
        dto.setRowId(entity.getRowId());
        dto.setMoghDate(entity.getMoghDate());
        dto.setJaryanCount(entity.getJaryan_cnt());
        dto.setJaryanAmount(entity.getJaryan_amt());
        dto.setNashenasCount(entity.getNashenas_cnt());
        dto.setNashenasAmount(entity.getNashenas_amt());
        dto.setTekrariCount(entity.getTekrari_cnt());
        dto.setTekrariAmount(entity.getTekrari_amt());
        dto.setConfirmUserId(entity.getConfirmUserId());
        dto.setConfirmDate(entity.getConfirmDate());

        return dto;

    }


    public static List<DisagreementViewDto> mapToDtoList(List<DisagreementView> entities) throws InvocationTargetException, IllegalAccessException {
        List<DisagreementViewDto> dtos = new ArrayList<>();
        for (DisagreementView entity : entities) {
            DisagreementViewDto dto = new DisagreementViewDto();
            dto = mapToDto(entity, dto);
            dtos.add(dto);
        }
        return dtos;
    }

}
