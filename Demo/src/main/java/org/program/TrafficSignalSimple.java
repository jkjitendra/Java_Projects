package org.program;

public class TrafficSignalSimple implements Runnable{

    public static void main(String[] args) {
        TrafficSignalSimple trafficSignalSimple = new TrafficSignalSimple();
        Thread thread = new Thread(trafficSignalSimple);
        thread.start();
    }

    @Override
    public void run() {
        while(true) {
            try {
                System.out.println("YELLOW");
                Thread.sleep(3000);
                System.out.println("RED");
                Thread.sleep(3000);
                System.out.println("GREEN");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
