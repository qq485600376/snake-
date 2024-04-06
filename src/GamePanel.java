package hspedu.snake;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Random;
import java.io.File;




//ActionListener 监听按钮点击事件
//KeyListener 接收键盘事件(击键)的侦听器接口
public class GamePanel extends JPanel implements KeyListener, ActionListener {

    int time1=100;
    int score=0;
    int length;
    String dir;
    int[] snakeX = new int[850];//蛇的X坐标
    int[] snakeY = new int[850];//蛇的Y坐标
    Random random = new Random();
    int foodX,foodX1,foodX2,foodX3,foodX4;
    int foodY,foodY1,foodY2,foodY3,foodY4;

    boolean isStart = false;
    boolean isFail = false; //游戏是否失败，初始化为未失败

    Timer timer = new Timer(time1,this);//监听当前对象，100ms刷新一次

    public void init(){
        length = 3;
        snakeX[0] = 100;snakeY[0] = 200;//脑袋的坐标
        snakeX[1] = 70;snakeY[1] = 200;
        snakeX[2] = 40;snakeY[2] = 200;
        dir = "R"; //初始方向向右

        foodX = 40 + 30*random.nextInt(33);
        foodY = 80 + 30*random.nextInt(23);
        foodX1 = 40 + 30*random.nextInt(30);
        foodY1 = 80 + 30*random.nextInt(20);
        foodX2 = 40 + 30*random.nextInt(30);
        foodY2 = 80 + 30*random.nextInt(20);
        foodX3 = 40 + 30*random.nextInt(30);
        foodY3 = 80 + 30*random.nextInt(20);
        foodX4 = 40 + 30*random.nextInt(30);
        foodY4 = 80 + 30*random.nextInt(20);
        score = 0;
    }
    JButton startButton;
    JButton stopButton;//按钮
    long startTime; //开始
    long endTime;
    Timer gameTimer; // 计时器
    JLabel timeLabel; // 标签

