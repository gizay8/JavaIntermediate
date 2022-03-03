//Thread usage example
import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args) {
        ArrayList<Integer> allNumbers = new ArrayList<>();
        for(int i = 1 ; i <= 10000 ; i++){
            allNumbers.add(i);
        }
        
        ArrayList<Integer> list0 = new ArrayList<Integer>();
        ArrayList<Integer> list1 = new ArrayList<Integer>();
        ArrayList<Integer> list2 = new ArrayList<Integer>();
        ArrayList<Integer> list3 = new ArrayList<Integer>();
        for (int i = 1; i <= 10000; i++) {
            if (i <= 2500) {
                list0.add(i);
            } else if (i <= 5000) {
                list1.add(i);
            } else if (i <= 7500) {
                list2.add(i);
            } else {
                list3.add(i);
            }
        }
        
        Numbers numbers0 = new Numbers(list0);
        Numbers numbers1 = new Numbers(list1);
        Numbers numbers2 = new Numbers(list2);
        Numbers numbers3 = new Numbers(list3);
        
        Thread thread0 = new Thread(numbers0);
        Thread thread1 = new Thread(numbers1);
        Thread thread2 = new Thread(numbers2);
        Thread thread3 = new Thread(numbers3);
        
        thread0.start();
        thread1.start();
        thread2.start();
        thread3.start();
    }
}