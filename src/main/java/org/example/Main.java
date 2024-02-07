
//https://www.youtube.com/watch?v=Op1E5nXOSUQ      video link

//Deadlock:The deadlock is a situation when two or more threads try to access
//         the same object that is acquired by another thread. Since the threads
//         wait for releasing the object, the condition is known as deadlock.

package org.example;
public class Main {
    String s1="Roushan";
    String s2="Kumar";
    //Anonymous class way
    Thread t1=new Thread(){
        public void run(){
            while(true){
                synchronized (s1){//put a lock on s1
                    try {
                        System.out.println(Thread.currentThread().getName() + " locked " + s1);
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    synchronized (s2){
                        System.out.println(Thread.currentThread().getName()+ " locked " + s2);
                        System.out.println(s1+s2);
                    }
                }
            }
        }
    };

    Thread t2=new Thread(){
        public void run(){
            while(true){
                synchronized (s2){//put a lock on s2
                    System.out.println(Thread.currentThread().getName()+ " locked " +s2);
                    synchronized (s1){
                        System.out.println(Thread.currentThread().getName()+ " locked " +s1);
                        System.out.println(s1+s2);
                    }
                }
            }
        }
    };

    public static void main(String[] args) {
      Main obj=new Main();  //creating object of class main
      obj.t1.start();
      obj.t2.start();
    }
}
//Thread 1 will wait for thread 2 to release lock on s2
//Thread 2 will wait for thread 1 to release lock on s1




//code to avoid above deadlock scenario(just make small change of s1 & s2 in Thread 2)
//public class Main {
//    String s1="Roushan";
//    String s2="Kumar";
//    //Anonymous class way
//    Thread t1=new Thread(){
//        public void run(){
//            while(true){
//                synchronized (s1){//put a lock on s1
//                    try {
//                        System.out.println(Thread.currentThread().getName() + " locked " + s1);
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                    synchronized (s2){
//                        System.out.println(Thread.currentThread().getName()+ " locked " + s2);
//                        System.out.println(s1+s2);
//                    }
//                }
//            }
//        }
//    };
//
//    Thread t2=new Thread(){
//        public void run(){
//            while(true){
//                synchronized (s1){//put a lock on s1
//                    System.out.println(Thread.currentThread().getName()+ " locked " +s2);
//                    synchronized (s2){
//                        System.out.println(Thread.currentThread().getName()+ " locked " +s1);
//                        System.out.println(s1+s2);
//                    }
//                }
//            }
//        }
//    };
//
//    public static void main(String[] args) {
//        Main obj=new Main();  //creating object of class main
//        obj.t1.start();
//        obj.t2.start();
//    }
//}