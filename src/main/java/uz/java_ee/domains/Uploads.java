package uz.java_ee.domains;

import jakarta.persistence.*;
import lombok.*;
import uz.java_ee.dto.UploadsDTO;

/**
 * @author "Elmurodov Javohir"
 * @since 26/07/22/15:10 (Tuesday)
 * java-ee/IntelliJ IDEA
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class Uploads {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originalName;
    private String generatedName;
    private String contentType;
    private long size;

    @Column(columnDefinition = "boolean default false")
    private boolean template;

    public static Uploads toDomain(UploadsDTO dto) {
        return Uploads.builder()
                .originalName(dto.getOriginalName())
                .generatedName(dto.getGeneratedName())
                .contentType(dto.getContentType())
                .size(dto.getSize())
                .build();
    }
}
