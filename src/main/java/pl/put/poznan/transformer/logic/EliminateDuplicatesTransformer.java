package pl.put.poznan.transformer.logic;

public class EliminateDuplicatesTransformer extends TextTransformerDecorator {

    public EliminateDuplicatesTransformer(TextTransformer textTransformer) {
        super(textTransformer);
    }

    @Override
    public String transform(String text) {
        text = super.transform(text);

        String[] words = text.split(" ");
        StringBuilder resultBuilder = new StringBuilder();

        for(int i = 0; i < words.length; i++) {
            if(i==0 || !words[i].equalsIgnoreCase(words[i-1])) {
                resultBuilder.append(words[i]);
                resultBuilder.append(" ");
            }
        }

        return resultBuilder.toString();
    }
}
