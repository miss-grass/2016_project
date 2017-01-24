// Generates interior-scrambled words and interior-reversed words,
// proceeds one line of String at a time.
// (It doesn't consider the non-alphabetic letters in the text. May work on the problem latter.)

import java.util.*;

public class Typoglycemia {
   private String[] parts;
   public static final int UNCHANGABLE = 3; // the word length when it won't be changed
   
   public Typoglycemia(String original) {
      parts = original.split("[ \t]+");
   }
   
   public String reorganizeJumble() {
      String result = "";
      for (String s: parts) {
         if (s.length() <= UNCHANGABLE) {
            result += s + " ";
         } else {
            int last = s.length() - 1;
            result += s.charAt(0) + chaos(s.substring(1, last)) + s.charAt(last) + " ";
         }
      }
      return result.trim();
   }
   
   public String reorganizeReverse() {
      String result = "";
      for (String s: parts) {
         if (s.length() <= UNCHANGABLE) {
            result += s + " ";
         } else {
            int last = s.length() - 1;
            result += s.charAt(0) + reverse(s.substring(1, last)) + s.charAt(last) + " ";
         }
      }
      return result.trim();
   }
   
   // randomly reorganizes letters
   public String chaos(String interior) {
      ArrayList<Character> letterList = new ArrayList<Character>();
      for (int i = 0; i < interior.length(); i++) {
         letterList.add(interior.charAt(i));
      }
      Collections.shuffle(letterList);
      String result = "";
      for (char ch: letterList) {
         result += ch;
      }
      return result;
   }
   
   // reverse letters
   public String reverse(String interior) {
      if (interior.length() < 2) {
         return interior;
      } else {
         int last = interior.length() - 1;
         return interior.charAt(last) + reverse(interior.substring(1, last)) + interior.charAt(0);
      }
   }
}