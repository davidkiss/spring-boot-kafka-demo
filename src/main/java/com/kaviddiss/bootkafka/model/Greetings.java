package com.kaviddiss.bootkafka.model;

// lombok autogenerates getters, setters, toString() and a builder (see https://projectlombok.org/):
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @Builder
public class Greetings {
    private String message;
}
