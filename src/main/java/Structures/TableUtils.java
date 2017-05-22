package Structures;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oradchykova on 5/21/17.
 */
public class TableUtils {
    public static TableOfCodes getTableBeginnings(Integer N, TableOfCodes[] codes){
        Integer amount = TableUtils.countAmountBeginnings(N, codes);
        return new TableOfCodes(N, amount, TableUtils.growCodesBeginnings(N, amount, codes));
    }

    private static Integer countAmountBeginnings(Integer N, TableOfCodes[] codes){
        int counter = 0;
        for (int i = 1; i < N; i++){
            if (i != 3){
                counter += codes[N - i].getAmountOfCodes();
            }
        }
        if (N != 3) counter++;
        return ++counter;
    }

    private static List<String> growCodesBeginnings(Integer N, Integer amount, TableOfCodes[] codes){
        List<String> resultingCodes = new ArrayList<>(amount);
        String addition = "0";
        for (int i = 1; i < N; i++){
            List<String> codesSize = new ArrayList<>();
            if (i != 3){
                codesSize.addAll(codes[N - i].getCodes());
            }
            growCode(codesSize, addition);
            resultingCodes.addAll(codesSize);
            addition = "1" + addition;
        }
        if (N != 3) resultingCodes.add(addition);
        resultingCodes.add(addition.substring(0, addition.length() - 1) + "1");
        return resultingCodes;
    }

    private static void growCode(List<String> codes, String addition){
        for (int i = 0; i < codes.size(); i++) {
            codes.set(i, addition + codes.get(i));
        }
    }
}
