import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordHelper {

    public static final String LATIN = "Latin";
    public static final String CYRILLIC = "Cyrillic";
    public static final String NUMBER = "Number";
    public static final String OTHER = "Other";

    public static boolean isNumber(String word){
        return isRealNumber(word) || isIntNumber(word);
    }

    public static boolean isRealNumber(String word){
        try{
            var val = Double.valueOf(word.replace(',','.'));
        }
        catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    public static boolean isIntNumber(String word){
        try{
            var val = Long.valueOf(word);
        }
        catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    public static String wordType(String word){
        String latinRegex = "[a-zA-Z]+";
        String cyrillicRegex = "[абвгдeєжзиіїйклмнопрстуфхцчшщьюяАБВГДЕЄЖЗИІЇЙКЛИНОПРСТУФХЦЧШЩЬЮЯ]+";

        Pattern latinPattern = Pattern.compile(latinRegex);
        Pattern cyrillicPattern = Pattern.compile(cyrillicRegex);

        Matcher latinMathcer = latinPattern.matcher(word);
        Matcher cyrillicMathcer = cyrillicPattern.matcher(word);

        if(latinMathcer.matches()){
            return LATIN;
        }
        else if(cyrillicMathcer.matches()){
            return CYRILLIC;
        }
        else if(isNumber(word)){
            return NUMBER;
        }
        else {
            return OTHER;
        }
    }
}
