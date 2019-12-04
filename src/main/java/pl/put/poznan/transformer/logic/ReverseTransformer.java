package pl.put.poznan.transformer.logic;

public class ReverseTransformer extends TextTransformerDecorator{
    public ReverseTransformer(TextTransformer textTransformer) {
        super(textTransformer);
    }
    public String transform(String text){
        StringBuilder result = new StringBuilder();
        boolean[] indexes = new boolean[text.length()];
        char[] text_arr = text.toCharArray();
        for(int i = 0; i < text.length(); i++){
            if((int)text_arr[i] > 64 && (int)text_arr[i] < 91){
                indexes[i] = true;
            }
            else{
                indexes[i] = false;
            }
        }
        text = text.toLowerCase();
        char[] text_arr2 = text.toCharArray();
        for(int i = 0; i < text.length(); i++){
            if(indexes[i] == false || (int)text_arr2[text.length()-1-i] == 32){
                result.append(text_arr2[text.length()-1-i]);
            }
            else{
                int pom = (int)text_arr2[text.length()-1-i] - 32;
                result.append((char)pom);
            }
        }
        return result.toString();
    }
}
