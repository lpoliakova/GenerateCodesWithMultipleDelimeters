package Structures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by oradchykova on 5/21/17.
 */
public class TableShortCodes {
    private final TableOfCodes[] codes;

    public TableShortCodes(){
        codes = new TableOfCodes[]{
                initSize3(),
                initSize4(),
                initSize5(),
                initSize6(),
                initSize7(),
                initSize8()
        };
    }

    public TableOfCodes[] getCodes() {
        return codes;
    }

    public TableOfCodes getCodes(Integer size) {
        return codes[size - 3];
    }

    private TableOfCodes initSize3(){
        return new TableOfCodes(3, 1, new ArrayList<>(Arrays.asList(new String[]{"110"})));
    }

    private TableOfCodes initSize4(){
        return new TableOfCodes(4, 1, new ArrayList<>(Arrays.asList(new String[]{"0110"})));
    }

    private TableOfCodes initSize5(){
        return new TableOfCodes(5, 2, new ArrayList<>(Arrays.asList(
                new String[]{"00110", "10110"})));
    }

    private TableOfCodes initSize6(){
        return new TableOfCodes(6, 3, new ArrayList<>(Arrays.asList(
                new String[]{"000110", "010110", "100110"})));
    }

    private TableOfCodes initSize7(){
        return new TableOfCodes(7, 6, new ArrayList<>(Arrays.asList(
                new String[]{"0000110", "0010110", "0100110",
                        "1000110", "1010110", "1110110"})));
    }

    private TableOfCodes initSize8(){
        return new TableOfCodes(8, 11, new ArrayList<>(Arrays.asList(
                new String[]{"00000110", "00010110", "00100110",
                        "01000110", "01010110", "01110110",
                        "10000110", "10010110", "10100110",
                        "11100110", "11110110"})));
    }
}
