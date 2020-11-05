
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LabTask6Sentence extends Sentence {
    public LabTask6Sentence(Text text, int off, int len, boolean question){
        this.text = new LabTask6Text(text.toString());
        this.off = off;
        this.len = len;
        this.question = question;
    }

    public String getSecondWordStartingWith(char ch){
        String regex = "\\b.+?\\b\\b" + ch + ".+?\\b";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(this.toString());

        if(matcher.find()){
            var iter = this.getWords();
            if(iter.hasNext()){
                iter.next();
                if(iter.hasNext()){
                    return iter.next().toString();
                }
            }
        }

        return null;
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

            var wIter = getWords();

            while (wIter.hasNext()) {
                var w = wIter.next();
                var type = WordHelper.wordType(w.toString());

                statistics.put(type, statistics.get(type) + 1);

            }
        }
    }

    @Override
    public Iterator<Word> getWords() {
        var list = new ArrayList<Word>();

        String regex = "\\S+";//"\\b.+?\\b";
        Pattern pattern = Pattern.compile(regex);

        var sentence= this.toString();

        Matcher matcher = pattern.matcher(sentence);
        while(matcher.find()){
            var str = sentence.substring(matcher.start(), matcher.end());

            if(!str.isBlank())
                list.add(new Word(str, matcher.start() + getOff()));
        }

        return list.iterator();
    }
}
