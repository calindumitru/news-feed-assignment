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
@Getter
@Setter
@ToString
public class NewsItemEntity {

    @Id
    @Column(nullable = false, length = 500)
    private String guid;
    @Column(nullable = false)
    private String title;
    @Column(length = 10000)
    private String description;
    @Column(nullable = false)
    private ZonedDateTime publicationDate;
    @Column
    private String image;
    @Column(length = 1000)
    private String link;
}
