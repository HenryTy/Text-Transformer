package pl.put.poznan.transformer.logic;

public class FullFormToAbbreviationTransformer extends TextTransformerDecorator{

    public FullFormToAbbreviationTransformer(TextTransformer textTransformer) {
        super(textTransformer);
    }

    public String parse(String[] words, int start, int N){
        String parsed = "";
        for(int i = start; i < start + N; i++){
            parsed += words[i];
            if(i < start + N - 1){
                parsed += " ";
            }
        }

        return parsed;
    }

    public String transform(String text){
        text = super.transform(text);

        String[] words = text.split(" ");
        StringBuilder resultBuilder = new StringBuilder();

        String[] abbreviations1 = {"prof.", "dr"};
        String[] abbreviations2 = {"np."};
        String[] abbreviations3 = {"itd."};
        String[] singleWord = {"profesor", "doktor"};
        String[] doubleWord = {"na przykład"};
        String[] tripleWord = {"i tym podobne"};

        boolean IsAbbreviatable;
        String expression;

        for(int i = 0; i < words.length; i++) {
            IsAbbreviatable = false;
            expression = parse(words, i, 1);

            //text3 = parse(words, i, 3);

            for(int j = 0; j < singleWord.length; j++){
                if(expression.equalsIgnoreCase(singleWord[j])){
                    IsAbbreviatable = true;
                    resultBuilder.append(abbreviations1[j]);
                }
            }

            if(!IsAbbreviatable && i < words.length - 1){
                expression = parse(words, i, 2);
                for(int j = 0; j < doubleWord.length; j++){
                    if(expression.equalsIgnoreCase(doubleWord[j])){
                        IsAbbreviatable = true;
                        resultBuilder.append(abbreviations2[j]);
                        i++;
                    }
                }
            }

            if(!IsAbbreviatable && i < words.length - 2){
                expression = parse(words, i, 3);
                for(int j = 0; j < tripleWord.length; j++){
                    if(expression.equalsIgnoreCase(tripleWord[j])){
                        IsAbbreviatable = true;
                        resultBuilder.append(abbreviations3[j]);
                        i += 2;
                    }
                }
            }

            if(!IsAbbreviatable){
                resultBuilder.append(words[i]);
            }
            if(i < words.length - 1){
                resultBuilder.append(" ");
            }
        }

        return resultBuilder.toString();
    }
}
