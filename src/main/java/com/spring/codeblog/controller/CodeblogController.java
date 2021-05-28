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
    // se algum campo for null
    if (result.hasErrors()) {
      attributes.addFlashAttribute("message", "Verifique se os campos obrigatórios foram preenchidos!");
      return "redirect:/newpost";
    }
    post.setDate(LocalDate.now());
    codeblogService.save(post);
    return "redirect:/posts";
  }

  // delete post
  @RequestMapping(value = "/posts/delete/{id}", method = RequestMethod.GET)
  public String deletePost(@PathVariable("id") long id, RedirectAttributes attributes) {
    Post post = codeblogService.findById(id);
    attributes.addFlashAttribute("messageSuccess", "Post - " + post.getTitulo() + " removido com sucesso!");
    codeblogService.delete(post);
    return "redirect:/posts";
  }

  // update page
  @RequestMapping(value = "/posts/edit/{id}", method = RequestMethod.GET)
  public ModelAndView getEditPost(@PathVariable("id") long id) {
    ModelAndView mv = new ModelAndView("postForm");
    Post post = codeblogService.findById(id);
    mv.addObject("post", post);
    mv.addObject("title", "Editar post - " + post.getTitulo());
    return mv;
  }

  // update
  @RequestMapping(value = "/posts/edit/{id}", method = RequestMethod.POST)
  public ModelAndView updatePost(@PathVariable("id") long id, @Valid Post postRequest, BindingResult result) {

    ModelAndView mv = new ModelAndView("posts");
    List<Post> posts = codeblogService.findAll();

    if (result.hasErrors()) {

      mv.addObject("posts", posts);
      mv.addObject("message", "Verifique se os campos obrigatórios");

      return mv;
    }
    Post post = codeblogService.findById(id);
    post.setAutor(postRequest.getAutor());
    post.setTexto(postRequest.getTexto());
    post.setTitulo(postRequest.getTitulo());
    post.setDate(LocalDate.now());
    codeblogService.save(post);

    posts = codeblogService.findAll();
    mv.addObject("posts", posts);
    mv.addObject("messageSuccess", "post atualizado!");
    return mv;
  }
}
