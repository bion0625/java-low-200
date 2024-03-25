package mySample;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

//마우스 매크로
public class MouseMoving {
    public static void main(String[] args) {
        // x, y 좌표 + 초 (ex 3000 == 3초)
        mouseLocation(770, 440, 60000);
    }

    public static int tCnt = 0; // 마우스 건드린 시점 트래킹

    public static void mouseMoveMecro(int x, int y) {
        try{
            Robot robot = new Robot();
            robot.mouseMove(x, y);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        }catch(AWTException e){
            e.printStackTrace();
        }
    }

    public static void mouseLocation(int x, int y, int p) {
        Timer t = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                PointerInfo pt = MouseInfo.getPointerInfo();
                Point point = pt.getLocation();
                System.out.printf("[%d] %s\n", tCnt, point);
                if (point.getX() == x && point.getY() == y) {
                    tCnt++;
                }else{
                    tCnt = 0;
                    System.out.printf("\t<%s>\n\n", new Date().toString().substring(11, 19));
                }

                mouseMoveMecro(x, y);
            }
        };
        t.schedule(timerTask, 0, p);
    }
}
