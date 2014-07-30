package com.madhouse.app;

class MutliThread extends Thread
{
    private String name;
//    private int ticket=100;//每个线程都拥有100张票
    //MutliThread
    public   MutliThread(String name) {
        this.name = name;
    }

    public void run() {
        for (int i = 0; i < 500; i++) {
            System.out.println(name + "运行     " + i);
        }
    }
    //public static void main(String[] args) {
        //MutliThread h1=new MutliThread("A");
        //MutliThread h2=new MutliThread("B");
        //h1.start();
        //h2.start();
    //}
}
