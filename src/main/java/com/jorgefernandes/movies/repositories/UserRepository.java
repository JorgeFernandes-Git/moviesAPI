package com.jorgefernandes.movies.repositories;

import com.jorgefernandes.movies.domain.user.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository  extends MongoRepository<User, ObjectId> {
    User findByNickname(String nickname);
}
