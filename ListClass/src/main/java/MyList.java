//Generic yapısını kullanarak verilerin tutulduğu bir sınıf
import java.util.Arrays;
public class MyList <T>{
    private int capacity;
    int number = 0;

    T[] list;
    T[] replicaList;
	
    public MyList() {
	this.capacity = 10;
	this.list = (T[]) new Object[this.capacity];
	this.replicaList = (T[]) new Object[this.capacity];
    }
	
    public MyList(int capacity) {
	this.capacity = capacity;
	this.list = (T[]) new Object[this.capacity];
	this.replicaList = (T[]) new Object[this.capacity];
    }
    
    public int size() {
	int size=0;
	for(Object l : this.list) {
	    if(l != null) {
		size++;
	    }
	}
	return size;
    }
    
    public int getCapacity() {
	return this.capacity;
    }
	
    public boolean add(T data) {
	if(number == this.capacity) {
	    this.capacity *= 2;
	    this.replicaList = Arrays.copyOf(this.list, this.capacity);
	    this.list = (T[]) new Object[this.capacity]; 
	    this.list = Arrays.copyOf(this.replicaList, this.capacity);
	    this.replicaList = (T[]) new Object[this.capacity];
	}
	add(data,number);
	number++;	
	return true;	
    }
    
    private void add(T data,int i) {
	this.list[i] = data;
    }
	
    public T get(int index) {
	if(index < 0 || index >= size()) {
	    return null;
	}
        T i = this.list[index];
	return i;	
    }
    
    public void remove(int index) {
        if(index < 0 || index >= size()) {
            System.out.println("null");
	}
        else{
            for(int i = index ; i < size() ; i++) {
	    this.list[index] = this.list[index+1];	
	}
	number--;
        }	
    }

    public void set(int index,T data) {
        if(index < 0 || index >= size()) {
            System.out.println("null");
	}
        else{
            this.list[index] = data;
        }
    }
	
    public String toString() {
	String text = "";
	text += "[";
	for(int  i = 0 ; i < size() ; i++) {
	    if(this.list[i] != null) {
		if(this.list[i+1] == null) {
		    text += this.list[i];
                }
                else {
		    text += this.list[i]+",";
		}
	    }	
	}
	text += "]";
	return text;
    }
    
    public int indexOf(T data) {
	for(int i = 0 ; i < size() ; i++) {
	    if(this.list[i] == data) {
		return i;
	    }
	}
	return -1;
    }
    
    public int lastIndexOf(T data) {
	for(int i = size() ; i >= 0 ; i--) {
	    if(this.list[i] == data) {
		return i;
	    }
	}
	return -1;
    }
    
    public boolean isEmpty() {
	if(size() == 0) {
	    return true;
	}else{
            return false;
        }
    }
	
    public T[] toArray() {
	T[] array = (T[]) new Object[size()];
	for(int i = 0 ; i < size() ; i++) {
	    array[i] = this.list[i];
	}
	return array;
    }
    
    public void clear() {
	this.list = (T[]) new Object[10];
    }
	
    public MyList<T> subList(int start,int finish) {
	MyList<T> neuList = new MyList<T>();
	for(int i = start ; i <= finish ; i++) {
	    neuList.add((T) this.list[i]);
        }
	return neuList;
	}
	
    public boolean contains(T data) {
	for(int i = 0 ; i < size() ; i++) {
	    if(this.list[i] == data) {
		return true;
	    }
	}
	return false;
    }
	
}