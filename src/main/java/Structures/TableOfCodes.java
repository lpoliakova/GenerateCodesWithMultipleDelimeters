package Structures;

import java.util.ArrayList;
import java.util.List;

public class TableOfCodes {
    private final Integer lengthOfCodes;
    private final Integer amountOfCodes;
    private final List<String> codes;

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

    public static TableOfCodes emptyTable(){
        return new TableOfCodes(0, 0, new ArrayList<>());
    }
}
