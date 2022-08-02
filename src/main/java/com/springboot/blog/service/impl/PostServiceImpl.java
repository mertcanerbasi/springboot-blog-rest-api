package com.springboot.blog.service.impl;

import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.model.Post;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        //convert DTO to Entity
        Post post = mapToPost(postDto);

        //Save post to DB
        Post newPost = postRepository.save(post);

        //convert Entity to DTO Object
        PostDto postResponse = mapToDto(newPost);



        return postResponse;
    }

    @Override
    public PostResponse getAllPosts(int pageNumber,int pageSize,String sortBy,String sortDir) {

        //Sort Object

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        //Create Pageable Instance
        Pageable pageable = PageRequest.of(pageNumber,pageSize, sort);

        Page<Post> posts = postRepository.findAll(pageable);



        //get content for page object
        List<Post> listofPosts = posts.getContent();

        List<PostDto> content = listofPosts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());
        return postResponse;
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        return mapToDto(post);
    }

    @Override
    public PostDto updatePostById(PostDto postDto, long id) {
        Post oldPost = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        oldPost.setContent(postDto.getContent());
        oldPost.setDescription(postDto.getDescription());
        oldPost.setTitle(postDto.getTitle());
        Post updatedPost = postRepository.save(oldPost);
        PostDto newPost = mapToDto(updatedPost);
        return newPost;
    }

    @Override
    public void deletePostById(long id) {
        Post deletePost = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        postRepository.delete(deletePost);

    }


    private PostDto mapToDto(Post post){
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        return postDto;
    }

    private Post mapToPost(PostDto postDto){
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }
}
