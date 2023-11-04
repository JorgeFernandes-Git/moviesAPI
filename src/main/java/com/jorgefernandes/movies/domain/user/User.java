package com.jorgefernandes.movies.domain.user;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.jorgefernandes.movies.domain.review.Review;
import com.jorgefernandes.movies.dtos.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    private String name;
    private String surname;
    private Integer age;

    @Indexed(unique = true)
    private String nickname;

    @DocumentReference
    private List<Review> reviewsIds;

    public User(UserDTO data) {
        this.name = data.name();
        this.surname = data.surname();
        this.age = data.age();
        this.nickname = data.nickname();
    }
}
