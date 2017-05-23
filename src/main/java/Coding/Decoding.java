package Coding;

import CodesBuilders.Delimiter2IntegerCodesBuilder;
import Tables.IntegerCode;
import Utils.WordsTranslation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oradchykova on 5/22/17.
 */
public class Decoding {
    public static void decode (String inFileName, String outFileName, String dictionaryFileName) throws IOException{
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
                if (length == 3 && checkForShortDelimiter(code)) {
                    input.add(new IntegerCode(3, 6));
                    code = 0;
                    length = 0;
                } else if (length >= 4 && checkForDelimiter(code)) {
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

    private static Boolean checkForShortDelimiter(Integer code) {
        return code == 6;
    }

    private static Boolean checkForDelimiter(Integer code) {
        return (code & 15) == 6;
    }

    private static List<Integer> decodeWords(List<IntegerCode> codes) {
        List<Integer> codeNumbers = new ArrayList<>();
        for (IntegerCode code : codes) {
            codeNumbers.add(decodeWord(code));
        }
        return codeNumbers;
    }

    private static Integer decodeWord(IntegerCode code) {
        return Delimiter2IntegerCodesBuilder.getIntegerForCode(code);
    }
}
