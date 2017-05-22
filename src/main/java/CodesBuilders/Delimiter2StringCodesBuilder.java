package CodesBuilders;

import Structures.TableOfCodes;
import Structures.TableUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olena on 22.05.17.
 */
public class Delimiter2StringCodesBuilder {
    private static List<Integer> amountsForSize = new ArrayList<>();
    private static List<TableOfCodes> codesForSize = new ArrayList<>();

    public static Integer getIntegerForCode(String code){
        return getPositionFromCurrentSizeCodes(code) + getAmountBeforeCurrentSize(code.length());
    }

    public static String getCodeForInteger(Integer number){

    }

    private static Integer getAmountBeforeCurrentSize(Integer size){
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
        Integer startSize = codesForSize.size();
        for (int i = startSize; i <= size; i++){
            amountsForSize.add(amountsForSize.get(i) + countAmountForSize(i));
        }
    }

    private static Integer countAmountForSize(Integer size){
        int counter = 0;
        for (int i = 1; i < amountsForSize.size(); i++){
            if (i != 3){
                counter += amountsForSize.get(size - i);
            }
        }
        if (size != 3) counter++;
        return ++counter;
    }

    private static void getCodesToSize(Integer size){
        Integer startSize = codesForSize.size() - 1;
        for (int i = startSize; i <= size; i++){
            codesForSize.add(getCodesForSize(i));
        }
    }

    private static TableOfCodes getCodesForSize(Integer size){
        Integer amount = amountsForSize.get(size) - amountsForSize.get(size - 1);
        return new TableOfCodes(size, amount, growCodes(size, amount));
    }

    private static List<String> growCodes(Integer size, Integer amount) {
        List<String> grownCodes = new ArrayList<>(amount);
        String addition = "0";
        for (int i = 1; i < codesForSize.size(); i++){
            List<String> codes = new ArrayList<>();
            if (i != 3){
                codes.addAll(codesForSize.get(size - i).getCodes());
            }
            growCode(codes, addition);
            grownCodes.addAll(codes);
            addition = "1" + addition;
        }
        if (size != 3) grownCodes.add(addition);
        grownCodes.add(addition.substring(0, addition.length() - 1) + "1");
        return grownCodes;
    }

    private static void growCode(List<String> codes, String addition){
        for (int i = 0; i < codes.size(); i++) {
            codes.set(i, addition + codes.get(i));
        }
    }
}
