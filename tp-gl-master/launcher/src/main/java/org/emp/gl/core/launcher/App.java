package org.emp.gl.core.launcher;

import org.emp.gl.clients.Horloge ;
import org.emp.gl.timer.service.TimerService;
import org.emp.gl.time.service.impl.DummyTimeServiceImpl;

/**
 * Hello JAVA!
 *
 */
public class App {

    public static void main(String[] args) {
        clearScreen();
        System.out.println("*** RESULTATS TP1 - Design Pattern Observer ***\n");
        testDuTimeService();
    }

    private static void testDuTimeService() {
        TimerService timerService = new DummyTimeServiceImpl();

        Horloge had1 = new Horloge("Horloge1", timerService,10);
        Horloge had2 = new Horloge("Horloge2", timerService,10);
        Horloge had3 = new Horloge("Horloge3", timerService,10);
        //etape 1 10s
        try {
            Thread.sleep(10000); // 10 secondes
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //atape 2 had2 desinscrir 
        had2.arreter();
        try {
            Thread.sleep(10000); // 10 secondes supplémentaires
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //etape3 ajout de had4
         Horloge had44 = new Horloge("Horloge4", timerService,10);
        
        try {
            Thread.sleep(5000); // 5 secondes supplémentaires
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
