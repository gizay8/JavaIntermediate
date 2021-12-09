//MyList sınıfını test etmek için kullanılabilir örnekler
public class Main {

	public static void main(String[] args) {	
        MyList<Integer> list1 = new MyList<>();
        System.out.println("Dizideki Eleman Sayısı : " + list1.size());
        System.out.println("Dizinin Kapasitesi : " + list1.getCapacity());
        list1.add(10);
        list1.add(20);
        list1.add(30);
        list1.add(40);
        System.out.println("Dizideki Eleman Sayısı : " + list1.size());
        System.out.println("Dizinin Kapasitesi : " + list1.getCapacity());
        list1.add(50);
        list1.add(60);
        list1.add(70);
        list1.add(80);
        list1.add(90);
        list1.add(100);
        list1.add(110);
        System.out.println("Dizideki Eleman Sayısı : " + list1.size());
        System.out.println("Dizinin Kapasitesi : " + list1.getCapacity());
        System.out.println();
        
	
	MyList<Integer> list2 = new MyList<>();
        list2.add(10);
        list2.add(20);
        list2.add(30);
        System.out.println("2. indisteki değer : " + list2.get(2));
        list2.remove(2);
        list2.add(40);
        list2.set(0, 100);
        System.out.println("2. indisteki değer : " + list2.get(2));
        System.out.println(list2.toString());		
	System.out.println();
        
	MyList<Integer> list3 = new MyList<>();
        System.out.println("Liste Durumu : " + (list3.isEmpty() ? "Boş" : "Dolu"));
        list3.add(10);
        list3.add(20);
        list3.add(30);
        list3.add(40);
        list3.add(20);
        list3.add(50);
        list3.add(60);
        list3.add(70);

        System.out.println("Liste Durumu : " + (list3.isEmpty() ? "Boş" : "Dolu"));

        // Bulduğu ilk indeksi verir
        System.out.println("Indeks : " + list3.indexOf(20));

        // Bulamazsa -1 döndürür
        System.out.println("Indeks :" + list3.indexOf(100));

        // Bulduğu son indeksi verir
        System.out.println("Indeks : " + list3.lastIndexOf(20));

        // Listeyi Object[] dizisi olarak geri verir.
        Object[] array = list3.toArray();
        System.out.println("Object dizisinin ilk elemanı :" + array[0]);

        // Liste veri türünde alt bir liste oluşturdu
        MyList<Integer> subList3 = list3.subList(0, 3);
        System.out.println(subList3.toString());

        // Değerim listedeki olup olmadığını sorguladı
        System.out.println("Listemde 20 değeri : " + list3.contains(20));
        System.out.println("Listemde 120 değeri : " + list3.contains(120));

        // Listeyi tamamen boşaltır ve varsayılan boyutuna çevirir
        list3.clear();
        System.out.println(list3.toString());	
	}

}
