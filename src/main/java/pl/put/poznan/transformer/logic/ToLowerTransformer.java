package pl.put.poznan.transformer.logic;

public class ToLowerTransformer extends TextTransformerDecorator {

    public ToLowerTransformer(TextTransformer textTransformer) {
        super(textTransformer);
    }

    public String transform(String text){
        text = super.transform(text);

        return text.toLowerCase();
    }
}
