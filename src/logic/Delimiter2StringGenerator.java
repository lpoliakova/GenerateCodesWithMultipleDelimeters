package logic;

import java.util.ArrayList;
import java.util.List;

public class Delimiter2StringGenerator {

    public List<String> getListOfCodes(Integer amount) {
        List<String> listOfCodes = new ArrayList<String>();
        List<String> currentLengthCodes = new ArrayList<String>();
        currentLengthCodes.add("110");
        while (listOfCodes.size() < amount) {
            listOfCodes.addAll(validCodesList(currentLengthCodes));
            currentLengthCodes = getNextLengthCodes(currentLengthCodes);
        }
        return listOfCodes;
    }

    private List<String> getNextLengthCodes(List<String> previousLengthCodes) {
        List<String> nextLengthCodes = new ArrayList<String>();
        for (String code : previousLengthCodes) {
            nextLengthCodes.add("0" + code);
            nextLengthCodes.add("1" + code);
        }
        return nextLengthCodes;
    }

    private List<String> validCodesList(List<String> listOfCodes) {
        List<String> validCodesList = new ArrayList<String>();
        for (String code : listOfCodes) {
            if (validCode(code)) validCodesList.add(code);
        }
        return validCodesList;
    }

    private Boolean validCode(String code) {
        final String shortDelimiter = "110";
        final String longDelimiter = "0110";
        if (code.equals(shortDelimiter)) return true;
        if (!code.endsWith(longDelimiter)) return false;
        code = code.substring(0, code.length() - longDelimiter.length());
        return !(code.equals("11") || code.startsWith(shortDelimiter) || code.contains(longDelimiter) || code.endsWith("011"));
    }
}