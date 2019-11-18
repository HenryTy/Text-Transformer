package pl.put.poznan.transformer.logic;

public class CapitalizeTransformer extends TextTransformerDecorator {

    public CapitalizeTransformer(TextTransformer textTransformer) {
        super(textTransformer);
    }

    public String transform(String text){
        text = super.transform(text);

        String[] words = text.split(" ");
        StringBuilder resultBuilder = new StringBuilder();
        for(int i = 0; i < words.length; i++) {
            String capitalized = words[i].substring(0, 1).toUpperCase() + words[i].substring(1);
            resultBuilder.append(capitalized);
            resultBuilder.append(" ");
        }
        return resultBuilder.toString();
    }
}
