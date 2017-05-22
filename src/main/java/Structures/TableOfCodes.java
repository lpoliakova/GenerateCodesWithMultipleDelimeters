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

    public static TableOfCodes emptyTable(Integer length){
        return new TableOfCodes(length, 0, new ArrayList<>());
    }

    public static void printTables(List<TableOfCodes> tables) {
        for (TableOfCodes t : tables) {
            printTable(t);
        }
    }

    public static void printTable(TableOfCodes table){
        System.out.println("Length : " + table.getLengthOfCodes());
        System.out.println("Amount : " + table.getAmountOfCodes());
        System.out.println("Codes : ");
        table.getCodes().forEach(System.out::println);
    }
}
