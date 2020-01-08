package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.error.BadAnalysisException;

public class TextAnalysisExecutor {

    public String analyseText(String analysisName, String text) throws BadAnalysisException {
        TextAnalyzer analyzer;
        switch (analysisName) {
            case "anagram":
                analyzer = new CheckAnagrams();
                break;
            case "distance":
                analyzer = new WordsDistance();
                break;
            default:
                throw new BadAnalysisException("Bad analysis name: " + analysisName);
        }
        return analyzer.analyze(text);
    }
}
