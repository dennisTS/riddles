package riddles;

import java.util.*;

import static java.lang.Math.pow;

public class AnagramFinder {
    private Map<String, StringBuilder> cache;

    public void printAnagrams(String word) {
        int count = 0;
        List<StringBuilder> anagrams = findAnagrams(word);

        System.out.println("Number of anagrams without repetitions is " + anagrams.size());

        for (StringBuilder anagram : anagrams) {
            System.out.print(anagram + " ");

            if (++count == 10) {
                System.out.println();
                count = 0;
            }
        }
    }

    public List<StringBuilder> findAnagrams(String word) {
        if (word == null || word.isEmpty()) {
            return Collections.emptyList();
        }

        cache = new HashMap<>();

        return doFindAnagrams(new StringBuilder(word.toLowerCase()));
    }

    private List<StringBuilder> doFindAnagrams(StringBuilder word) {
        if (word.length() == 1) {
            return new ArrayList<>(Arrays.asList(word));
        }

        List<StringBuilder> result = new ArrayList<>();
        boolean[] usedLetters = new boolean['z' - 'a' + 1];

        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            boolean isLetterUsed = usedLetters[currentChar - 'a'];
            if (!isLetterUsed) {
                usedLetters[currentChar - 'a'] = true;

                word.deleteCharAt(i);

                List<StringBuilder> subAnagrams = doFindAnagrams(word);
                for (StringBuilder subAnagram : subAnagrams) {
                    result.add(getStringBuilder("" + currentChar + subAnagram));
                }

                word.insert(i, currentChar);
            }
        }

        return result;
    }

    private StringBuilder getStringBuilder(String string) {
        if (cache.containsKey(string)) {
            return cache.get(string);
        } else {
            StringBuilder newEntry = new StringBuilder(string);
            cache.put(string, newEntry);

            return newEntry;
        }
    }
}
