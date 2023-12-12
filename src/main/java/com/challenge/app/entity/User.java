package com.challenge.app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="users")
public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;

}
