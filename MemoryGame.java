// This program manages a memory game in which users watch a simple image for certain length of
// time and answer questions about the colors of different parts in the image.
// The image is drawn by using drawing panel: http://www.buildingjavaprograms.com/DrawingPanel.java
// (The program can't end by itself...)

import java.util.*;

public class MemoryGame {
   public static final int CHOICE_NUMBER = 4;  // number of choices for each question
   
   public static void main(String[] args) {
      Scanner console = new Scanner(System.in);
      MemoryGameImage m = new MemoryGameImage();
      System.out.println("Choose level: A. easier  B. harder");
      String level = console.next();
      System.out.println("You have 10 seconds");
      m.drawText(10000);
      m.drawHouse1();  // the basic house image
      if (level.equalsIgnoreCase("B")) { 
         m.drawHouse2();  // adds the tree and smoke
      } 
      m.erase(10000);  // erases image after 10s
      boolean done = false;
      Iterator itr = m.getSet().iterator();
      int count = 0;
      int countCorrect = 0;
      while (!done && itr.hasNext()) {
         count++;
         String target = itr.next().toString();
         System.out.println();
         System.out.println("What was the color of the " + target + "? Choose from: ");
         String correct = m.getAnswer(target);
         
         // randomly builds up choices including the correct one
         Set<String> set= new HashSet<String>();
         set.add(correct);
         while (set.size() < CHOICE_NUMBER) {
            set.add(m.getColor());
         }
         for (String choice: set) {
            System.out.print(choice + "  ");
         }
         System.out.println();
         
         // gets the answer and compares it with the correct answer
         System.out.println("Answer: ");
         String answer = console.next();
         if (answer.equalsIgnoreCase(correct)) {
            System.out.println("Correct!");
            countCorrect++;
         } else {
            System.out.println("Nope! The answer is " + correct);
         }
         // below is a little weird...the boolean has no meaning but helps to let users put
         // enter key to continue; however, I haven't figured out how to let them quit
         System.out.print("next(press enter key) "); 
         if (console.nextLine().length() != 0) {
            done = true;
         }
         console.nextLine();
      }
      System.out.println();
      System.out.println("You got " + countCorrect + " out of " + count + "!"); 
      if ((double) countCorrect / count > 0.5) {
         System.out.println("Good job!");
      } else {
         System.out.println("Try harder!");
      }
      
      // asks to show the image
      System.out.print("Show the image? (Y/N) ");
      String answer = console.next();
      if (answer.equalsIgnoreCase("Y")) {
         m.regain();
      }
   }
}