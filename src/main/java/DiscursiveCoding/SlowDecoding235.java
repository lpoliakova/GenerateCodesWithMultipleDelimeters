package DiscursiveCoding;

import CodesBuilders.Delimiter2IntegerCodesBuilder;
import CodesLists.IntegerCode;
import Utils.WordsTranslation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oradchykova on 6/19/17.
 */
public class SlowDecoding235 {
    public static void decode (String inFileName, String outFileName, String dictionaryFileName) throws IOException {
        byte[] in = readCode(inFileName);
        List<IntegerCode> input = divideInCodewords(in);
        List<Integer> words = decodeWords(input);
        WordsTranslation.writeIntegerOutput(words, outFileName, dictionaryFileName);
    }

    private static byte[] readCode(String inFileName) throws IOException {
        return Files.readAllBytes(new File(inFileName).toPath());
    }

    private static List<IntegerCode> divideInCodewords(byte[] in) {
        List<IntegerCode> input = new ArrayList<>();
        Integer code = 0;
        Integer length = 0;
        for (byte byteCode : in) {
            for (int i = Byte.SIZE ; i > 0; i--) {
                if (length >= 3 && (checkForShortCode(code, length) || checkForDelimiter(code))) {
                    input.add(new IntegerCode(length, code));
                    code = 0;
                    length = 0;
                }
                code <<= 1;
                code |= (byteCode & (1 << i - 1)) >> (i - 1);
                length++;
            }
        }
        return input;
    }

    private static Boolean checkForShortCode(Integer code, Integer length) {
        return (length == 3 && code == 6) || (length == 4 && code == 14) || (length == 6 && code == 62);
    }

    private static Boolean checkForDelimiter(Integer code) {
        return ((code & 15) == 6) || ((code & 31) == 14) || ((code & 127) == 62);
    }

    private static List<Integer> decodeWords(List<IntegerCode> codes) {
        List<Integer> codeNumbers = new ArrayList<>();
        for (IntegerCode code : codes) {
            codeNumbers.add(decodeWord(code));
        }
        return codeNumbers;
    }

    private static Integer decodeWord(IntegerCode code) {
        if (code.getCode() == 6){
            code = new IntegerCode(code.getCodeSize() - 3, code.getCode() >> 3);
        } else if ((code.getCode() & 15) == 6){
            code = decodeRunningOnes(new IntegerCode(code.getCodeSize() - 4, code.getCode() >> 4));
        } else if ((code.getCode() & 31) == 14){
            code = decodeRunningOnes(new IntegerCode(code.getCodeSize() - 4, code.getCode() >> 4));
            code = new IntegerCode(code.getCodeSize() + 4, (code.getCode() << 4) | 14);
        } else if ((code.getCode() & 127) == 62){
            code = decodeRunningOnes(new IntegerCode(code.getCodeSize() - 6, code.getCode() >> 6));
            code = new IntegerCode(code.getCodeSize() + 6, (code.getCode() << 6) | 62);
        }
        return (1 << code.getCodeSize()) | code.getCode();
    }

    private static IntegerCode decodeRunningOnes(IntegerCode code){
        Integer runningOnes = 0;
        Integer newCode = 0;
        Integer length = 0;
        for (int i = code.getCodeSize() - 1; i >= 0; i--){
            if (((code.getCode() >> i) & 1) == 1){
                runningOnes++;
                if (runningOnes > 3) {
                    newCode <<= 1;
                    newCode |= 1;
                    length++;
                }
            } else {
                if (runningOnes == 1 || runningOnes == 4) {
                    newCode <<= 1;
                    newCode |= 1;
                    length++;
                }
                runningOnes = 0;
                newCode <<= 1;
                length++;
            }
        }
        if (runningOnes == 1 || runningOnes == 4) {
            newCode <<= 1;
            newCode |= 1;
            length++;
        }
        return new IntegerCode(length, newCode);
    }
}