    public GamePanel(){
        init();

        this.setFocusable(true); //获得焦点事件
        this.addKeyListener(this);
        playMusic("C:\\Users\\music.wav");
        timer.start();

        setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10)); //按钮


        startButton = new JButton("开始游戏");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isStart = true;
                isFail = false;
                repaint();
            }
        });
        stopButton = new JButton("停止游戏");
        stopButton.addActionListener(new ActionListener() {
            @Override  // 父类重写。
            public void actionPerformed(ActionEvent e) {
                isStart = false;
                isFail = false;
                repaint();
            }
        });

        timeLabel = new JLabel("游戏时间: 0秒");
        add(timeLabel);

        gameTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long currentTime = System.currentTimeMillis();
                long timePlayed = (currentTime - startTime) / 1000;
                timeLabel.setText("游戏时间: " + timePlayed + "秒"); // 更新时间
            }
        });

        add(startButton);
        add(stopButton);
    }


    public void playMusic(String filePath) {
        try {
            File musicFile = new File(filePath);
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException  | javax.sound.sampled.LineUnavailableException e) {
            e.printStackTrace();
        }
    }


    //绘制面板
    //Swing组件使用paintComponent方法绘制图形
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Data.backimg2.paintIcon(this,g,25,90);
        if (!isStart && !isFail) {
            startButton.setVisible(true);
            stopButton.setVisible(false);
        } else {
            startButton.setVisible(false);
            stopButton.setVisible(true);
        }

        this.setBackground(Color.WHITE);
        Data.header.paintIcon(this,g,25,10);
        //画游戏界面
        g.fillRect(25,90,850,750);
        Data.backimg1.paintIcon(this,g,25,90);

        g.setColor(Color.red);
        g.setFont(new Font("微软雅黑",Font.BOLD,18));
        g.drawString("长度"+length,650,35);
        g.drawString("分数"+score,650,70);

        Data.food.paintIcon(this,g,foodX,foodY);

        Data.food1.paintIcon(this,g,foodX1,foodY1);
        Data.food2.paintIcon(this,g,foodX2,foodY2);
        Data.food3.paintIcon(this,g,foodX3,foodY3);
        Data.food4.paintIcon(this,g,foodX4,foodY4);

        //游戏状态
        if (!isStart)
        {
            g.setColor(Color.white);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            super.paintComponent(g);
            Data.backimg2.paintIcon(this,g,25,90);
            g.drawString("按下空格或者按钮 开始游戏",300,300);

        }
        if (isFail)
        {
            g.setColor(Color.red);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            super.paintComponent(g);
            Data.backimg2.paintIcon(this,g,25,90);
            g.drawString("失败！！！按下空格或者按钮 重新开始游戏",200,300);
        }

        //把小蛇画上去
        switch (dir) {
            case "R":
                Data.right.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case "L":
                Data.left.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case "U":
                Data.up.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case "D":
                Data.down.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
        }
        for (int i = 1; i < length; i++) {
            Data.body.paintIcon(this,g,snakeX[i],snakeY[i]);
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE) {
            if (isFail) {
                //重新开始
                isFail = false;
                init();
            } else{
                isStart = !isStart;
            }
            repaint();
        }
        //小蛇移动
        switch (keyCode) {
            case KeyEvent.VK_UP:
                dir = "U";
                break;
            case KeyEvent.VK_DOWN:
                dir = "D";
                break;
            case KeyEvent.VK_LEFT:
                dir = "L";
                break;
            case KeyEvent.VK_RIGHT:
                dir = "R";
                break;
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyReleased(KeyEvent e) {

    }

    //实现事件监听
    @Override
    public void actionPerformed(ActionEvent e) {

        if (isStart && !isFail && startTime == 0) {
            startTime = System.currentTimeMillis();
            gameTimer.start(); // 启动游戏计时器
        }

        // 如果游戏结束，记录游戏结束时间并停止游戏计时器
        if (!isStart && endTime == 0) {
            endTime = System.currentTimeMillis();
            gameTimer.stop();
            long gameDuration = (endTime - startTime) / 1000;
            timeLabel.setText("游戏持续时间: " + gameDuration + "秒");
        }
        //开始，小蛇动
        if (isStart && !isFail)
        {
            {
                if(score<50) time1=100;
                else if(score>50&&score<100) time1=80;
                else if(score>100&&score<200) time1=70;
                else if(score>300) time1=50;
            }
            //吃食物
            if(snakeX[0] == foodX && snakeY[0] == foodY ||snakeX[0] == foodX2 && snakeY[0] == foodY2||
                    snakeX[0] == foodX3 && snakeY[0] == foodY3||snakeX[0] == foodX4 && snakeY[0] == foodY4)
            {
                length++;
                score+=10;
                foodX = 40 + 30*random.nextInt(33);
                foodY = 80 + 30*random.nextInt(23);
                foodX2 = 40 + 30*random.nextInt(30);
                foodY2 = 80 + 30*random.nextInt(20);
                foodX3 = 40 + 30*random.nextInt(30);
                foodY3 = 80 + 30*random.nextInt(20);
                foodX4 = 40 + 30*random.nextInt(30);
                foodY4 = 80 + 30*random.nextInt(20);
            }
            else if(snakeX[0] == foodX1 && snakeY[0] == foodY1){
                length+=3;
                score+=30;
                foodX1 = 40 + 30*random.nextInt(30);
                foodY1 = 80 + 30*random.nextInt(20);
            }

            //右移
            for (int i = length - 1; i > 0; i--) {
                snakeX[i] = snakeX[i-1];
                snakeY[i] = snakeY[i-1];
            }
            //脑袋如何移动
            switch (dir) {
                case "U":
                    snakeY[0] = snakeY[0] - 30;
                    break;
                case "D":
                    snakeY[0] = snakeY[0] + 30;
                    break;
                case "L":
                    snakeX[0] = snakeX[0] - 30;
                    break;
                case "R":
                    snakeX[0] = snakeX[0] + 30;
                    break;
            }

            //边界判断
            if(snakeX[0] > 850)
                snakeX[0] = 30;
            if(snakeX[0] < 30)
                snakeX[0] = 850;
            if(snakeY[0] > 750)
                snakeY[0] = 90;
            if(snakeY[0] < 90)
                snakeY[0] = 750;

            //撞到自己就失败
            for (int i = 1; i < length; i++) {
                if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i])
                    isFail = true;
            }
            repaint();//重画页面
        }
        {
            if(score<50) time1=100;
            else if(score>50&&score<100) time1=80;
            else if(score>100&&score<200) time1=70;
            else if(score>300) time1=50;
        }
        timer.start();
    }
}

//381