package DiscursiveCoding;

import CodesLists.IntegerCode;
import Utils.WordsTranslation;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oradchykova on 6/19/17.
 */
public class Encoding235 {
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
        Integer numberLength = (int)(Math.log(number) / Math.log(2)) + 1;
        number = deleteFirstOne(number, numberLength);
        numberLength--;
        if (number == 0) return new IntegerCode(numberLength + 3, 6);
        if ((number & 31) == 14) {
            IntegerCode startCode = encodeRunningOnes(number >> 5, numberLength - 5);
            return new IntegerCode(startCode.getCodeSize() + 5, (startCode.getCode() << 5) | 14);
        }
        if ((number & 127) == 62) {
            IntegerCode startCode = encodeRunningOnes(number >> 7, numberLength - 7);
            return new IntegerCode(startCode.getCodeSize() + 7, (startCode.getCode() << 7) | 62);
        }
        IntegerCode startCode = encodeRunningOnes(number, numberLength);
        return new IntegerCode(startCode.getCodeSize() + 4, (startCode.getCode() << 4) | 6);
    }

    private static IntegerCode encodeRunningOnes(Integer number, Integer numberLength){
        number <<= 1;
        numberLength++;
        Integer runningOnes = 0;
        Integer code = 0;
        Integer codeLength = 0;
        for (int i = numberLength - 1; i >= 0; i--) {
            if (((number >> i) & 1) == 1) {
                runningOnes++;
                codeLength++;
                code <<= 1;
                code |= 1;
            } else {
                if (runningOnes >= 3) {
                    codeLength += 3;
                    code <<= 3;
                    code |= 7;
                } else if (runningOnes == 2) {
                    codeLength += 2;
                    code <<= 2;
                    code |= 3;
                }
                runningOnes = 0;
                codeLength++;
                code <<= 1;
            }
        }
        return new IntegerCode(--codeLength, code >> 1);
    }

    private static Integer deleteFirstOne(Integer number, Integer numberLength) {
        return number & (~(1 << (numberLength - 1)));
    }

    private static void writeFile (byte[] output, String outFileName) throws IOException {
        try (FileOutputStream encodingWriter = new FileOutputStream(outFileName)) {
            encodingWriter.write(output);
            encodingWriter.flush();
        }
    }
}
