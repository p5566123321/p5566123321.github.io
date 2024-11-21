package com.test.bulletinboard;

import org.springframework.data.repository.CrudRepository;

import com.test.bulletinboard.Message;

import org.springframework.data.jpa.repository.JpaRepository;



// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface MessageRepository extends CrudRepository<Message, Integer> {
}