package fr.uge.reddit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class IndexController {
    public static class Message {
        private String author;
        private String body;
        private int score;
        private List<Message> replies;

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

        public Message(String author, String body, int score, List<Message> replies) {
            this.author = author;
            this.body = body;
            this.score = score;
            this.replies = replies;
        }
    }

    @ModelAttribute("message")
    public Message message() {
        return new Message("toto", "hello world", 4,
                List.of(
                        new Message("titi", "bonjour", 0, null),
                        new Message("tutu", "aaaaaaaaaaa", -2, null)
                )
        );
    }

    @GetMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("message", message());
        return "index";
    }
}
