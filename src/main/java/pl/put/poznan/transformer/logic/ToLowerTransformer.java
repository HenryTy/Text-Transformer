package pl.put.poznan.transformer.logic;

/**
 * Klasa sluzaca do zamiany wszystkich liter wystepujacych w ciagu do transformacji na male
 *
 * @version 1.0
 */

public class ToLowerTransformer extends TextTransformerDecorator {

    /**
     * konstruktor tworzacy obiekt dekorujacy podany TextTransformer
     *
     *@param textTransformer typu TextTransformer
     */

    public ToLowerTransformer(TextTransformer textTransformer) {
        super(textTransformer);
    }

    /**
     * Metoda zamieniajaca wszystkie litery wystepujace w ciagu do transformacji na male
     *
     * @param text typu String przechowuje tekst majacy zostac poddany transformacji
     * @return zwracany jest tekst, ktorego wszystkie litery zostaly zamienione na male
     *
     */

    public String transform(String text){
        text = super.transform(text);

        return text.toLowerCase();
    }
}
