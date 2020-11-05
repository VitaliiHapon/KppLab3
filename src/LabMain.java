import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LabMain {
    public static void main(String[] args) {
        //hello?word 545. 4545,656 gdfgd g434 ? 4343 fdgg 545,5434 434 gfdgfg 3434 gdfg?
        //vddsfsfds sdg sds. sdgfdg sdg ssdf.fsd fsd f? sdfsdf.sdf ? sfdf.; dfsf ? fsdвааіва аіва. авііва?df.?


        while(true) {
            try {
                Scanner scan = new Scanner(System.in);
                System.out.print("Enter text: ");

                var str = scan.nextLine();
                var map = analyzeText(str);
                System.out.println("Task 1:");
                System.out.println("\tText statitistic: " + map);
                System.out.println("\tText with numbers types: " + putNumberTypes(str));
                System.out.println("\tText without sentence with max other words: " + removeSentenceByMaxOtherWords(str));

                System.out.println("Task 2:");
                System.out.print("\tEnter char of second words: ");

                var ch = scan.next().charAt(0);
                System.out.print("\tEnter words length in questions: ");
                var len = scan.nextInt();

                System.out.println("\t\t Second words starts with char: ");
                var seconds = getSecondWordsStartsWith(ch, str);
                System.out.print(seconds);

                System.out.println("\t\t Words with length " + len + " in questions: ");
                var fixed = getWordsByLengthInQuestions(len, str);
                System.out.print(fixed);

                System.out.println();
            }
            catch (Exception e) {
                System.out.println("Error...");
            }

        }
    }

    public static String getWordsByLengthInQuestions(int len, String string){
        var text = new LabTask6Text(string);

        var sIter = text.getWordsByLengthFromQuestions(len);

        StringBuilder result = new StringBuilder();

        while(sIter.hasNext()){
            var s = sIter.next();

            result.append(s + "\n");
        }

        return result.toString();
    }

    public static String getSecondWordsStartsWith(char ch, String string){
        var text = new LabTask6Text(string);

        var sIter = text.getSentences();

        StringBuilder result = new StringBuilder();

        while(sIter.hasNext()){
            var s = sIter.next();

            if(s instanceof LabTask6Sentence){
                var word = ((LabTask6Sentence)s).getSecondWordStartingWith(ch);
                if(word != null)
                    result.append( word+ "\n");
            }

        }

        return result.toString();
    }

    public static String removeSentenceByMaxOtherWords(String string){
        var text = new LabTask6Text(string);

        var sIter= text.getSentences();

        Sentence maxSentence = null;
        int otherWordsMax = 0;

        while(sIter.hasNext()) {
            var s = sIter.next();

            Map<String, Integer> result = new HashMap<>();
            s.calculateStatistic(result);
            var otherVal = result.get(WordHelper.OTHER);
            if(otherVal.intValue() > otherWordsMax){
                otherWordsMax = otherVal.intValue();
                maxSentence = s;
            }

        }

        if(maxSentence != null){
            text.removeSentence(maxSentence);
        }

        return  text.toString();
    }

    public static String putNumberTypes(String string){
        StringBuilder result = new StringBuilder(string);

        var text = new LabTask6Text(string);

        var sIter= text.getSentences();

        int insertOffset = 0;

        final var intTypeStr = "(int)";
        final var realTypeStr = "(real)";

        while(sIter.hasNext()){
            var s = sIter.next();
            var wIter = s.getWords();
            while(wIter.hasNext()){
                var w = wIter.next();
                var type = WordHelper.wordType(w.toString());

                if(WordHelper.isIntNumber(w.toString())){
                    result.insert(w.getPos() + w.toString().length() + insertOffset, intTypeStr);
                    insertOffset += intTypeStr.length();
                }
                else if(WordHelper.isRealNumber(w.toString())){
                    result.insert(w.getPos() + w.toString().length() + insertOffset, realTypeStr);
                    insertOffset += realTypeStr.length();
                }
            }
        }
        return result.toString();
    }

    public static Map<String, Integer> analyzeText(String string){
        Map<String, Integer> result = new HashMap<>();

        var text = new LabTask6Text(string);

        text.calculateStatistic(result);
        return result;
    }
}
