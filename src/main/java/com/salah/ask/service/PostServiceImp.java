package com.salah.ask.service;

import com.salah.ask.repository.PostRepository;

public class PostServiceImp implements PostService {
   private final PostRepository postRepository;

    public PostServiceImp(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


}
