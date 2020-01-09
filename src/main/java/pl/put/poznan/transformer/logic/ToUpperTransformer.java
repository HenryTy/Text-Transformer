package pl.put.poznan.transformer.logic;

/**
 * Klasa sluzaca do zamiany wszystkich liter wystepujacych w ciagu do transformacji na duze
 *
 * @version 1.0
 */


public class ToUpperTransformer extends TextTransformerDecorator {

    /**
     * konstruktor tworzacy obiekt dekorujacy podany TextTransformer
     *
     *@param textTransformer typu TextTransformer
     */


    public ToUpperTransformer(TextTransformer textTransformer) {
        super(textTransformer);
    }

    /**
     * Metoda zamieniajaca wszystkie litery wystepujace w ciagu do transformacji na duze
     *
     * @param text typu String przechowuje tekst majacy zostac poddany transformacji
     * @return zwracany jest tekst, ktorego wszystkie litery zostaly zamienione na duze
     *
     */

    public String transform(String text){
        text = super.transform(text);

        return text.toUpperCase();
    }
}
