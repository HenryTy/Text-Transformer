package pl.put.poznan.transformer.logic;

/**
 * Klasa sluzaca do zamieniania pierwszej litery slow w tekscie na duza
 *
 * @version 1.0
 */

public class CapitalizeTransformer extends TextTransformerDecorator {

    /**
     * konstruktor tworzacy obiekt dekorujacy podany TextTransformer
     *
     *@param textTransformer typu TextTransformer
     */

    public CapitalizeTransformer(TextTransformer textTransformer) {
        super(textTransformer);
    }

    /**
     * Metoda zamieniajaca pierwsza litere wszystkich slow w tekscie na duza
     *
     * @param text typu String przechowuje tekst majacy zostac poddany transformacji
     * @return zwracany jest tekst, w ktorym pierwsze litery slow zostaly zamienione na duze
     *
     */

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
