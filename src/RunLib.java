class Book {
    private String title;
    private String author;
    private int yearPublished;

    public Book(String title, String author, int yearPublished) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
    }

    public String getTitle() {
        return title;
    }

    public String getBookInfo() {
        return title + ", " + author + ", " + yearPublished;
    }

    public String printBookDetails() {
        return "Title: " + title + ", \nAuthor: " + author + ", \nYear published: " + yearPublished;
    }
}

class PrintedBook extends Book {
    private int numberOfPages;
    private String publisher;
    private double price;

    public PrintedBook(String title, String author, int yearPublished, int numberOfPages, String publisher, double price) {
        super(title, author, yearPublished);
        this.numberOfPages = numberOfPages;
        this.publisher = publisher;
        this.price = numberOfPages * price;
    }

    public String getBookInfo() {
        return super.getBookInfo() + ", " + numberOfPages + ", " + publisher + ", " + price;
    }

    public String printBookDetails() {
        return "Printed book details: \n" + super.printBookDetails() + ", \nNumber of pages: " + numberOfPages + ", \nPublisher: " + publisher + ", \nPrice: " + price;
    }

    public void bookType() {
        System.out.println("Book type: \n" + "Printed book");
    }

    public String printBook() {
        return "Printing the " + super.getTitle();
    }

}

class EBook extends Book {
    private double fileSizeMB;
    private String fileFormat;
    private double price;

    public EBook(String title, String author, int yearPublished, double fileSizeMB, String fileFormat, double price) {
        super(title, author, yearPublished);
        this.fileSizeMB = fileSizeMB;
        this.fileFormat = fileFormat;
        this.price = fileSizeMB * price;
    }

    public String getBookInfo() {
        return super.getBookInfo() + ", " + fileSizeMB + ", " + fileFormat + ", " + price;
    }

    public String printBookDetails() {
        return "Ebook details: \n" + super.printBookDetails() + ", \nFile size in mb: " + fileSizeMB + ", \nFile format: " + fileFormat + ", \nPrice: " + price;
    }

    public void bookType() {
        System.out.println("Book type: \n" + "Ebook");
    }

}

public class RunLib {
    public static void main(String[] args) {
        PrintedBook pb = new PrintedBook("Prntbk", "me", 1984, 216, "IntelliJ IDEA", 0.5);

        EBook eb = new EBook("Ebk", "me", 1984, 3.4, "docx", 30);

        System.out.println(pb.getBookInfo());
        System.out.println(pb.printBookDetails());
        pb.bookType();
        System.out.println(pb.printBook());
        System.out.println();

        System.out.println(eb.getBookInfo());
        System.out.println(eb.printBookDetails());
        eb.bookType();
    }
}


/*
Здравстуйте, Мунара эже. А что вы имели ввиду под двумя конструкторами? А то я немного не понял суть этого задания
 */