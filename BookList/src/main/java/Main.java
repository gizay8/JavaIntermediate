/*This program sorts books by creating a Map<String, String> 
so that "the author name" is opposite "the book name". 
In addition, it filters books with more than 100 pages 
and outputs them as a new list 
using the Stream and Lambda expressions.
*/
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        ArrayList<Book> book = new ArrayList<>();
        
        Map<String, String>  nameAndAuthor = new HashMap<>();
        
        book.add(new Book("The Metamorphosis", "Franz Kafka", 80, 1915));
        book.add(new Book("1984", "George Orwell", 352, 1949));
        book.add(new Book("The Picture of Dorian Gray", "Oscar Wilde", 279, 1890));
        book.add(new Book("A Tale of Two Cities", "Charles Dickens", 464, 1859));
        book.add(new Book("Emma", "Jane Austen", 468, 1815));
        book.add(new Book("Jane Eyre", "Charlotte Brontë", 632, 1847));
        book.add(new Book("The Age of Innocence", "Edith Wharton", 331, 1920));
        book.add(new Book("Mrs. Dalloway", "Virginia Wolf", 192, 1925));
        book.add(new Book("Anna Karenina", "Leo Tolstoy", 864, 1878));
        book.add(new Book("The Yellow Wallpaper", "Charlotte Perkins Gilman", 72, 1892));
        
        System.out.println("Book Name - Author Name");
        System.out.println("-----------------------");
        
        book.forEach(b -> {nameAndAuthor.put(b.getName(),b.getAuthor());} );
        for(String key : nameAndAuthor.keySet()) {
            System.out.println(key + " - " + nameAndAuthor.get(key));
        }
        
        System.out.println("=================================================");
        System.out.println("=================================================");
        
        System.out.println("Books with more than 100 pages");
        System.out.println("------------------------------");
        
        ArrayList<Book> bookList = new ArrayList<>();
        book.stream().filter(books -> books.getTotalPages() > 100).forEach(i -> bookList.add(i));
        bookList.stream().forEach(j -> System.out.println(j.toString()));
        
    }
    
}
