package org.example.streaming.reactive.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Data // <2>
@AllArgsConstructor
@NoArgsConstructor
public class Tweets {

    @Id
    private String id_str;
    private String  text;

}


