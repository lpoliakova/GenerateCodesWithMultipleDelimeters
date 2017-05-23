import CodesBuilders.Delimiter2IntegerCodesBuilder;
import CodesBuilders.Delimiter2StringCodesBuilder;
import Coding.Decoding;
import Coding.Encoding;
import Tables.IntegerCode;
import Utils.CompareTexts;

import java.io.File;
import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException{
        main4();
    }

    private static void main1(){
        String code = "0011101011100110";
        //String code = "111111111111111111110110";
        Integer number = Delimiter2StringCodesBuilder.getIntegerForCode(code);
        String newCode = Delimiter2StringCodesBuilder.getCodeForInteger(number);
        System.out.println("Code: " + code + " pos - " + number + " new code - " + newCode);
    }

    private static void main2(){
        IntegerCode code = new IntegerCode("110");
        //IntegerCode code = new IntegerCode("1111111111111111111111111110110");
        Integer number = Delimiter2IntegerCodesBuilder.getIntegerForCode(code);
        IntegerCode newCode = Delimiter2IntegerCodesBuilder.getCodeForInteger(number);
        System.out.println("Code: " + code + " pos - " + number + " new code - " + newCode);
    }

    private static void main3(){
        long start = System.nanoTime();
        for (int i = 1; i <= 41451340; i++){
            IntegerCode code = Delimiter2IntegerCodesBuilder.getCodeForInteger(i);
            Integer number = Delimiter2IntegerCodesBuilder.getIntegerForCode(code);
            if (i % 10000 == 0) {
                long end = System.nanoTime();
                System.out.println("number " + i + ", time " + ((double)(end - start) / 1000000000.0) + "s");
            }
            if (i % 100000 == 0){
                long end = System.nanoTime();
                System.out.println("big number " + i + ", time " + ((double)(end - start) / 1000000000.0) + "s");
            }
            if (i != number) {
                System.out.println("number: " + i + " code: " + code);
                break;
            }
        }
    }

    public static void main4() throws IOException{
        String location = "src/test/java/";
        Encoding.encode(location + "exmpIn", location + "exmpCode", location + "exmpDict");
        Decoding.decode(location + "exmpCode", location + "exmpOut", location + "exmpDict");
        Integer compare = CompareTexts.compare(new File(location + "exmpIn"), new File(location + "exmpOut"));
        System.out.println(compare == 0 ? "equal" : "not equal word " + compare);
    }
}