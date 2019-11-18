package pl.put.poznan.transformer.logic;

public class ToUpperTransformer extends TextTransformerDecorator {

    public ToUpperTransformer(TextTransformer textTransformer) {
        super(textTransformer);
    }

    public String transform(String text){
        text = super.transform(text);

        return text.toUpperCase();
    }
}
