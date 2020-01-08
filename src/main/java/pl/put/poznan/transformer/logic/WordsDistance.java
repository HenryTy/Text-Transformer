package pl.put.poznan.transformer.logic;

public class WordsDistance implements TextAnalyzer {

    private int min(int a, int b, int c){
        int m = 0;
        if(a <= b && a <= c) m = a;
        if(b <= a && b <= c) m = b;
        if(c <= a && c <= b) m = c;
        return m;
    }

    private int LevenshteinDistance(String a ,String b){
        int m = a.length(), n = b.length();
        int[][] distances = new int[m + 1][n + 1];

        for(int i = 0; i <= m; i++){
            for(int j = 0; j <= n; j++){
                distances[i][j] = 0;
            }
        }

        for(int i = 1; i <= m; i++){
            distances[i][0] = i;
        }

        for(int i = 1; i <= n; i++){
            distances[0][i] = i;
        }
        int cost;
        for(int j = 1; j <= n; j++){
            for(int i = 1; i <= m; i++){
                if(a.charAt(i - 1) == b.charAt(j - 1)){
                    cost = 0;
                }
                else{
                    cost = 1;
                }
                distances[i][j] = min(distances[i - 1][j] + 1, distances[i][j - 1] + 1, distances[i - 1][j - 1] + cost);
            }
        }

        return distances[m][n];
    }

    @Override
    public String analyze(String text){
        String[] words = text.split(" ");
        if(words.length != 2) {
            return "Podano błędną ilość wyrazów do sprawdzenia";
        }

        int d = LevenshteinDistance(words[0], words[1]);

        return Integer.toString(d);
    }
}
