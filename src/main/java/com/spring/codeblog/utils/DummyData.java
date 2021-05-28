package com.spring.codeblog.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.spring.codeblog.model.Post;
import com.spring.codeblog.repository.CodeblogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DummyData {

  @Autowired
  CodeblogRepository codeblogRepository;

  // @PostConstruct
  public void savePosts() {
    List<Post> postList = new ArrayList<>();
    Post post1 = new Post();
    post1.setAutor("Nathan Oliveira");
    post1.setDate(LocalDate.now());
    post1.setTitulo("Docker");
    post1.setTexto(
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");

    Post post2 = new Post();
    post2.setAutor("Nathan Oliveira");
    post2.setDate(LocalDate.now());
    post2.setTitulo("API REST");
    post2.setTexto(
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");

    postList.add(post1);
    postList.add(post2);

    for (Post post : postList) {
      Post postSaved = codeblogRepository.save(post);
      System.out.println(postSaved.getId());
    }

  }
}