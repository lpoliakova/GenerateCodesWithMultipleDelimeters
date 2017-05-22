import Structures.TableOfCodes;
import logic.*;
import Structures.*;

import java.util.Arrays;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        main4();

    }

    private static void main1() {
        Delimiter2StringGenerator d2 = new Delimiter2StringGenerator();
        List<String> listOfCodes = d2.getListOfCodes(30);
        for (String code : listOfCodes) {
            System.out.println(code);
        }
    }

    /*private static void main2() {
        CountPosition counter = new CountPosition();
        TableOfEndings table = new TableOfEndings();

        //String code  = "000010110";
        String code = "00110";
        System.out.println(counter.count(code));
        List<TableOfCodes> tables  = table.buildTable(code.length());
        Integer pos = 0;
        for (int i = 0; i < code.length(); i++) {
            pos += tables.get(i).getAmountOfCodes();
        }
        System.out.println(pos + tables.get(code.length()).getCodes().indexOf(code) + 1);
    }*/

    private static void main3() {
        printTables(Arrays.asList(new TableEndings().getCodes()));
        printTable(new TableMiddle().codes);
    }

    private static void main4() {
        String codePart1 = "10011";
        String codePart2 = "100110";
        TableBeginnings beginnings = new TableBeginnings();
        TableEndings endings = new TableEndings();
        System.out.println("Code: " + codePart1 + " len - " + codePart1.length() + " pos - " + (beginnings.getCodes(codePart1.length()).getCodes().indexOf(codePart1) + 1));
        System.out.println("Code: " + codePart2 + " len - " + codePart2.length() + " pos - " + (endings.getCodes(codePart2.length()).getCodes().indexOf(codePart2) + 1));
        System.out.println("code: " + codePart1 + codePart2 + " len - " + (codePart1.length() + codePart2.length()) + " globPos - " + CountPosition.getGlobalPosition(codePart2));
    }

    private static void printTables(List<TableOfCodes> tables) {
        for (TableOfCodes t : tables) {
            printTable(t);
        }
    }

    private static void printTable(TableOfCodes table){
        System.out.println("Length : " + table.getLengthOfCodes());
        System.out.println("Amount : " + table.getAmountOfCodes());
        System.out.println("Codes : ");
        table.getCodes().forEach(System.out::println);
    }
}