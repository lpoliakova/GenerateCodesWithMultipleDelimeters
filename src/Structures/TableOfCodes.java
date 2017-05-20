package Structures;

import java.util.List;

public class TableOfCodes {
    private Integer lengthOfCodes;
    private Integer amountOfCodes;
    private List<String> codes;

    public TableOfCodes(Integer lengthOfCodes, Integer amountOfCodes, List<String> codes) {
        this.lengthOfCodes = lengthOfCodes;
        this.amountOfCodes = amountOfCodes;
        this.codes = codes;
    }

    public Integer getLengthOfCodes() {
        return  lengthOfCodes;
    }

    public Integer getAmountOfCodes() {
        return amountOfCodes;
    }

    public List<String> getCodes() {
        return codes;
    }
}
