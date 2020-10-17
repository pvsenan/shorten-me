package se.pscode.shortenme.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class UrlData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer urlId;
    private String originalUrl;
    private Date createdDate;
}
