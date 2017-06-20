package CodesLists;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oradchykova on 5/22/17.
 */
public class ListOfIntegerCodes {

    private final Integer lengthOfCodes;
    private final Integer amountOfCodes;
    private final List<Integer> codes;

    public ListOfIntegerCodes(Integer lengthOfCodes, Integer amountOfCodes, List<Integer> codes) {
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

    public List<Integer> getCodes() {
        return codes;
    }

    public static ListOfIntegerCodes emptyTable(Integer length){
        return new ListOfIntegerCodes(length, 0, new ArrayList<>());
    }

    public static void printTables(List<ListOfIntegerCodes> tables) {
        for (ListOfIntegerCodes t : tables) {
            printTable(t);
        }
    }

    public static void printTable(ListOfIntegerCodes table){
        System.out.println("Length : " + table.getLengthOfCodes());
        System.out.println("Amount : " + table.getAmountOfCodes());
        System.out.println("Codes : ");
        for (Integer code : table.getCodes()) {
            System.out.println(getExactSize(Integer.toBinaryString(code), table.getLengthOfCodes()));
        }
    }

    private static String getExactSize(String number, Integer size){
        while (number.length() < size) {
            number = "0" + number;
        }
        //if (number.length() > size){
        //    number = number.substring(number.length() - size, number.length());
        //}
        return number;
    }
}
