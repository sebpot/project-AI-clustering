package pl.si.projekt;

import java.util.*;

public class Tokenizer {

    private static final List<String> consList = Arrays.asList("a", "do", "z", "na", "za", "ze", "i");
    private static final List<String> sufList = Arrays.asList("em", "a", "y", "e", "ę", "ą", "i");

    public Tokenizer() {

    }

    public List<String> tokenize(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str);
        List<String> tokens = new ArrayList<>();

        while (stringTokenizer.countTokens() != 0) {
            String next = stringTokenizer.nextToken().toLowerCase(Locale.ROOT);
            if (!(consList.contains(next))) {
                for (String suf : sufList) {
                    if (next.endsWith(suf)) {
                        next = next.substring(0, next.length() - suf.length());
                        break;
                    }
                }
                tokens.add(next);
            }
        }

        return tokens;
    }

}
