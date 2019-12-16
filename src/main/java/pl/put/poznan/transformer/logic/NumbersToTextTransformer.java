package pl.put.poznan.transformer.logic;

public class NumbersToTextTransformer extends TextTransformerDecorator {

    private NumberFormatChecker numberFormatChecker;
    private NumberToTextConverter numberToTextConverter;

    public NumbersToTextTransformer(TextTransformer textTransformer) {
        super(textTransformer);
        this.numberFormatChecker = new NumberFormatChecker();
        this.numberToTextConverter = new NumberToTextConverter();
    }

    public NumbersToTextTransformer(TextTransformer textTransformer,
                                    NumberFormatChecker numberFormatChecker,
                                    NumberToTextConverter numberToTextConverter) {
        this(textTransformer);
        this.numberFormatChecker = numberFormatChecker;
        this.numberToTextConverter = numberToTextConverter;
    }

    public String transform(String text) {
        text = super.transform(text);

        String[] words = text.split(" ");
        StringBuilder resultBuilder = new StringBuilder();

        for (String word : words) {
            if (numberFormatChecker.isNumeric(word)) {
                String integerPart = numberToTextConverter.convertIntegerPart(word);
                resultBuilder.append(integerPart);
                if(numberFormatChecker.isFloat(word)) {
                    String fractionalPart = numberToTextConverter.convertFractionalPart(word);
                    if(integerPart.length() > 0 && fractionalPart.length() > 0) resultBuilder.append(" i ");
                    if(!fractionalPart.equals(" ")) resultBuilder.append(fractionalPart);
                }
                if(word.equals("0") || word.equals("0.0") || word.equals("0,0")) resultBuilder.append("zero");
                resultBuilder.append(" ");
            }
            else{
                resultBuilder.append(word);
                resultBuilder.append(" ");
            }
        }

        return resultBuilder.toString();
    }
}

