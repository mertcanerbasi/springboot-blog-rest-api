package com.springboot.blog.repository;

import com.springboot.blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//We used Long because Post models id was long
//Also we dont need to add @Repository because JpaRepository already contains it in itself
public interface PostRepository extends JpaRepository<Post,Long> {

}
