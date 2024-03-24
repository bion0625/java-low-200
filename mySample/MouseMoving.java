package mySample;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.Timer;
import java.util.TimerTask;

//마우스 매크로
public class MouseMoving {
    public static void main(String[] args) {
        // x, y 좌표 + 초 (ex 3000 == 3초)
        mouseLocation(770, 440, 3000);
    }

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
                System.out.println(pt.getLocation());

                mouseMoveMecro(x, y);
            }
        };
        t.schedule(timerTask, 0, p);
    }
}
