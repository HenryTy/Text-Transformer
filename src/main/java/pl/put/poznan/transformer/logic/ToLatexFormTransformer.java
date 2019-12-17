package pl.put.poznan.transformer.logic;

public class ToLatexFormTransformer extends TextTransformerDecorator{
    public ToLatexFormTransformer(TextTransformer textTransformer) {
        super(textTransformer);
    }
    public String transform(String text){
        text = super.transform(text);
        char[] text_before = text.toCharArray();
        StringBuilder text_after = new StringBuilder();
        for(int i = 0;i < text_before.length; i++){
            if(text_before[i] == '$' || text_before[i] == '&'){
                text_after.append('\\');
                text_after.append(text_before[i]);
            }
            else{
                text_after.append(text_before[i]);
            }
        }
        return text_after.toString();
    }
}
