package pl.put.poznan.transformer.logic;
import java.util.Arrays;

public class CheckAnagrams extends TextTransformerDecorator {
    public CheckAnagrams(TextTransformer textTransformer) {
        super(textTransformer);
    }
    public String transform(String text){
        text = super.transform(text);
        String[] words = text.split(" ");
        if(words.length != 2){
            return "Podano błędną ilość wyrazów do sprawdzenia";
        }
        else{
            if(words[0].length() != words[1].length()){
                return "Podane słowa nie są anagramami";
            }
            else{
                char[] word1 = new char[words[0].length()];
                char[] word2 = new char[words[1].length()];
                word1 = words[0].toCharArray();
                word2 = words[1].toCharArray();
                Arrays.sort(word1);
                Arrays.sort(word2);
                int pom = 0;
                for(int i = 0; i<word1.length; i++){
                    if(word1[i] == word2[i]){
                        pom = pom + 1;
                    }
                }
                if(pom == word1.length){
                    return "Podane słowa są anagramami";
                }
                else{
                    return "Podane słowa nie są anagramami";
                }
            }
        }
    }
}
