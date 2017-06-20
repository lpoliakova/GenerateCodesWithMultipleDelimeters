import CodesBuilders.Delimiter235IntegerCodesBuilder;
import CodesBuilders.Delimiter2IntegerCodesBuilder;
import CodesBuilders.Delimiter2StringCodesBuilder;
import CodesLists.IntegerCode;
import Utils.CompareTexts;

import java.io.File;
import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException{
        main4();
        main6();
    }

    private static void main1(){
        String code = "0011101011100110";
        //String code = "111111111111111111110110";
        Integer number = Delimiter2StringCodesBuilder.getIntegerForCode(code);
        String newCode = Delimiter2StringCodesBuilder.getCodeForInteger(number);
        System.out.println("Code: " + code + " pos - " + number + " new code - " + newCode);
    }

    private static void main2(){
        //IntegerCode code = new IntegerCode("110");
        IntegerCode code = new IntegerCode("1111111111111111111111111110110");
        Integer number = Delimiter235IntegerCodesBuilder.getIntegerForCode(code);
        IntegerCode newCode = Delimiter235IntegerCodesBuilder.getCodeForInteger(number);
        System.out.println("Code: " + code + " pos - " + number + " new code - " + newCode + " " + (code.getCodeSize().equals(newCode.getCodeSize()) && code.getCode().equals(newCode.getCode())));
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
        long start = System.nanoTime();
        GradualCoding.Encoding235.encode(location + "exmpIn", location + "exmpCode", location + "exmpDict");
        long encoding = System.nanoTime();
        GradualCoding.Decoding235.decode(location + "exmpCode", location + "exmpOut", location + "exmpDict");
        long decoding = System.nanoTime();
        System.out.println("Gradual: enc " + ((double)(encoding - start) / 1000000000.0) + " dec: " + ((double)(decoding - encoding) / 1000000000.0));
        Integer compare = CompareTexts.compare(new File(location + "exmpIn"), new File(location + "exmpOut"));
        System.out.println(compare == 0 ? "equal" : "not equal word " + compare);
    }

    private static void main5(){
        long start = System.nanoTime();
        for (int i = 1; i <= 14838725; i++){
            IntegerCode code = Delimiter235IntegerCodesBuilder.getCodeForInteger(i);
            Integer number = Delimiter235IntegerCodesBuilder.getIntegerForCode(code);
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

    public static void main6() throws IOException{
        String location = "src/test/java/";
        long start = System.nanoTime();
        DiscursiveCoding.Encoding235.encode(location + "exmpIn", location + "exmpCode1", location + "exmpDict");
        long encoding = System.nanoTime();
        DiscursiveCoding.SlowDecoding235.decode(location + "exmpCode1", location + "exmpOut", location + "exmpDict");
        long decoding = System.nanoTime();
        System.out.println("Discursive: enc " + ((double)(encoding - start) / 1000000000.0) + " dec: " + ((double)(decoding - encoding) / 1000000000.0));
        Integer compare = CompareTexts.compare(new File(location + "exmpIn"), new File(location + "exmpOut"));
        System.out.println(compare == 0 ? "equal" : "not equal word " + compare);
    }

}