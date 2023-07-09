package com.chat.messaging.repository;

import com.chat.messaging.models.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

	@Query("SELECT c FROM ChatRoom c JOIN c.participants p WHERE p.userId = :userId")
	List<ChatRoom> findByUserId(@Param("userId") String userId);

}
