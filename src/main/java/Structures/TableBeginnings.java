package Structures;

/**
 * Created by oradchykova on 5/21/17.
 */
public class TableBeginnings {
    private final TableOfCodes[] codes;

    public TableBeginnings(){
        codes = new TableOfCodes[9];
        codes[0] = TableOfCodes.emptyTable(0);
        codes[1] = TableUtils.getTableBeginnings(1, codes);
        codes[2] = TableUtils.getTableBeginnings(2, codes);
        codes[3] = TableUtils.getTableBeginnings(3, codes);
        codes[4] = TableUtils.getTableBeginnings(4, codes);
        codes[5] = TableUtils.getTableBeginnings(5, codes);
        codes[6] = TableUtils.getTableBeginnings(6, codes);
        codes[7] = TableUtils.getTableBeginnings(7, codes);
        codes[8] = TableUtils.getTableBeginnings(8, codes);
    }

    public TableOfCodes[] getCodes() {
        return codes;
    }

    public TableOfCodes getCodes(Integer size) {
        return codes[size];
    }
}
