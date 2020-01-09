package pl.put.poznan.transformer.logic;

/**
 * Klasa sluzaca do usuniecia powtarzajacych sie wyrazow
 *
 * @version 1.0
 */

public class EliminateDuplicatesTransformer extends TextTransformerDecorator {

    /**
     * konstruktor tworzacy obiekt dekorujacy podany TextTransformer
     *
     *@param textTransformer typu TextTransformer
     */

    public EliminateDuplicatesTransformer(TextTransformer textTransformer) {
        super(textTransformer);
    }

    /**
     * Metoda usuwajace powtarzajace sie wyrazy
     *
     * @param text typu String przechowuje tekst majacy zostac poddany transformacji
     * @return zwracany jest tekst bez powtorzen
     *
     */

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
