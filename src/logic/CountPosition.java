package logic;

import Structures.TableOfCodes;

import java.util.List;

public class CountPosition {

    public Integer count(String code) {
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
    }
}
