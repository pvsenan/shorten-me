package se.pscode.shortenme.modal;

import lombok.Data;
import java.util.Date;

@Data
public class UrlData {
    private String urlId;
    private String originalUrl;
    private Date createdDate;
}
