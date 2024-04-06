package hspedu.snake;

import javax.swing.*;



public class StartGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("贪吃蛇");

        frame.setBounds(10, 10, 950, 750);
        frame.setResizable(false);//窗口大小不可变


        //单创界面
        frame.add(new GamePanel());

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
