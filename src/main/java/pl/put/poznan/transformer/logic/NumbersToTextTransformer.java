package pl.put.poznan.transformer.logic;

public class NumbersToTextTransformer extends TextTransformerDecorator {

    public NumbersToTextTransformer(TextTransformer textTransformer) {
        super(textTransformer);
    }

    public static boolean isNumeric(String s) {
        if (s == null || s.equals("")) {
            return false;
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((c < '0' || c > '9') && (c != '.' && c != ',')) {
                return false;
            }
        }
        return true;
    }

    public static boolean isFloat(String str){
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '.' || c == ',') {
                return true;
            }
        }
        return false;
    }

    public String poPrzecinku(String str){
        String[] dziesiatki = {"", "dziesięć", "dwadzieścia", "trzydzieści", "czterdzieści", "pięćdziesiąt", "sześćdziesiąt", "siedemdziesiąt", "osiemdzieciąt", "dziewięćdziesiąt"};
        String[] nastki = {"dziesięć", "jedenaście", "dwanaście", "trzynaście", "czternaście", "piętnaście", "szesnaście", "siedemnaście", "osiemnaście", "dziewiętnaście"};
        String[] czesci_ulamkowe = {"", "jedna", "dwie", "trzy", "cztery", "pięć", "sześć", "siedem", "osiem", "dziewięć"};
        String[] jednosci = {"", "jeden", "dwie", "trzy", "cztery", "pięć", "sześć", "siedem", "osiem", "dziewięć"};

        StringBuilder newStr = new StringBuilder();
        StringBuilder resultBuilder = new StringBuilder();

        boolean flag = false;
        for(int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(flag){
                newStr.append(c);
            }
            if(c == '.' || c == ','){
                flag = true;
            }
        }
        if(newStr.charAt(0) == '1' && newStr.length() == 2){
            int a = newStr.charAt(1) - '0';
            resultBuilder.append(nastki[a]);
            resultBuilder.append(" ");
            resultBuilder.append("setnych");
        }
        else if(newStr.length() == 2){
            int a = newStr.charAt(0) - '0';
            int b = newStr.charAt(1) - '0';
            resultBuilder.append(dziesiatki[a]);
            resultBuilder.append(" ");
            resultBuilder.append(jednosci[b]);
            resultBuilder.append(" ");
            if(b == 2 || b == 3 || b == 4) resultBuilder.append("setne");
            else resultBuilder.append("setnych");
        }
        else{
            int a = newStr.charAt(0) - '0';
            resultBuilder.append(czesci_ulamkowe[a]);
            resultBuilder.append(" ");
            if(a == 1) resultBuilder.append("dziesiąta");
            else if(a == 2 || a == 3 || a == 4) resultBuilder.append("dziesiąte");
            else if(a != 0) resultBuilder.append("dziesiątych");
        }

        return resultBuilder.toString();
    }

    public String przedPrzecinkiem(String str){
        String[] setki = {"", "sto", "dwieście", "trzysta", "czterysta", "pięćset", "sześćset", "siedemset", "osiemset", "dziewięćset"};
        String[] nastki = {"dziesięć", "jedenaście", "dwanaście", "trzynaście", "czternaście", "piętnaście", "szesnaście", "siedemnaście", "osiemnaście", "dziewiętnaście"};
        String[] dziesiatki = {"", "dziesięć", "dwadzieścia", "trzydzieści", "czterdzieści", "pięćdziesiąt", "sześćdziesiąt", "siedemdziesiąt", "osiemdzieciąt", "dziewięćdziesiąt"};
        String[] jednosci = {"", "jeden", "dwa", "trzy", "cztery", "pięć", "sześć", "siedem", "osiem", "dziewięć"};

        StringBuilder resultBuilder = new StringBuilder();
        StringBuilder newStr = new StringBuilder();

        boolean flag = true;
        for(int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(c == '.' || c == ','){
                flag = false;
            }
            if(flag){
                newStr.append(c);
            }
        }

        for(int i = newStr.length() - 1; i >= 0; i--){
            char c = newStr.charAt(i);
            int a = newStr.charAt(i) - '0';

            if(newStr.length() - 1 - i == 0) {
                resultBuilder.insert(0, jednosci[a]);
            }
            else if(newStr.length() - 1 - i == 1) {
                if(a == 1){
                    int b = newStr.charAt(i+1) - '0';
                    resultBuilder.setLength(0);
                    resultBuilder.insert(0, nastki[b]);
                }
                else {
                    if(newStr.charAt(i) != '0') resultBuilder.insert(0, ' ');
                    resultBuilder.insert(0, dziesiatki[a]);
                }
            }
            else if(newStr.length() - 1 - i == 2) {
                if(newStr.charAt(1) != '0' && newStr.charAt(2) != '0') resultBuilder.insert(0, ' ');
                resultBuilder.insert(0, setki[a]);
            }
        }

        return resultBuilder.toString();
    }

    public String transform(String text){

        String[] words = text.split(" ");
        StringBuilder resultBuilder = new StringBuilder();

        for (String word : words) {
            if (isNumeric(word)) {
                if(isFloat(word)) {
                    resultBuilder.append(przedPrzecinkiem(word));
                    String pom = przedPrzecinkiem(word);
                    if(pom.length() > 0) resultBuilder.append(" i ");
                    resultBuilder.append(poPrzecinku(word));
                }
                else{
                    resultBuilder.append(przedPrzecinkiem(word));
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

