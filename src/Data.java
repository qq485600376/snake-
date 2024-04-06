package hspedu.snake;

import javax.swing.*;
import java.net.URL;

//数据中心
public class Data {
    public static URL headerURL = Data.class.getResource("img/header4.png");
    public static ImageIcon header = new ImageIcon(headerURL);

    public static URL upURL = Data.class.getResource("img/h3.png");
    public static URL downURL = Data.class.getResource("img/h1.png");
    public static URL leftURL = Data.class.getResource("img/h4.png");
    public static URL rightURL = Data.class.getResource("img/h2.png");
    public static ImageIcon up = new ImageIcon(upURL);
    public static ImageIcon down = new ImageIcon(downURL);
    public static ImageIcon left = new ImageIcon(leftURL);
    public static ImageIcon right = new ImageIcon(rightURL);
    public static URL bodyURL = Data.class.getResource("img/b.png");
    public static ImageIcon body = new ImageIcon(bodyURL);
    public static URL foodURL = Data.class.getResource("img/food.png");
    public static ImageIcon food = new ImageIcon(foodURL);
    public static URL food1URL = Data.class.getResource("img/food1.png");
    public static ImageIcon food1= new ImageIcon(food1URL);
    public static URL food2URL = Data.class.getResource("img/f1.png");
    public static ImageIcon food2= new ImageIcon(food2URL);
    public static URL food3URL = Data.class.getResource("img/f2.png");
    public static ImageIcon food3= new ImageIcon(food3URL);
    public static URL food4URL = Data.class.getResource("img/f3.png");
    public static ImageIcon food4= new ImageIcon(food4URL);
    public static URL backimg1URL = Data.class.getResource("img/backimg1.png");
    public static ImageIcon backimg1= new ImageIcon(backimg1URL);
    public static URL backimg2URL = Data.class.getResource("img/bakimg2.png");
    public static ImageIcon backimg2= new ImageIcon(backimg2URL);
}
