// This class can be used to draw and manage the image for memory game and stores the answers 
// of the randomly used colors

import java.awt.*;
import java.util.*;

public class MemoryGameImage {
   private Random r;
   private DrawingPanel panel;
   private Graphics g;
   private Map<String, Integer> figureColor;
   
   public static final Color[] PALETTE = {Color.WHITE, Color.GRAY, Color.RED, Color.ORANGE,  
                                          Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE, 
                                          Color.PINK, new Color(160, 32, 240)};
   public static final String[] NAMES =  {"white", "gray", "red", "orange", "yellow", 
                                          "green", "cyan", "blue", "pink", "purple"};
   
   public MemoryGameImage() {
      r = new Random();
      panel = new DrawingPanel(600, 600);
      g = panel.getGraphics();
      figureColor = new HashMap<String, Integer>();
   }   
   
   // draws the starting text showing for given time
   public void drawText(int time) {
      g.setFont(new Font("Monospaced", Font.BOLD, 28));
      g.drawString("You have " + time / 1000 + " seconds!", 100, 100);
      panel.sleep(2000);
      panel.clear();
      g.drawString("Try to memorize all colors!", 100, 100);
      panel.sleep(2000);
      panel.clear();
   }
   
   // draws a house
   public void drawHouse1() {
      int ground = r.nextInt(PALETTE.length);
      figureColor.put("ground", ground);
      g.setColor(PALETTE[ground]);
      g.fillOval(-800, 400, 2200, 2200);
      g.setColor(Color.BLACK);
      g.drawOval(-800, 400, 2200, 2200);
      int chimney = r.nextInt(PALETTE.length);
      figureColor.put("chimney", chimney);
      g.setColor(PALETTE[chimney]);
      g.fillRect(350, 160, 40, 90);
      g.fillRect(340, 150, 60, 10);
      g.setColor(Color.BLACK);
      g.drawRect(350, 160, 40, 90);
      g.drawRect(340, 150, 60, 10);

      int roof = r.nextInt(PALETTE.length);
      figureColor.put("roof", roof);
      g.setColor(PALETTE[roof]);
      Polygon poly1 = new Polygon();
      poly1.addPoint(300, 170);
      poly1.addPoint(150, 300);
      poly1.addPoint(450, 300);
      g.fillPolygon(poly1);
      g.setColor(Color.BLACK);
      g.drawPolygon(poly1);
      
      int wall = r.nextInt(PALETTE.length);
      figureColor.put("wall", wall);
      g.setColor(PALETTE[wall]);
      g.fillRect(200, 300, 200, 150);
      g.setColor(Color.BLACK);
      g.drawRect(200, 300, 200, 150);
      
      int window = r.nextInt(PALETTE.length);
      figureColor.put("window", window);
      g.setColor(PALETTE[window]);
      g.fillRect(225, 325, 65, 50);
      g.setColor(Color.BLACK);
      g.drawRect(225, 325, 65, 50);
      g.drawLine(257, 325, 257, 375);
      
      int door = r.nextInt(PALETTE.length);
      figureColor.put("door", door);
      g.setColor(PALETTE[door]);
      g.fillRect(305, 365, 40, 85);
      g.setColor(Color.BLACK);
      g.drawRect(305, 365, 40, 85);
   }   
   
   // draws a tree and smokes
   public void drawHouse2() {
      int smoke = r.nextInt(PALETTE.length);
      figureColor.put("smoke", smoke);
      Color smokeColor = PALETTE[smoke];
      g.setColor(smokeColor);
      for (int i = 0; i < 3; i++) {
         g.fillOval(400 + i * 50, 100 - i * 35, 50 + i * 10, 30 + i * 5);
         g.setColor(Color.BLACK);
         g.drawOval(400 + i * 50, 100 - i * 35, 50 + i * 10, 30 + i * 5);
         g.setColor(smokeColor);
      }
      
      int treeTrunk= r.nextInt(PALETTE.length);
      figureColor.put("tree trunk", treeTrunk);
      Polygon poly2 = new Polygon();
      poly2.addPoint(120, 350);
      poly2.addPoint(100, 500);
      poly2.addPoint(140, 500);
      g.fillPolygon(poly2);
      g.setColor(Color.BLACK);
      g.drawPolygon(poly2);
      
      int treeCrown = r.nextInt(PALETTE.length);
      figureColor.put("tree crown", treeCrown);
      Color crownColor = PALETTE[treeCrown];
      g.setColor(crownColor);
      for (int i = 0; i < 3; i++) {
         Polygon poly3 = new Polygon();
         poly3.addPoint(120, 350 - i * 50);
         poly3.addPoint(30 + i * 15, 430 - i * 40);
         poly3.addPoint(210 - i * 15, 430 - i * 40);
         g.fillPolygon(poly3);
         g.setColor(Color.BLACK);
         g.drawPolygon(poly3);
         g.setColor(crownColor);
      }
   }
   
   // returns the color name of the given figure
   public String getAnswer(String figure) {
      return NAMES[figureColor.get(figure)];
   }
   
   // returns a color name
   public String getColor() {
      return NAMES[r.nextInt(NAMES.length)];
   }
   
   // hids the image after given time
   public void erase(int time) {
      panel.sleep(time);
      panel.setVisible(false);  
   }
   
   // shows the image
   public void regain() {
      panel.setVisible(true);
   }
   
   // returns a set of current used colors' names
   public Set<String> getSet() {
      return figureColor.keySet();
   }  
}