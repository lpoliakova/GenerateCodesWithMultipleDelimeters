package Tables;

/**
 * Created by oradchykova on 5/22/17.
 */
public class IntegerCode {
    private final Integer size;
    private final Integer code;

    public IntegerCode(Integer size, Integer code){
        this.size = size;
        this.code = code;
    }

    public IntegerCode(String code){
        this(code.length(), Integer.parseInt(code, 2));
    }

    public Integer getCodeSize() {
        return size;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String toString(){
        String number = Integer.toBinaryString(code);
        while (number.length() < size) {
            number = "0" + number;
        }
        //if (number.length() > size){
        //    number = number.substring(number.length() - size, number.length());
        //}
        return number;
    }
}
