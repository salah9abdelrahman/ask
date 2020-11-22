package com.salah.ask.repository;

import com.salah.ask.model.ask.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
