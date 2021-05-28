package com.spring.codeblog.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import com.spring.codeblog.model.Post;
import com.spring.codeblog.service.CodeblogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CodeblogController {

  @Autowired
  CodeblogService codeblogService;

  // return both model and view
  @RequestMapping(value = "/posts", method = RequestMethod.GET)
  public ModelAndView getPosts() {
    // view
    ModelAndView mv = new ModelAndView("posts");
    List<Post> posts = codeblogService.findAll();
    mv.addObject("posts", posts);
    return mv;
  }

  // posts details
  @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
  public ModelAndView getPostDetails(@PathVariable("id") long id) {
    ModelAndView mv = new ModelAndView("postDetails");
    Post post = codeblogService.findById(id);
    mv.addObject("post", post);
    return mv;
  }

  // create page for new post
  @RequestMapping(value = "/newpost", method = RequestMethod.GET)
  public String getPostForm() {
    return "postForm";
  }

  // method for save posts
  @RequestMapping(value = "/newpost", method = RequestMethod.POST)
  public String savePost(@Valid Post post, BindingResult result, RedirectAttributes attributes) {
    // if any fields blank or null
    if (result.hasErrors()) {
      attributes.addFlashAttribute("message", "Verifique se os campos obrigatórios foram preenchidos!");
      return "redirect:/newpost";
    }
    post.setDate(LocalDate.now());
    codeblogService.save(post);
    return "redirect:/posts";
  }

}
