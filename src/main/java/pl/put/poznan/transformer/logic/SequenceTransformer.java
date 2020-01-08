package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.error.BadTransformationException;

public class SequenceTransformer {

    private TextTransformer textTransformer;

    public SequenceTransformer(String[] transforms) throws BadTransformationException{
        textTransformer = new BasicTransformer();
        for(String trName : transforms) {
            textTransformer = createTransformerFromName(trName, textTransformer);
        }
    }

    public String transform(String text){
        return textTransformer.transform(text);
    }

    private TextTransformer createTransformerFromName(String name, TextTransformer decoratedTransformer)
            throws BadTransformationException {
        switch (name) {
            case "upper":
                return new ToUpperTransformer(decoratedTransformer);
            case "lower":
                return new ToLowerTransformer(decoratedTransformer);
            case "capitalize":
                return new CapitalizeTransformer(decoratedTransformer);
            case "abbToFull":
                return new AbbreviationToFullFormTransformer(decoratedTransformer);
            case "eliminateDuplicates":
                return new EliminateDuplicatesTransformer(decoratedTransformer);
            case "numtext":
                return new NumbersToTextTransformer(decoratedTransformer);
            case "latex":
                return new ToLatexFormTransformer(decoratedTransformer);
            case "reverse":
                return new ReverseTransformer(decoratedTransformer);
            case "fullToAbb":
                return new FullFormToAbbreviationTransformer(decoratedTransformer);
            default:
                throw new BadTransformationException("Bad transformation: " + name);
        }
    }
}
