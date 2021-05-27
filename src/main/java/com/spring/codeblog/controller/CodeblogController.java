package com.spring.codeblog.controller;

import java.util.List;

import com.spring.codeblog.model.Post;
import com.spring.codeblog.service.CodeblogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CodeblogController {
  
  @Autowired
  CodeblogService codeblogService;

  //return both model and view
  @RequestMapping(value = "/posts", method = RequestMethod.GET)
  public ModelAndView getPosts() {
    //view
    ModelAndView mv = new ModelAndView("posts");
    List<Post> posts = codeblogService.findAll();
    mv.addObject("posts", posts);
    return mv;
  }
}
