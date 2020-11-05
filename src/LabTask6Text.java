import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LabTask6Text extends Text {
    public LabTask6Text(String text){
        super(text);
    }

    @Override
    public void calculateStatistic(Map<String, Integer> statistics){
        if(statistics != null) {

            if (!statistics.containsKey(WordHelper.LATIN))
                statistics.put(WordHelper.LATIN, 0);
            if (!statistics.containsKey(WordHelper.CYRILLIC))
                statistics.put(WordHelper.CYRILLIC, 0);
            if (!statistics.containsKey(WordHelper.NUMBER))
                statistics.put(WordHelper.NUMBER, 0);
            if (!statistics.containsKey(WordHelper.OTHER))
                statistics.put(WordHelper.OTHER, 0);
        }

        var sIter= getSentences();

        while(sIter.hasNext()){
            var s = sIter.next();
            s.calculateStatistic(statistics);
        }
    }

    public Iterator<String> getWordsByLengthFromQuestions(int len){
        Set<String> uniqueWords = new HashSet<>();
        var list = new ArrayList<Sentence>();

        readSentences('?', list);

        for(var v: list){
            var sentence = v.toString();
            String regex = "\\S{"+ String.valueOf(len) +"}";//"\\b.+?\\b";
            Pattern pattern = Pattern.compile(regex);

            Matcher matcher = pattern.matcher(sentence);
            while(matcher.find()){
                var str = sentence.substring(matcher.start(), matcher.end());

                if(!str.isBlank())
                    uniqueWords.add(str);
            }
        }
        return uniqueWords.iterator();
    }

    private void readSentences(char endMark, List<Sentence> list){

        //vddsfsfds sdg sds. sdgfdg sdg ssdf.fsd fsd f? sdfsdf.sdf ? sfdf.; dfsf ? fsdвааіва аіва. авііва?df.?

        String regex = "(\\b[^\\?\\.]+?\\b)+\\s*\\" + endMark;
        Pattern sPattern = Pattern.compile(regex);

        var sentence= this.toString();

        Matcher matcher = sPattern.matcher(sentence);

        while(matcher.find()){
            var str = sentence.substring(matcher.start(), matcher.end() - 1).trim();

            if(!str.isBlank())
                list.add(new LabTask6Sentence(
                        this, matcher.start(), str.length(), endMark == '?'));
        }
    }

    @Override
    public Iterator<Sentence> getSentences() {
        var list = new ArrayList<Sentence>();

        readSentences('.', list);
        readSentences('?', list);

        Collections.sort(list, (a, b) -> a.getOff() - b.getOff());

        return list.iterator();
    }
}
