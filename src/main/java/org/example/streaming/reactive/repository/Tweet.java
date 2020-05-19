package org.example.streaming.reactive.repository;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class Tweet {


    private String id_str;
    private String  text;

}


