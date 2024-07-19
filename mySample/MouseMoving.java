package mySample;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

// 마우스 매크로
public class MouseMoving {

    public static int tCnt = 0; // 마우스 건드린 시점 트래킹
    public static int targetX = 770;
    public static int targetY = 440;

    public static void mouseMoveMecro(int x, int y) {
        try {
            Robot robot = new Robot();
            robot.mouseMove(x, y);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public static void mouseLocation(int p) {
        Timer t = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                PointerInfo pt = MouseInfo.getPointerInfo();
                Point point = pt.getLocation();
                System.out.printf("[%d] %s\n", (tCnt * (p / 60000)), point);
                if (point.getX() == targetX && point.getY() == targetY) {
                    tCnt++;
                } else {
                    tCnt = 0;
                    System.out.printf("\t<%s>\n\n", new Date().toString().substring(11, 19));
                }

                mouseMoveMecro(targetX, targetY); // 마우스 이동 및 클릭
            }
        };
        t.schedule(timerTask, 0, p);
    }

    public static void main(String[] args) {
        // JFrame을 사용하여 사용자의 마우스 클릭 이벤트를 캡처
        JFrame frame = new JFrame();
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                targetX = e.getXOnScreen();
                targetY = e.getYOnScreen();
                System.out.printf("New target coordinates: (%d, %d)\n", targetX, targetY);
            }
        });

        // 주기적으로 마우스 위치 체크 및 이동
        mouseLocation(180000); // 3초
    }
}

/**
 * jar 파일 말기 및 실행
 * 
 * 1. 컴파일
 * javac -encoding UTF-8 .\mySample\MouseMoving.java
 * 
 * 2. jar 생성
 * jar cfe MouseMoving.jar mySample.MouseMoving mySample/*.class
 * 
 * 3. jar 실행
 * java -jar MouseMoving.jar
*/