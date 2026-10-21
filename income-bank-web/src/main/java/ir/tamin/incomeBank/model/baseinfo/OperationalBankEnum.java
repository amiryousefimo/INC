/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.tamin.incomeBank.model.baseinfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author s_maknooni
 */
public enum OperationalBankEnum {

    REFAH("بانک رفاه", "01"),
    MELLI("بانک ملی", "02"),
    MELLAT("بانک ملت", "03"),
    TEJARAT("بانک تجارت", "04"),
    SADERAT("بانک صادرات", "05"),
    KESHAVARZI("بانک کشاورزی", "06"),
    SEPAH("بانک سپه", "07"),
    POSTBANK("پست بانک", "19");
    //BONYADSHAHID("بنیاد شهید", (String) "00");

    private final String code;
    private final String name;
    private String codeName;

    OperationalBankEnum(String name, String code) {

        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getCodeName() {
        return code + " " + name;
    }

    public static String getNameOf(String code) {
        for (OperationalBankEnum type : OperationalBankEnum.values()) {
            if (type.getCode().equals(code)) {
                return type.getName();
            }
        }

        return "";
    }

//    public static Map[] getEnum() {
//        Map[] enumArray = new HashMap[OperationalBankEnum.values().length];
//        Map<String, String> map;
//        int i = 0;
//        for (OperationalBankEnum item : OperationalBankEnum.values()) {
//            map = new HashMap<String, String>();
//            map.put("code", item.getCode());
//            map.put("name", item.getName());
//            enumArray[i] = map;
//            i++;
//        }
//
//        return enumArray;
//    }
    public static List<Map> getEnum(String value) {
        List<Map> list = new ArrayList<>();
        Map<String, String> map;
        for (OperationalBankEnum item : OperationalBankEnum.values()) {
            map = new HashMap<String, String>();
            if (value.isEmpty()) {
                map.put("code", item.getCode());
                map.put("name", item.getName());
                map.put("codeName", item.getCodeName());
                list.add(map);
            } else {
                if (item.getCode().contains(value) || item.getName().contains(value)) {
                    map.put("code", item.getCode());
                    map.put("name", item.getName());
                    map.put("codeName", item.getCodeName());
                    list.add(map);
                }
            }
        }

        return list;
    }

}
