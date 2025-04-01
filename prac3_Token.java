public class prac3_Token {
    private String type;
    private String value;
    private int line;

    public prac3_Token(String type, String value, int line){
        this.type = type;
        this.value = value;
        this.line = line;
    }

    @Override
    public String toString(){
        return "type :" +type + ", value :"+value +", line :"+line;
    }
}