package pl.put.poznan.transformer.logic;

public class EliminateDuplicatesTransformer implements TextTransformer {

    @Override
    public String transform(String text) {
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
