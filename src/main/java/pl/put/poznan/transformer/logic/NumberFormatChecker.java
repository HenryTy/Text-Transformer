package pl.put.poznan.transformer.logic;

public class NumberFormatChecker {

    public boolean isNumeric(String s) {
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

    public boolean isFloat(String str){
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '.' || c == ',') {
                return true;
            }
        }
        return false;
    }
}
