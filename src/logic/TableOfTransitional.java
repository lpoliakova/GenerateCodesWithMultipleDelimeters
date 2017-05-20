package logic;

import Structures.TableOfCodes;

import java.util.ArrayList;
import java.util.List;

public class TableOfTransitional {

    private final String shortDelimiter = "110";
    private final String longDelimiter = "0110";

    public List<TableOfCodes> buildTable(Integer maxCodeLength) {
        List<TableOfCodes> table = new ArrayList<>();
        table.add(new TableOfCodes(0, 0, new ArrayList<>()));
        for (int i = 1; i <= maxCodeLength; i++) {
            table.add(buildTableOfCurrentLength(i));
        }
        return table;
    }

    private TableOfCodes buildTableOfCurrentLength(Integer length) {
        List<String> codes = new ArrayList<>();
        for (int i = 0; i < (int)Math.pow(2, length); i++) {
            checkAndAddCode(i, length, codes);
        }
        return new TableOfCodes(length, codes.size(), codes);
    }

    private void checkAndAddCode(Integer number, Integer length, List<String> codes){
        String code = convertIntegerToString(number, length);
        if (checkCode(code)) {
            codes.add(code);
        }
    }

    private String convertIntegerToString(Integer number, Integer length) {
        if (length == 0) return "";
        String code = Integer.toBinaryString(number);
        while (code.length() < length) {
            code = "0" + code;
        }
        return code;
    }

    private Boolean checkCode(String code) {
        return !(code.equals("1") || code.equals("11") || code.endsWith("01") || code.endsWith("011")
                || code.startsWith(shortDelimiter) || code.contains(longDelimiter));
    }
}
