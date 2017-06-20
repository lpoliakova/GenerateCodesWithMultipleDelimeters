package CodesBuilders;

import CodesLists.ListOfStringCodes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by oradchykova on 5/22/17.
 */
public class Delimiter2StringCodes {
    private static final List<ListOfStringCodes> codesForSize = new ArrayList<>();

    public static String getCodeForCurrentSize(Integer size, Integer index){
        if (size > codesForSize.size() - 1) {
            getCodesToSize(size);
        }
        return codesForSize.get(size).getCodes().get(index);
    }

    public static Integer getPositionForCurrentSize(String code){
        if (code.length() > codesForSize.size() - 1) {
            getCodesToSize(code.length());
        }
        return codesForSize.get(code.length()).getCodes().indexOf(code) + 1;
    }

    private static void getCodesToSize(Integer size){
        if (codesForSize.isEmpty()){
            initCodesForSize();
        }
        Integer startSize = codesForSize.size();
        for (int i = startSize; i <= size; i++){
            codesForSize.add(getCodesForSize(i));
            //System.out.println("built " + i + "table");
            //ListOfStringCodes.printTable(codesForSize.get(i));
        }
    }

    private static ListOfStringCodes getCodesForSize(Integer size){
        Integer amount = Delimiter2Amounts.getAmountForCurrentSize(size) - Delimiter2Amounts.getAmountForCurrentSize(size - 1);
        return new ListOfStringCodes(size, amount, growCodes(size, amount));
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

    private static void initCodesForSize(){
        codesForSize.add(ListOfStringCodes.emptyTable(0));
        codesForSize.add(ListOfStringCodes.emptyTable(1));
        codesForSize.add(ListOfStringCodes.emptyTable(2));
        codesForSize.add(new ListOfStringCodes(3, 1, new ArrayList<>(Arrays.asList(new String[]{"110"}))));
    }
}
