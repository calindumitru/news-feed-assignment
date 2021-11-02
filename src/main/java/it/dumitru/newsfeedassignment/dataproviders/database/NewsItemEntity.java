package it.dumitru.newsfeedassignment.dataproviders.database;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.ZonedDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(exclude = "description")
public class NewsItemEntity {

    @Id
    private String guid;
    @Column
    private String title;
    @Column(length = 5000)
    private String description;
    @Column
    private ZonedDateTime publicationDate;
    @Column
    private String image;
    @Column
    private String link;
}
