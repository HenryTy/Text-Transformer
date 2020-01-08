package pl.put.poznan.transformer.logic;
import java.util.Arrays;

public class CheckAnagrams implements TextAnalyzer {

    @Override
    public String analyze(String text) {
        String[] words = text.split(" ");
        if(words.length != 2){
            return "Podano błędną ilość wyrazów do sprawdzenia";
        }
        else{
            if(words[0].length() != words[1].length()){
                return "Podane słowa nie są anagramami";
            }
            else{
                char[] word1 = words[0].toCharArray();
                char[] word2 = words[1].toCharArray();
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
