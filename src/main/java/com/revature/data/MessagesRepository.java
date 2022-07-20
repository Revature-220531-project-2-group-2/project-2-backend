package com.revature.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.Message;

@Repository
public interface MessagesRepository extends JpaRepository<Message, Integer> {

	
}
