package Structures;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oradchykova on 5/21/17.
 */
public class TableMiddle {
    public final Integer positionStart0;
    public final Integer positionStart10;
    public final Integer positionStart110;
    public final Integer positionStart1110;

    public final TableOfCodes codes;

    public TableMiddle(){
        TableBeginnings beginnings = new TableBeginnings();
        positionStart0 = 0;
        positionStart10 = beginnings.getCodes(7).getAmountOfCodes();
        positionStart110 = positionStart10 + beginnings.getCodes(6).getAmountOfCodes();
        positionStart1110 = positionStart110 + beginnings.getCodes(5).getAmountOfCodes();
        TableOfCodes[] allBeginnings = new TableOfCodes[]{
                beginnings.getCodes(7),
                beginnings.getCodes(6),
                beginnings.getCodes(5),
                beginnings.getCodes(4),
                beginnings.getCodes(3),
                beginnings.getCodes(2),
                beginnings.getCodes(1)};
        int size = countSize(allBeginnings);
        codes = new TableOfCodes(8, size, growCodes(allBeginnings));
    }

    private Integer countSize(TableOfCodes[] codes){
        int counter = 0;
        for (int i = 0; i < codes.length; i++){
            counter += codes[i].getAmountOfCodes();
        }
        return counter + 2;
    }

    private List<String> growCodes(TableOfCodes[] codes){
        List<String> resultingCodes = new ArrayList<>();
        String addition = "0";
        for (int i = 0; i < codes.length; i++){
            List<String> codesSize = new ArrayList<>();
            codesSize.addAll(codes[i].getCodes());
            growCode(codesSize, addition);
            resultingCodes.addAll(codesSize);
            addition = "1" + addition;
        }
        resultingCodes.add(addition);
        resultingCodes.add(addition.substring(0, addition.length() - 1) + "1");
        return resultingCodes;
    }

    private void growCode(List<String> codes, String addition){
        for (int i = 0; i < codes.size(); i++) {
            codes.set(i, addition + codes.get(i));
        }
    }
}
