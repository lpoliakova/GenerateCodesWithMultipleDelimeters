package CodesBuilders;

/**
 * Created by olena on 22.05.17.
 */
public class Delimiter2StringCodesBuilder {
    public static Integer getIntegerForCode(String code){
        return Delimiter2Amounts.getAmountForCurrentSize(code.length() - 1) + Delimiter2StringCodes.getPositionForCurrentSize(code);
    }

    public static String getCodeForInteger(Integer number){
        number--;
        for (int size = 3; ; size++){
            if (number < Delimiter2Amounts.getAmountForCurrentSize(size)){
                return Delimiter2StringCodes.getCodeForCurrentSize(size, number - Delimiter2Amounts.getAmountForCurrentSize(size - 1));
            }
        }
    }
}
