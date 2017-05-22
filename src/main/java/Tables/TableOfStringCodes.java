package Tables;

import java.util.ArrayList;
import java.util.List;

public class TableOfStringCodes {
    private final Integer lengthOfCodes;
    private final Integer amountOfCodes;
    private final List<String> codes;

    public TableOfStringCodes(Integer lengthOfCodes, Integer amountOfCodes, List<String> codes) {
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

    public static TableOfStringCodes emptyTable(Integer length){
        return new TableOfStringCodes(length, 0, new ArrayList<>());
    }

    public static void printTables(List<TableOfStringCodes> tables) {
        for (TableOfStringCodes t : tables) {
            printTable(t);
        }
    }

    public static void printTable(TableOfStringCodes table){
        System.out.println("Length : " + table.getLengthOfCodes());
        System.out.println("Amount : " + table.getAmountOfCodes());
        System.out.println("Codes : ");
        table.getCodes().forEach(System.out::println);
    }
}
