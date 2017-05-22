import CodesBuilders.Delimiter2IntegerCodes;
import CodesBuilders.Delimiter2IntegerCodesBuilder;
import CodesBuilders.Delimiter2StringCodesBuilder;
import Tables.IntegerCode;

public class Application {

    public static void main(String[] args) {
        main1();
        main2();
    }

    private static void main1(){
        String code = "0011101011100110";
        //String code = "111111111111111111110110";
        Integer number = Delimiter2StringCodesBuilder.getIntegerForCode(code);
        String newCode = Delimiter2StringCodesBuilder.getCodeForInteger(number);
        System.out.println("Code: " + code + " pos - " + number + " new code - " + newCode);
    }

    private static void main2(){
        //IntegerCode code = new IntegerCode("0011101011100110");
        IntegerCode code = new IntegerCode("1111111111111111111111111110110");
        Integer number = Delimiter2IntegerCodesBuilder.getIntegerForCode(code);
        IntegerCode newCode = Delimiter2IntegerCodesBuilder.getCodeForInteger(number);
        System.out.println("Code: " + code + " pos - " + number + " new code - " + newCode);
    }
}