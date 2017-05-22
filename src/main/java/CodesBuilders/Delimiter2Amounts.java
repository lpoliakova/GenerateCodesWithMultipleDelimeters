package CodesBuilders;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oradchykova on 5/22/17.
 */
public class Delimiter2Amounts {
    private static final List<Integer> amountsForSize = new ArrayList<>();

    public static Integer getAmountForCurrentSize(Integer size){
        if (size > amountsForSize.size() - 1) {
            getAmountsToSize(size);
        }
        return amountsForSize.get(size);
    }

    private static void getAmountsToSize(Integer size){
        if (amountsForSize.isEmpty()){
            initAmountsForSize();
        }
        Integer startSize = amountsForSize.size();
        for (int i = startSize; i <= size; i++){
            amountsForSize.add(amountsForSize.get(i - 1) + getAmountForSize(i));
            //System.out.println("amount for len " + i + " = " + amountsForSize.get(i));
        }
    }

    private static Integer getAmountForSize(Integer size){
        if (size <= 3) return amountsForSize.get(size);
        return amountsForSize.get(size - 1) - getAmountForSize(size - 3);
    }

    private static void initAmountsForSize(){
        amountsForSize.add(0);
        amountsForSize.add(0);
        amountsForSize.add(0);
        amountsForSize.add(1);
    }
}
