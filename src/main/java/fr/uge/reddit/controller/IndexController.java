package fr.uge.reddit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class IndexController {
    // TODO: Replace by actual Message class / DTO
    public static class Message {
        private Long id;
        private String author;
        private String body;
        private int score;
        private List<Message> replies;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public List<Message> getReplies() {
            return replies;
        }

        public void setReplies(List<Message> replies) {
            this.replies = replies;
        }

        public Message() {
        }

        public Message(Long id, String author, String body, int score, List<Message> replies) {
            this.id = id;
            this.author = author;
            this.body = body;
            this.score = score;
            this.replies = replies;
        }
    }

    // TODO: Replace by actual User class / DTO
    public static class User {
        private String login;

        public User() {
        }

        public User(String login) {
            this.login = login;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }
    }

    @ModelAttribute("message")
    public Message message() {
        return new Message(0L, "toto", "hello world", 4,
                List.of(
                        new Message(1L, "titi", "bonjour", 0, null),
                        new Message(2L, "tutu", "aaaaaaaaaaa", -2, null)
                )
        );
    }

    @GetMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("message", message());
        return "redirect:/popular";
    }

    @GetMapping("/u/{login}")
    public String getUserPage(@PathVariable String login, Model model) {
        // TODO: Actually fetch from the database
        // TODO: Sort, filter, page, etc.
        model.addAttribute("messages", List.of(message(), message(), message()));
        model.addAttribute("user", new User(login));
        return "user";
    }
}
