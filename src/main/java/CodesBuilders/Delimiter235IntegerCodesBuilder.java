package CodesBuilders;

import CodesLists.IntegerCode;

/**
 * Created by oradchykova on 5/29/17.
 */
public class Delimiter235IntegerCodesBuilder {
    public static Integer getIntegerForCode(IntegerCode code){
        return Delimiter235Amounts.getAmountForCurrentSize(code.getCodeSize() - 1) + Delimiter235IntegerCodes.getPositionForCurrentSize(code);
    }

    public static IntegerCode getCodeForInteger(Integer number){
        for (int size = 3; ; size++){
            if (number < Delimiter235Amounts.getAmountForCurrentSize(size)){
                return Delimiter235IntegerCodes.getCodeForCurrentSize(size, number - Delimiter235Amounts.getAmountForCurrentSize(size - 1));
            }
        }
    }
}
