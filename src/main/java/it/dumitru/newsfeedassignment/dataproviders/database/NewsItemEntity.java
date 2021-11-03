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
@ToString(exclude = "description")//todo remove
public class NewsItemEntity {

    @Id
    @Column(nullable = false)
    private String guid;
    @Column(nullable = false)
    private String title;
    @Column(length = 10000)
    private String description;
    @Column(nullable = false)
    private ZonedDateTime publicationDate;
    @Column
    private String image;
    @Column
    private String link;
}
