import java.util.ArrayList;

class Numbers implements Runnable{
    ArrayList<Integer> evenNumbers  = new ArrayList<Integer>();
    ArrayList<Integer> oddNumbers  = new ArrayList<Integer>();
    ArrayList<Integer> list = new ArrayList<Integer>();

    public Numbers(ArrayList<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " started.");
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i) % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + " added " 
                        + list.get(i) + " to even numbers.");
                evenNumbers.add(list.get(i));
            }
            else {
                System.out.println(Thread.currentThread().getName() + " added " 
                        + list.get(i) + " to odd numbers.");
                oddNumbers.add(list.get(i));
            }
            }
        }
}