package ir.tamin.incomeBank.mapper.basicTables.debtTypes;

import ir.tamin.incomeBank.dto.basicTables.debtTypes.DebtTypesTableDto;
import ir.tamin.incomeBank.model.basicTables.debtTypes.DebtTypesTable;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class DebtTypesTableMapper {
    public static DebtTypesTableDto mapToDto(DebtTypesTable entity,DebtTypesTableDto dto){
        dto.setRowNum(entity.getRowNum());
        dto.setDebitCode(entity.getDebitCode());
        dto.setCodeKol(entity.getCodeKol());
        dto.setCodeMoin(entity.getCodeMoin());
        dto.setCodeTafsili(entity.getCodeTafsili());
        dto.setDebitDescription(entity.getDebitDescription());
        return dto;
    }
    public static List<DebtTypesTableDto> mapToDtoList(List<DebtTypesTable> entities)throws InvocationTargetException, IllegalAccessException {
        List<DebtTypesTableDto> dtos = new ArrayList<>();
        for(DebtTypesTable entity : entities){
            DebtTypesTableDto dto = new DebtTypesTableDto();
            dto = mapToDto(entity,dto);
            dtos.add(dto);
        }
        return dtos;
    }
}
