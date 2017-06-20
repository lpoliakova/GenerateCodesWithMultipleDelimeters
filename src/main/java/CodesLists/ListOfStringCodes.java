package CodesLists;

import java.util.ArrayList;
import java.util.List;

public class ListOfStringCodes {
    private final Integer lengthOfCodes;
    private final Integer amountOfCodes;
    private final List<String> codes;

    public ListOfStringCodes(Integer lengthOfCodes, Integer amountOfCodes, List<String> codes) {
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

    public static ListOfStringCodes emptyTable(Integer length){
        return new ListOfStringCodes(length, 0, new ArrayList<>());
    }

    public static void printTables(List<ListOfStringCodes> tables) {
        for (ListOfStringCodes t : tables) {
            printTable(t);
        }
    }

    public static void printTable(ListOfStringCodes table){
        System.out.println("Length : " + table.getLengthOfCodes());
        System.out.println("Amount : " + table.getAmountOfCodes());
        System.out.println("Codes : ");
        table.getCodes().forEach(System.out::println);
    }
}
