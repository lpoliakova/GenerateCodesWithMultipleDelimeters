package DiscursiveCoding;

import Utils.WordsTranslation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oradchykova on 6/19/17.
 */
public class FastDecoding235 {
    public static void decode (String inFileName, String outFileName, String dictionaryFileName) throws IOException {
        byte[] input = readCode(inFileName);
        List<Integer> words = decodeWords(input);
        WordsTranslation.writeIntegerOutput(words, outFileName, dictionaryFileName);
    }

    private static byte[] readCode(String inFileName) throws IOException {
        return Files.readAllBytes(new File(inFileName).toPath());
    }

    private static List<Integer> decodeWords(byte[] codes) {
        List<Integer> codeNumbers = new ArrayList<>();
        Integer number = 1;
        Integer rest = 0;
        for (byte code : codes) {
            Long longDecoding = ByteTable235.getLongDecoding(rest, code);
            Integer amount = (int)(longDecoding >> 56);
            Integer length = (int)((longDecoding >> 48) & 255);
            number <<= length;
            number |= (int)((longDecoding >> 32) & 65535);
            if (amount > 0) {
                codeNumbers.add(number);
                if (amount > 1) {
                    codeNumbers.add((int)((longDecoding >> 24) & 255));
                    if (amount > 2) {
                        codeNumbers.add((int)((longDecoding >> 16) & 255));
                        number = (int)((longDecoding >> 8) & 255);
                    } else {
                        number = (int)((longDecoding >> 16) & 255);
                    }
                } else {
                    number = (int)((longDecoding >> 24) & 255);
                }
            }
            rest = (int)(longDecoding & 255);
        }
        return codeNumbers;
    }
}
