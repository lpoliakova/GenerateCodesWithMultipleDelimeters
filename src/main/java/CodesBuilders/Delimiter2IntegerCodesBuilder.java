package CodesBuilders;

import Tables.IntegerCode;

/**
 * Created by oradchykova on 5/22/17.
 */
public class Delimiter2IntegerCodesBuilder {
    public static Integer getIntegerForCode(IntegerCode code){
        return Delimiter2Amounts.getAmountForCurrentSize(code.getCodeSize() - 1) + Delimiter2IntegerCodes.getPositionForCurrentSize(code);
    }

    public static IntegerCode getCodeForInteger(Integer number){
        for (int size = 3; ; size++){
            if (number < Delimiter2Amounts.getAmountForCurrentSize(size)){
                return Delimiter2IntegerCodes.getCodeForCurrentSize(size, number - Delimiter2Amounts.getAmountForCurrentSize(size - 1));
            }
        }
    }
}
