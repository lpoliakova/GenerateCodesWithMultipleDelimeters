package Structures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by oradchykova on 5/21/17.
 */
public class TableBeginnings {
    private final TableOfCodes[] codes;

    public TableBeginnings(){
        codes = new TableOfCodes[9];
        codes[0] = TableOfCodes.emptyTable();
        codes[1] = getTable(1);
        codes[2] = getTable(2);
        codes[3] = getTable(3);
        codes[4] = getTable(4);
        codes[5] = getTable(5);
        codes[6] = getTable(6);
        codes[7] = getTable(7);
        codes[8] = getTable(8);
    }

    public TableOfCodes[] getCodes() {
        return codes;
    }

    public TableOfCodes getCodes(Integer size) {
        return codes[size];
    }

    private TableOfCodes getTable(Integer N){
        Integer amount = countAmount(N);
        return new TableOfCodes(N, amount, growCodes(N, amount));
    }

    private Integer countAmount(Integer N){
        int counter = 0;
        for (int i = 1; i < N; i++){
            if (i != 3){
                counter += codes[N - i].getAmountOfCodes();
            }
        }
        if (N != 3) counter++;
        return ++counter;
    }

    private List<String> growCodes(Integer N, Integer amount){
        List<String> resultingCodes = new ArrayList<>(amount);
        String addition = "0";
        for (int i = 1; i < N; i++){
            List<String> codesSize = new ArrayList<>();
            if (i != 3){
                codesSize.addAll(codes[N - i].getCodes());
            }
            growCode(codesSize, addition);
            resultingCodes.addAll(codesSize);
            addition = "1" + addition;
        }
        if (N != 3) resultingCodes.add(addition);
        resultingCodes.add(addition.substring(0, addition.length() - 1) + "1");
        return resultingCodes;
    }

    private void growCode(List<String> codes, String addition){
        for (int i = 0; i < codes.size(); i++) {
            codes.set(i, addition + codes.get(i));
        }
    }
}
