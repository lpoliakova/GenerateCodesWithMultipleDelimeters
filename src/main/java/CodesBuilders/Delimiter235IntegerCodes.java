package CodesBuilders;

import CodesLists.IntegerCode;
import CodesLists.ListOfIntegerCodes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by oradchykova on 5/29/17.
 */
public class Delimiter235IntegerCodes {
    private static final List<ListOfIntegerCodes> codesForSize = new ArrayList<>();

    public static IntegerCode getCodeForCurrentSize(Integer size, Integer index){
        if (size > codesForSize.size() - 1) {
            getCodesToSize(size);
        }
        return new IntegerCode(size, codesForSize.get(size).getCodes().get(index));
    }

    public static Integer getPositionForCurrentSize(IntegerCode code){
        if (code.getCodeSize() > codesForSize.size() - 1) {
            getCodesToSize(code.getCodeSize());
        }
        return getPositionInTable(codesForSize.get(code.getCodeSize()), code.getCode());
    }

    private static Integer getPositionInTable(ListOfIntegerCodes table, Integer code){
        return Collections.binarySearch(table.getCodes(), code);
    }

    private static void getCodesToSize(Integer size){
        if (codesForSize.isEmpty()){
            initCodesForSize();
        }
        Integer startSize = codesForSize.size();
        for (int i = startSize; i <= size; i++){
            codesForSize.add(getCodesForSize(i));
            //System.out.println("built " + i + " table");
            //ListOfIntegerCodes.printTable(codesForSize.get(i));
        }
    }

    private static ListOfIntegerCodes getCodesForSize(Integer size){
        Integer amount = Delimiter235Amounts.getAmountForCurrentSize(size) - Delimiter235Amounts.getAmountForCurrentSize(size - 1);
        return new ListOfIntegerCodes(size, amount, growCodes(size, amount));
    }

    private static List<Integer> growCodes(Integer size, Integer amount){
        List<Integer> grownCodes = new ArrayList<>(amount);
        grownCodes.addAll(codesForSize.get(size - 1).getCodes());
        Integer mask = 1 << (size - 1);
        Integer addition = mask;
        for (int i = 2; i <= size - 3; i++){
            List<Integer> codes = new ArrayList<>();
            if (i != 3 && i != 4 && i!= 6){
                codes.addAll(codesForSize.get(size - i).getCodes());
            }
            growCode(codes, addition);
            grownCodes.addAll(codes);
            mask >>= 1;
            addition |= mask;
        }
        return grownCodes;
    }

    private static void growCode(List<Integer> codes, Integer addition){
        for (int i = 0; i < codes.size(); i++) {
            codes.set(i, addition | codes.get(i));
        }
    }

    private static void initCodesForSize(){
        codesForSize.add(ListOfIntegerCodes.emptyTable(0));
        codesForSize.add(ListOfIntegerCodes.emptyTable(1));
        codesForSize.add(ListOfIntegerCodes.emptyTable(2));
        codesForSize.add(new ListOfIntegerCodes(3, 1, new ArrayList<>(Arrays.asList(new Integer[]{6}))));
        codesForSize.add(new ListOfIntegerCodes(4, 1, new ArrayList<>(Arrays.asList(new Integer[]{6, 14}))));
        codesForSize.add(new ListOfIntegerCodes(5, 1, new ArrayList<>(Arrays.asList(new Integer[]{6, 14, 22}))));
        codesForSize.add(new ListOfIntegerCodes(6, 1, new ArrayList<>(Arrays.asList(new Integer[]{6, 14, 22, 38, 46, 62}))));
    }
}
