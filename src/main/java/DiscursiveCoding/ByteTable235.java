package DiscursiveCoding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by oradchykova on 6/16/17.
 */
public class ByteTable235 {
    private static Long[][] byteTable;

    public static Long getLongDecoding(Integer rest, byte code){
        if (byteTable == null) create();
        return byteTable[rest][(code + 256) % 256];
    }

    public static void create () {
        byteTable = createByteTable();
    }

    private static Long[][] createByteTable() {
        Long[][] byteTable = new Long[10][256];
        for (int column = 0; column < 256; column++) {
            String word = Integer.toBinaryString(column);
            if (word.length() > 8) word = word.substring(word.length() - 8, word.length());
            else while (word.length() != 8) word = "0" + word;
            for (int line = 0; line < 10; line++) {
                byteTable[line][column] = codeAnalise(getRestString(line), word);
            }
        }
        return byteTable;
    }

    private static Long codeAnalise(String rest, String thisByte){
        List<String> numbers = new ArrayList<>();
        String incomplete = findCompleteNumbers(numbers, rest, thisByte);
        analiseIncompleteNumber(numbers, incomplete, rest);
        return formLong(numbers);
    }

    private static String findCompleteNumbers(List<String> numbers, String rest, String thisByte){
        String code = rest + thisByte;
        String currentNumber = code.substring(0, 2);
        for (int i = 2; i < code.length(); i++){
            currentNumber += code.charAt(i);
            if (checkShortCodeShortDelimiter(currentNumber)){
                addNumber(numbers, "");
                currentNumber = "";
            } else if (checkShortCode(currentNumber)){
                addNumber(numbers, currentNumber);
                currentNumber = "";
            } else if (checkShortDelimiter(currentNumber)){
                if (checkPresenceOf1(numbers.isEmpty(), rest, currentNumber)){
                    addNumber(numbers, currentNumber.substring(0, currentNumber.length() - 3));
                } else {
                    addNumber(numbers, decodeRunningOnes(removeDelimiter(currentNumber)));
                }
                currentNumber = "";
            } else if (checkDelimiter(currentNumber)){
                addNumber(numbers, decodeRunningOnes(removeDelimiter(currentNumber)) + getDelimiter(currentNumber));
                currentNumber = "";
            }
        }
        return currentNumber;
    }

    private static void analiseIncompleteNumber(List<String> numbers, String incomplete, String prevRest){
        if (!incomplete.contains("0")){
            if (incomplete.length() <= 6){
                addNumber(numbers, "");
                addRest(numbers, incomplete);
            } else {
                addNumber(numbers, incomplete.substring(0, incomplete.length() - 6));
                addRest(numbers, "111111");
            }
        } else if (!incomplete.contains("1")){
            if (!numbers.isEmpty() || prevRest.isEmpty()) {
                addNumber(numbers, incomplete);
                addRest(numbers, "");
            } else {
                addNumber(numbers, incomplete.substring(0, incomplete.length() - 1));
                addRest(numbers, "0");
            }
        } else if (incomplete.endsWith("0")) {
            incomplete = decodeRunningOnes(incomplete);
            addNumber(numbers, incomplete.substring(0, incomplete.length() - 1));
            addRest(numbers, "0");
        } else {
            String rest = getDelimiter(incomplete);
            incomplete = decodeRunningOnes(removeDelimiter(incomplete));
            if (rest.length() >= 7) {
                addNumber(numbers, incomplete + rest.substring(0, rest.length() - 6));
                addRest(numbers, "111111");
            } else if (rest.length() >= 4) {
                addNumber(numbers, incomplete + "0");
                addRest(numbers, rest.substring(1, rest.length()));
            } else {
                if ((!numbers.isEmpty() || prevRest.isEmpty()) && (!incomplete.contains("1"))) {
                    addNumber(numbers, incomplete + "0");
                    addRest(numbers, rest.substring(1, rest.length()));
                } else {
                    addNumber(numbers, incomplete);
                    addRest(numbers, rest);
                }
            }
        }
    }

    private static Long formLong(List<String> words){
        List<Integer> numbers = new ArrayList<>(8);
        numbers.add(words.size() - 2);
        numbers.add(words.get(0).length());
        Integer word1 = parseInt(words.get(0));
        numbers.add((word1 & (255 << 8)) >> 8);
        numbers.add(word1 & 255);
        numbers.addAll(Arrays.asList(0, 0, 0));
        if (words.size() - 2 > 0){
            numbers.set(4, parseInt(words.get(1)));
            if (words.size() - 3 > 0){
                numbers.set(5, parseInt(words.get(2)));
                if (words.size() - 4 > 0){
                    numbers.set(6, parseInt(words.get(3)));
                }
            }
        }
        numbers.add(getRestInteger(words.get(words.size() - 1)));
        Long result = 0L;
        for (Integer number : numbers) {
            result <<= 8;
            result |= number;
        }
        return result;
    }

    private static void addNumber(List<String> numbers, String number){
        if (numbers.isEmpty()) numbers.add(number);
        else numbers.add("1" + number);
    }

    private static void addRest(List<String> numbers, String rest){
        numbers.add(rest);
    }

    private static Boolean checkShortCodeShortDelimiter(String code){
        return (code.equals("110"));
    }

    private static Boolean checkShortCode(String code){
        return (code.equals("1110") || code.equals("111110"));
    }

    private static Boolean checkShortDelimiter(String code){
        return code.endsWith("0110");
    }

    private static Boolean checkDelimiter(String code){
        return code.endsWith("01110") || code.endsWith("0111110");
    }

    private static Boolean checkPresenceOf1(Boolean first, String rest, String code) {
        return (!first || !rest.startsWith("0")) && (code.indexOf("10") == code.length() - 2);
    }

    private static String removeDelimiter(String code){
        return code.substring(0, code.lastIndexOf("01"));
    }

    private static String getDelimiter(String code){
        return code.substring(code.lastIndexOf("01"), code.length());
    }

    private static String decodeRunningOnes(String code){
        Integer runningOnes = 0;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < code.length(); i++){
            if (code.charAt(i) == '1') {
                runningOnes++;
                if (runningOnes > 3) result.append("1");
            } else {
                if (runningOnes == 1 || runningOnes == 4) {
                    result.append("1");
                }
                runningOnes = 0;
                result.append("0");
            }
        }
        if (runningOnes == 1 || runningOnes == 4) {
            result.append("1");
        }
        return result.toString();
    }

    private static Integer parseInt(String code){
        if (code.isEmpty()) return 0;
        return Integer.parseInt(code, 2);
    }

    private static String getRestString(int number) {
        if (number == 0) return "";
        if (number == 1) return "0";
        if (number == 2) return "1";
        if (number == 3) return "01";
        if (number == 4) return "11";
        if (number == 5) return "011";
        if (number == 6) return "111";
        if (number == 7) return "1111";
        if (number == 8) return "11111";
        return "111111";
    }

    private static Integer getRestInteger (String number) {
        if (number.equals("")) return 0;
        if (number.equals("0")) return 1;
        if (number.equals("1")) return 2;
        if (number.equals("01")) return 3;
        if (number.equals("11")) return 4;
        if (number.equals("011")) return 5;
        if (number.equals("111")) return 6;
        if (number.equals("1111")) return 7;
        if (number.equals("11111")) return 8;
        return 9;
    }
}
