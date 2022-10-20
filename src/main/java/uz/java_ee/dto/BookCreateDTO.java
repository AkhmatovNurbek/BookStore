package uz.java_ee.dto;


import lombok.*;
import uz.java_ee.domains.Uploads;

import javax.servlet.http.HttpServletRequest;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BookCreateDTO {
    private Long id;
    private String name;
    private String author;
    private String pageCount;
    private String genre;
    private String language;
    private Uploads file;
    private Uploads cover;

    public static BookCreateDTO toDTO(HttpServletRequest request) {
        return BookCreateDTO.builder().name(request.getParameter("name"))
                .author(request.getParameter("author"))
                .pageCount(request.getParameter("pageCount"))
                .genre(request.getParameter("genre"))
                .language(request.getParameter("language"))
                .build();
    }
}
