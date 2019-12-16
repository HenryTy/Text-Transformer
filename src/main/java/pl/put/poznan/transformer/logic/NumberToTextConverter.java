package pl.put.poznan.transformer.logic;

public class NumberToTextConverter {

    public String convertIntegerPart(String numberToConvert) {
        String[] setki = {"", "sto", "dwieście", "trzysta", "czterysta", "pięćset", "sześćset", "siedemset", "osiemset", "dziewięćset"};
        String[] nastki = {"dziesięć", "jedenaście", "dwanaście", "trzynaście", "czternaście", "piętnaście", "szesnaście", "siedemnaście", "osiemnaście", "dziewiętnaście"};
        String[] dziesiatki = {"", "dziesięć", "dwadzieścia", "trzydzieści", "czterdzieści", "pięćdziesiąt", "sześćdziesiąt", "siedemdziesiąt", "osiemdziesiąt", "dziewięćdziesiąt"};
        String[] jednosci = {"", "jeden", "dwa", "trzy", "cztery", "pięć", "sześć", "siedem", "osiem", "dziewięć"};

        StringBuilder resultBuilder = new StringBuilder();
        StringBuilder newStr = new StringBuilder();

        boolean flag = true;
        for(int i = 0; i < numberToConvert.length(); i++) {
            char c = numberToConvert.charAt(i);
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
                    if(newStr.charAt(i) != '0' && newStr.charAt(i + 1) != '0') resultBuilder.insert(0, ' ');
                    resultBuilder.insert(0, dziesiatki[a]);
                }
            }
            else if(newStr.length() - 1 - i == 2) {
                if(newStr.charAt(1) != '0' || newStr.charAt(2) != '0') resultBuilder.insert(0, ' ');
                resultBuilder.insert(0, setki[a]);
            }
        }

        return resultBuilder.toString();
    }

    public String convertFractionalPart(String numberToConvert){
        String[] dziesiatki = {"", "dziesięć", "dwadzieścia", "trzydzieści", "czterdzieści", "pięćdziesiąt", "sześćdziesiąt", "siedemdziesiąt", "osiemdziesiąt", "dziewięćdziesiąt"};
        String[] nastki = {"dziesięć", "jedenaście", "dwanaście", "trzynaście", "czternaście", "piętnaście", "szesnaście", "siedemnaście", "osiemnaście", "dziewiętnaście"};
        String[] czesci_ulamkowe = {"", "jedna", "dwie", "trzy", "cztery", "pięć", "sześć", "siedem", "osiem", "dziewięć"};
        String[] jednosci = {"", "jeden", "dwie", "trzy", "cztery", "pięć", "sześć", "siedem", "osiem", "dziewięć"};

        StringBuilder newStr = new StringBuilder();
        StringBuilder resultBuilder = new StringBuilder();

        boolean flag = false;
        for(int i = 0; i < numberToConvert.length(); i++) {
            char c = numberToConvert.charAt(i);
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
            if(a != 0) resultBuilder.append(" ");
            if(a == 0 && b == 1) resultBuilder.append("jedna");
            else resultBuilder.append(jednosci[b]);
            if(b != 0) resultBuilder.append(" ");
            if(b == 2 || b == 3 || b == 4) resultBuilder.append("setne");
            else if(a == 0 && b == 1) resultBuilder.append("setna");
            else if(a != 0 || b != 0) resultBuilder.append("setnych");
        }
        else{
            int a = newStr.charAt(0) - '0';
            resultBuilder.append(czesci_ulamkowe[a]);
            if(a != 0) resultBuilder.append(" ");
            if(a == 1) resultBuilder.append("dziesiąta");
            else if(a == 2 || a == 3 || a == 4) resultBuilder.append("dziesiąte");
            else if(a != 0) resultBuilder.append("dziesiątych");
        }

        return resultBuilder.toString();
    }
}
