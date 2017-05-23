package Coding;

import CodesBuilders.Delimiter2IntegerCodesBuilder;
import Tables.IntegerCode;
import Utils.WordsTranslation;

import java.io.*;
import java.util.*;

/**
 * Created by oradchykova on 5/22/17.
 */
public class Encoding {
    public static void encode (String inFileName, String outFileName, String dictionaryFileName) throws IOException {
        List<Integer> integerInput = WordsTranslation.readIntegerInput(inFileName, dictionaryFileName);
        byte[] output = encodeWords(integerInput);
        writeFile(output, outFileName);
    }

    private static byte[] encodeWords(List<Integer> input) {
        List<Byte> out = new ArrayList<>();
        byte currentByte = 0;
        Integer freePlace = 8;
        for (Integer number : input) {
            IntegerCode code = encodeWord(number);
            Integer codeNumber = code.getCode();
            Integer codeLength = code.getCodeSize();
            while (freePlace <= codeLength) {
                currentByte |= codeNumber >> (codeLength - freePlace);
                out.add(currentByte);
                codeLength -= freePlace;
                currentByte = 0;
                freePlace = 8;
            }
            currentByte |= codeNumber << (freePlace - codeLength);
            freePlace -= codeLength;
        }
        out.add(currentByte);

        byte[] output = new byte[out.size()];
        for (int i = 0; i < out.size(); i++) {
            output[i] = out.get(i);
        }
        return output;
    }

    private static IntegerCode encodeWord(Integer number) {
        return Delimiter2IntegerCodesBuilder.getCodeForInteger(number);
    }

    private static void writeFile (byte[] output, String outFileName) throws IOException {
        try (FileOutputStream encodingWriter = new FileOutputStream(outFileName)) {
            encodingWriter.write(output);
            encodingWriter.flush();
        }
    }
}
