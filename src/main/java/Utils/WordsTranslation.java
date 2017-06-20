package Utils;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by oradchykova on 5/22/17.
 */
public class WordsTranslation {
    public static List<Integer> readIntegerInput(String inFileName, String dictionaryFileName) throws FileNotFoundException{
        List<String> text = readWords(inFileName);
        List<String> words = sortWords(text);
        writeDictionary(dictionaryFileName, words);
        return integerInput(text, words);
    }

    public static void writeIntegerOutput(List<Integer> numbers, String outFileName, String dictionaryFileName) throws FileNotFoundException{
        List<String> words = readWords(dictionaryFileName);
        List<String> text = stringOutput(numbers, words);
        writeFile(text, outFileName);
    }

    private static List<String> readWords(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader(fileName));
        List<String> input = new ArrayList<>();
        while (scanner.hasNext()) {
            input.add(scanner.next());
        }
        return input;
    }

    private static List<String> sortWords(List<String> input) throws FileNotFoundException {
        Map<String, Integer> wordCounter = new HashMap<>();
        for (String word : input) {
            Integer counter = wordCounter.getOrDefault(word, 0);
            wordCounter.put(word, ++counter);
        }
        return wordCounter.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private static List<Integer> integerInput(List<String> input, List<String> words) {
        List<Integer> integerInput = new ArrayList<>();
        for (String word : input) {
            integerInput.add(indexOfWord(word, words));
        }
        return integerInput;
    }

    private static Integer indexOfWord(String word, List<String> array) {
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).equals(word)) return i + 1;
        }
        return 0;
    }

    private static List<String> stringOutput(List<Integer> output, List<String> words){
        List<String> stringOutput = new ArrayList<>();
        for (Integer number : output) {
            stringOutput.add(words.get(number - 1));
        }
        return stringOutput;
    }

    private static void writeDictionary(String fileName, List<String> words) throws FileNotFoundException{
        try (PrintWriter wordsWriter = new PrintWriter(new FileOutputStream(new File(fileName)))) {
            for (String word : words) {
                wordsWriter.println(word);
            }
            wordsWriter.flush();
        }
    }

    private  static void writeFile(List<String> words, String outFile) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(new File(outFile)))) {
            for (String word : words) {
                writer.print(word + " ");
            }
            writer.flush();
        }
    }
}
