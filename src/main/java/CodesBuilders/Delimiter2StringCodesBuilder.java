package CodesBuilders;

import Structures.TableOfCodes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by olena on 22.05.17.
 */
public class Delimiter2StringCodesBuilder {
    private static List<Integer> amountsForSize = new ArrayList<>();
    private static List<TableOfCodes> codesForSize = new ArrayList<>();

    public static Integer getIntegerForCode(String code){
        return getAmountForCurrentSize(code.length() - 1) + getPositionFromCurrentSizeCodes(code);
    }

    /*public static String getCodeForInteger(Integer number){

    }*/

    private static Integer getAmountForCurrentSize(Integer size){
        if (size > amountsForSize.size() - 1) {
            getAmountsToSize(size);
        }
        return amountsForSize.get(size);
    }

    private static Integer getPositionFromCurrentSizeCodes(String code){
        if (code.length() > codesForSize.size() - 1) {
            getCodesToSize(code.length());
        }
        return codesForSize.get(code.length()).getCodes().indexOf(code) + 1;
    }

    private static void getAmountsToSize(Integer size){
        if (amountsForSize.isEmpty()){
            initAmountsForSize();
        }
        Integer startSize = amountsForSize.size();
        for (int i = startSize; i <= size; i++){
            amountsForSize.add(amountsForSize.get(i - 1) + countAmountForSize(i));
            //System.out.println("amount for len " + i + " = " + amountsForSize.get(i));
        }
    }

    private static Integer countAmountForSize(Integer size){
        if (size <= 3) return amountsForSize.get(size);
        return amountsForSize.get(size - 1) - countAmountForSize(size - 3);
    }

    private static void getCodesToSize(Integer size){
        if (codesForSize.isEmpty()){
            initCodesForSize();
        }
        Integer startSize = codesForSize.size();
        for (int i = startSize; i <= size; i++){
            codesForSize.add(getCodesForSize(i));
            //System.out.println("built " + i + "table");
            //TableOfCodes.printTable(codesForSize.get(i));
        }
    }

    private static TableOfCodes getCodesForSize(Integer size){
        Integer amount = getAmountForCurrentSize(size) - getAmountForCurrentSize(size - 1);
        return new TableOfCodes(size, amount, growCodes(size, amount));
    }

    private static List<String> growCodes(Integer size, Integer amount) {
        List<String> grownCodes = new ArrayList<>(amount);
        String addition = "0";
        for (int i = 1; i <= size - 3; i++){
            List<String> codes = new ArrayList<>();
            if (i != 3){
                codes.addAll(codesForSize.get(size - i).getCodes());
            }
            growCode(codes, addition);
            grownCodes.addAll(codes);
            addition = "1" + addition;
        }
        return grownCodes;
    }

    private static void growCode(List<String> codes, String addition){
        for (int i = 0; i < codes.size(); i++) {
            codes.set(i, addition + codes.get(i));
        }
    }

    private static void initAmountsForSize(){
        amountsForSize.add(0);
        amountsForSize.add(0);
        amountsForSize.add(0);
        amountsForSize.add(1);
    }

    private static void initCodesForSize(){
        codesForSize.add(TableOfCodes.emptyTable(0));
        codesForSize.add(TableOfCodes.emptyTable(1));
        codesForSize.add(TableOfCodes.emptyTable(2));
        codesForSize.add(new TableOfCodes(3, 1, new ArrayList<>(Arrays.asList(new String[]{"110"}))));
    }
}
