// This program can be used to read words from a file and change them to a form that their interior
// letters are scrambled.
// It can show how our cognition process behind reading, known as Typoglycemia, works.
// Besides, it can also directly generate the special case that the interior letters are reversed.
// All intro text from: https://en.wikipedia.org/wiki/Typoglycemia
// (really not well organized in style..)

import java.io.*;
import java.util.*;

public class TypoglycemiaMain {
   public static void main(String[] args) throws FileNotFoundException {
      Scanner console = new Scanner(System.in);
      
      System.out.println("Welcome to this simple explanation of Typoglycemia.");
      System.out.print("('s' to skip the intro/any key to continue) ");
      // gives intro
      if (!console.nextLine().equalsIgnoreCase("s")) {
         System.out.println();
         intro1();
         System.out.println();
         Scanner input = new Scanner(new File("example.txt"));
         
         processFile(input);
         System.out.println();
         System.out.println("Feel no trouble reading that? Amazing!");
         System.out.print("Have a look at the intended message? (y/n) ");
         String answer = console.nextLine();
         // shows the original message of the example
         if (answer.equalsIgnoreCase("y")) {
            input = new Scanner(new File("example.txt")); 
            while (input.hasNextLine()) {
               System.out.println("    " + input.nextLine());
            }
         }
         System.out.print("Continue intro? (y/n) ");
         answer = console.nextLine();
         // gives intro of reversed case
         if (answer.equalsIgnoreCase("y")) {
            System.out.println();
            intro2();
            System.out.println();
            input = new Scanner(new File("example.txt"));
            processReverse(input);
         }
      }
      System.out.println();
      System.out.println("You can make your own Typoglycemia message now!");
      
      // deals with user's texts
      boolean done = false;
      while (!done) {
         System.out.print("What is the name of the file? (return to quit) ");
         String fileName = console.nextLine();
         if (fileName.length() == 0) {
            done = true;
         } else {
            Scanner input = new Scanner(new File(fileName)); 
            
            System.out.print("Would you like it to be: a.scrambled  b.reversed? ");
            String choice = console.nextLine();
            if (choice.equals("a")) {
               processFile(input);
            } else {
               processReverse(input);
            }
         }
      }
   }
   
   // introduces general case
   public static void intro1() {
      System.out.println("Typoglycemia is a neologism about your cognitive processes behind");
      System.out.println("reading written text, that you can understand the meaning of words in");
      System.out.println("a sentence even when the interior letters of each word are scrambled.");
      System.out.println("let's look at an example:");   
   }
   
   // introduces the reversed case
   public static void intro2() {
      System.out.println("However, the following example based on the same principle from the");
      System.out.println("same message, but where all the letters are reversed rather than");
      System.out.println("randomly jumbled, is much more difficult to read:");
   }
   
   // changes intended message into "scrambled" form
   public static void processFile(Scanner input) {
      while (input.hasNextLine()) {
         String next = input.nextLine().trim();
         Typoglycemia typo = new Typoglycemia(next);
         String result = typo.reorganizeJumble();
         System.out.println("\t" + result);
      }
      System.out.println();
   }
   
   // changes the intended message into "reversed" form
   public static void processReverse(Scanner input) {
      while (input.hasNextLine()) {
         String next = input.nextLine().trim();
         Typoglycemia typo = new Typoglycemia(next);
         String result = typo.reorganizeReverse();
         System.out.println("\t" + result);
      }
      System.out.println();
   }
   
}