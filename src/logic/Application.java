package logic;

import Structures.TableOfCodes;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        main3();
    }

    private static void main1() {
        Delimiter2StringGenerator d2 = new Delimiter2StringGenerator();
        List<String> listOfCodes = d2.getListOfCodes(30);
        for (String code : listOfCodes) {
            System.out.println(code);
        }

    }

    private static void main2() {
        TableOfEndings t2 = new TableOfEndings();
        List<TableOfCodes> table = t2.buildTable(9);
        printTable(table);
    }

    private static void main3() {
        TableOfTransitional t2 = new TableOfTransitional();
        List<TableOfCodes> table = t2.buildTable(8);
        printTable(table);
    }

    private static void main4() {
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
    }

    private static void printTable(List<TableOfCodes> table) {
        for (TableOfCodes t : table) {
            System.out.println("Length : " + t.getLengthOfCodes());
            System.out.println("Amount : " + t.getAmountOfCodes());
            System.out.println("Codes : ");
            t.getCodes().forEach(System.out::println);
        }
    }
}