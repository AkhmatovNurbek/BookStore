package uz.jl.java_ee.domains;

import jakarta.persistence.*;
import lombok.*;
import uz.jl.java_ee.dto.BookCreateDTO;

/**
 * @author "Elmurodov Javohir"
 * @since 26/07/22/10:27 (Tuesday)
 * java-ee/IntelliJ IDEA
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String author;
    private int pageCount;
    private Genre genre;
    private Language language;

    @OneToOne
    private Uploads file;

    @OneToOne
    private Uploads cover;

    public static Book toDomain(BookCreateDTO dto) {
        return Book.builder()
                .name(dto.getName())
                .author(dto.getAuthor())
                .genre(Genre.findGenreByName(dto.getGenre()))
                .language(Language.findByLanguageName(dto.getLanguage()))
                .pageCount(Integer.parseInt(dto.getPageCount()))
                .file(dto.getFile())
                .cover(dto.getCover())
                .build();
    }


    public enum Language {
        UZ, RU, EN;

        public static Language findByLanguageName(String languageName) {
            for (Language language : values()) {
                if (language.name().equalsIgnoreCase(languageName)) {
                    return language;
                }
            }
            return RU;
        }
    }

    public enum Genre {
        HORROR,
        ROMANCE,
        DRAMA,
        ROMANCE_DRAMA,
        SUPER_NATURAL_HORROR,
        SCI_FI,
        UNKNOWN;

        public static Genre findGenreByName(String genreName) {
            for (Genre genre : values()) {
                if (genre.name().equalsIgnoreCase(genreName)) {
                    return genre;
                }
            }
            return UNKNOWN;
        }
    }
}
