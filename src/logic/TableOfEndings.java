package logic;

import Structures.TableOfCodes;

import java.util.ArrayList;
import java.util.List;

public class TableOfEndings {

    private final String shortDelimiter = "110";
    private final String longDelimiter = "0110";

    public List<TableOfCodes> buildTable(Integer maxCodeLength) {
        List<TableOfCodes> table = new ArrayList<>();
        for (int i = 0; i <= maxCodeLength; i++) {
            table.add(buildTableOfCurrentLength(i));
        }
        return table;
    }

    private TableOfCodes buildTableOfCurrentLength(Integer length) {
        if (length < 3) return new TableOfCodes(length, 0, new ArrayList<>());
        List<String> codes = new ArrayList<>();
        for (int i = 0; i < (int)Math.pow(2, length - 3); i++) {
            checkAndAddCode(i, length, codes);
        }
        return new TableOfCodes(length, codes.size(), codes);
    }

    private void checkAndAddCode(Integer number, Integer length, List<String> codes){
        String code = convertIntegerToString(number, length - 3) + shortDelimiter;
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
        if (code.equals(shortDelimiter)) return true;
        if (!code.endsWith(longDelimiter)) return false;
        code = code.substring(0, code.length() - longDelimiter.length());
        return !(code.equals("11") || code.startsWith(shortDelimiter) || code.contains(longDelimiter) || code.endsWith("011"));
    }
}
