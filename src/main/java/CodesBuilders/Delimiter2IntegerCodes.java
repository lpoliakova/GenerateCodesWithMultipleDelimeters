package CodesBuilders;

import Tables.IntegerCode;
import Tables.TableOfIntegerCodes;
import Tables.TableOfStringCodes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by oradchykova on 5/22/17.
 */
public class Delimiter2IntegerCodes {
    private static final List<TableOfIntegerCodes> codesForSize = new ArrayList<>();

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
        return codesForSize.get(code.getCodeSize()).getCodes().indexOf(code.getCode()) + 1;
    }

    private static void getCodesToSize(Integer size){
        if (codesForSize.isEmpty()){
            initCodesForSize();
        }
        Integer startSize = codesForSize.size();
        for (int i = startSize; i <= size; i++){
            codesForSize.add(getCodesForSize(i));
            //System.out.println("built " + i + " table");
            //TableOfIntegerCodes.printTable(codesForSize.get(i));
        }
    }

    private static TableOfIntegerCodes getCodesForSize(Integer size){
        Integer amount = Delimiter2Amounts.getAmountForCurrentSize(size) - Delimiter2Amounts.getAmountForCurrentSize(size - 1);
        return new TableOfIntegerCodes(size, amount, growCodes(size, amount));
    }

    private static List<Integer> growCodes(Integer size, Integer amount){
        List<Integer> grownCodes = new ArrayList<>(amount);
        grownCodes.addAll(codesForSize.get(size - 1).getCodes());
        Integer mask = 1 << (size - 1);
        Integer addition = mask;
        for (int i = 2; i <= size - 3; i++){
            List<Integer> codes = new ArrayList<>();
            if (i != 3){
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
        codesForSize.add(TableOfIntegerCodes.emptyTable(0));
        codesForSize.add(TableOfIntegerCodes.emptyTable(1));
        codesForSize.add(TableOfIntegerCodes.emptyTable(2));
        codesForSize.add(new TableOfIntegerCodes(3, 1, new ArrayList<>(Arrays.asList(new Integer[]{6}))));
    }
}
