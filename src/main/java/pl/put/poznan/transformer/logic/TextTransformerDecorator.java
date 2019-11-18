package pl.put.poznan.transformer.logic;

public class TextTransformerDecorator implements TextTransformer {

    protected TextTransformer textTransformer;

    public TextTransformerDecorator(TextTransformer textTransformer) {
        this.textTransformer = textTransformer;
    }

    @Override
    public String transform(String text) {
        return textTransformer.transform(text);
    }
}
