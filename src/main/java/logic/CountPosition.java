package logic;

import Structures.TableOfCodes;
import Structures.TableUtils;

public class CountPosition {

    public static Integer getGlobalPosition(String code){
        TableOfCodes allCodes = getTableOfSize(code.length());
        return allCodes.getCodes().indexOf(code) + 1;
    }

    private static TableOfCodes getTableOfSize(Integer size){
        TableOfCodes[] codes = new TableOfCodes[size + 1];
        codes[0] = TableOfCodes.emptyTable(0);
        for (int i = 1; i <= size; i++){
            codes[i] = TableUtils.getTableBeginnings(i, codes);
            if (i < 7) {
                System.out.println("Length : " + codes[i].getLengthOfCodes());
                System.out.println("Amount : " + codes[i].getAmountOfCodes());
                System.out.println("Codes : ");
                codes[i].getCodes().forEach(System.out::println);
            }
        }
        return codes[size];
    }

    /*public Integer count(String code) {
        List<TableOfCodes> transTable = (new TableOfTransitional()).buildTable(10);
        List<TableOfCodes> endTable = (new TableOfEndings()).buildTable(10);

        Integer position = 0;

        String curCode = "";
        String curUncoded = "";
        while(code.length() > Byte.SIZE) {
            curCode = curUncoded + code.substring(0, Byte.SIZE);
            code = code.substring(Byte.SIZE, code.length());
            curUncoded = "";

            while (!transTable.get(curCode.length()).getCodes().contains(curCode)) {
                curUncoded = curCode.charAt(curCode.length() - 1) + curUncoded;
                curCode = curCode.substring(0, curCode.length() - 1);
            }

            position = (position + transTable.get(curCode.length()).getCodes().indexOf(curCode)) * transTable.get(curCode.length()).getAmountOfCodes();
            //position = position * transTable.get(curCode.length()).getAmountOfCodes() + transTable.get(curCode.length()).getCodes().indexOf(curCode) + 1;
        }


        position += endTable.get(code.length()).getCodes().indexOf(code) + 1;
        //position = position * endTable.get(code.length()).getAmountOfCodes() + endTable.get(code.length()).getCodes().indexOf(code) + 1;
        return position;
    }*/
}
