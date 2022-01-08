//A program that sorts books by name from A to Z and by number of pages from minimum to maximum
import java.util.Comparator;
import java.util.TreeSet;
public class Main {
    
    public static void main(String[] args) {
        TreeSet<Book> book = new TreeSet<>(new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        
        book.add(new Book("Of Mice and Men", 107, "John Steinbeck", 1937));
        book.add(new Book("Interpretation of Dreams", 480, "Sigmund Freud", 1899));
        book.add(new Book("The Alchemist", 188, "Paulo Coelho", 1988));
        book.add(new Book("The Scarlet Plague", 83, "Jack London", 1912));
        book.add(new Book("Art of Loving", 125, "Erich Fromm", 1956));
        
        System.out.println("Sorting by book name :");
        for (Book books : book) {
            System.out.println(books.getName());
        }
        System.out.println("######################");
        
        TreeSet<Book> book2 = new TreeSet<>(new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getTotalPages()- o2.getTotalPages();
            }
        });
        
        book2.addAll(book);
        
        System.out.println("Sorting by number of pages :");
        for (Book books2 : book2) {
            System.out.println(books2.getName());
        }
    }
    
}
