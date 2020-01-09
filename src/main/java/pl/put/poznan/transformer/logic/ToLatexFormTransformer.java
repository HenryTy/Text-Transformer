package pl.put.poznan.transformer.logic;

/**
 * Klasa sluzaca do transformacji tekstu na format wspierany przez Latex
 *
 * @version 1.0
 */

public class ToLatexFormTransformer extends TextTransformerDecorator{

    /**
     * konstruktor tworzacy obiekt dekorujacy podany TextTransformer
     *
     *@param textTransformer typu TextTransformer
     */

    public ToLatexFormTransformer(TextTransformer textTransformer) {
        super(textTransformer);
    }

    /**
     * Metoda transformujaca tekst na format wspierany przez Latex
     *
     * @param text typu String przechowuje tekst majacy zostac poddany transformacji
     * @return zwracany jest tekst w formacie obslugiwanym przez Latex
     *
     */

    public String transform(String text){
        text = super.transform(text);
        char[] text_before = text.toCharArray();
        StringBuilder text_after = new StringBuilder();
        for(int i = 0;i < text_before.length; i++){
            if(text_before[i] == '$' || text_before[i] == '&'){
                text_after.append('\\');
                text_after.append(text_before[i]);
            }
            else{
                text_after.append(text_before[i]);
            }
        }
        return text_after.toString();
    }
}
