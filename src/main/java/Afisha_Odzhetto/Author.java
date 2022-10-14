package Afisha_Odzhetto;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    public Author(String name){
        this.name=name;
    }
    @ManyToMany (cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "author_book",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> books=new ArrayList<>();

    public void addBook(Book book){
        this.books.add(book);
        book.getAuthors().add(this);
    }

    public void removeBook(Book book){
        this.books.remove(book);
        book.getAuthors().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Author)) return false;

        return id != null && id.equals(((Author) o).getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }
}