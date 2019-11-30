package pl.put.poznan.transformer.logic;

/**
 * Klasa sluzaca do rozwijania skrotow wystepujacych w podanym tekscie na pelne formy
 *
 * @version 1.0
 */

public class AbbreviationToFullFormTransformer extends TextTransformerDecorator {

    /**
     * konstruktor tworzacy obiekt dekorujacy podany TextTransformer
     *
     *@param textTransformer typu TextTransformer
     */

    public AbbreviationToFullFormTransformer(TextTransformer textTransformer) {
        super(textTransformer);
    }

    /**
     * Metoda rozwijajaca skroty wystepujace w podanym tekscie na pelne formy
     *
     * @param text typu String przechowuje tekst majacy zostac poddany transformacji
     * @return zwracany jest tekst, w ktorym wszystkie zdefiniowane skroty: prof., dr, np, itd., sa rozwijane na pelne formy
     *
     */

    public String transform(String text){
        text = super.transform(text);

        String[] words = text.split(" ");
        StringBuilder resultBuilder = new StringBuilder();

        String[] abbreviations = {"prof.", "dr", "np.", "itd."};
        String[] full_forms = {"profesor", "doktor", "na przykład", "i tym podobne"};
        boolean IsAbbreviation;
        for(int i = 0; i < words.length; i++) {
            IsAbbreviation = false;
            for(int j = 0; j < abbreviations.length; j++){
                if(words[i].equalsIgnoreCase(abbreviations[j])){
                    IsAbbreviation = true;
                    String FullForm = words[i].charAt(0) + full_forms[j].substring(1);
                    resultBuilder.append(FullForm);
                }
            }
            if(!IsAbbreviation){
                resultBuilder.append(words[i]);
            }
            if(i < words.length - 1){
                resultBuilder.append(" ");
            }
        }
        if(words[words.length - 1].equalsIgnoreCase("itd.")){
            resultBuilder.append(".");
        }
        return resultBuilder.toString();
    }
}
