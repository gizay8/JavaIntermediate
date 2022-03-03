public class Book {
    private String name, author;
    private int totalPages, publishYear;

    public Book(String name, String author, int totalPages, int publishYear) {
        this.name = name;
        this.author = author;
        this.totalPages = totalPages;
        this.publishYear = publishYear;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\"" + this.name + "\"" + " by " 
                + this.author + " (" + this.publishYear + ") " 
                + "Total pages : " + this.totalPages);
        return sb.toString();
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }
    
}
