package riddles;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("\nparseInt(\"-2147483647\"):\n" + new IntParser().parseInt("-2147483647"));
        System.out.println("\nfindPrimeFactors(9999990):\n" + new PrimeFactorFinder().findPrimeFactors(9999990));
        System.out.println("\ngetIndex(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 8):\n"
                + new IntFinder().getIndex(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 8));
        System.out.println("\nprintAnagrams(\"anagram\"):");
        new AnagramFinder().printAnagrams("anagram");
    }
}
