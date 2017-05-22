import CodesBuilders.Delimiter2StringCodesBuilder;
import Structures.TableOfCodes;
import logic.*;
import Structures.*;

import java.util.Arrays;
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

    private static void main3(){
        String code = "0011101011100110";
        //String code = "111111111111111111110110";
        Integer number = Delimiter2StringCodesBuilder.getIntegerForCode(code);
        String newCode = Delimiter2StringCodesBuilder.getCodeForInteger(number);
        System.out.println("Code: " + code + " pos - " + number + " new code - " + newCode);
    }
}