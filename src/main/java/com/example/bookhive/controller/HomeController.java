package com.example.bookhive.controller;

import com.example.bookhive.model.Book;
import com.example.bookhive.repository.BookRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class HomeController {
    private final BookRepository bookRepository;

    public HomeController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "home";
    }

    @GetMapping("/search")
    public String search(@RequestParam String keyword, Model model) {
        model.addAttribute("books", bookRepository.findByTitleContainingIgnoreCase(keyword));
        model.addAttribute("keyword", keyword);
        return "search";
    }

    @GetMapping("/book/{id}")
    public String bookDetails(@PathVariable Long id, Model model) {
        Book book = bookRepository.findById(id).orElseThrow();
        model.addAttribute("book", book);
        return "book";
    }

    @PostMapping("/cart/add/{id}")
    public String addToCart(@PathVariable Long id, HttpSession session) {
        List<Long> cart = (List<Long>) session.getAttribute("cart");
        if (cart == null) cart = new ArrayList<>();
        cart.add(id);
        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String cart(HttpSession session, Model model) {
        List<Long> cart = (List<Long>) session.getAttribute("cart");
        if (cart == null) cart = new ArrayList<>();
        List<Book> books = bookRepository.findAllById(cart);
        model.addAttribute("books", books);
        model.addAttribute("total", books.stream().mapToDouble(b -> b.getPrice()).sum());
        return "cart";
    }

    @GetMapping("/checkout")
    public String checkout() {
        return "checkout";
    }
}
