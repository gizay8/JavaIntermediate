public class Book {
   private String name;
   private int totalPages;
   private String authorName;
   private int publishYear;
   
   public Book(String name, int totalPages,String authorName, int publishYear){
       this.name = name;
       this.totalPages = totalPages;
       this.authorName = authorName;
       this.publishYear = publishYear;
   }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }
}
