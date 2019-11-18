package pl.put.poznan.transformer.logic;

public class AbbreviationToFullFormTransformer extends TextTransformerDecorator {

    public AbbreviationToFullFormTransformer(TextTransformer textTransformer) {
        super(textTransformer);
    }

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