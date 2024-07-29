package kz.dar.university.controller;

import kz.dar.university.feign.ClientClient;
import kz.dar.university.feign.PostClient;
import kz.dar.university.response.ClientResponse;
import kz.dar.university.response.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post-office")
public class PostOfficeController {

    @Autowired
    private PostClient postClient;

    @Autowired
    private ClientClient clientClient;

    @GetMapping("/check")
    public String checkPostOffice() {
        return "Post Office API is working";
    }

    @GetMapping("/checkPost")
    public String checkPost() {
        return postClient.checkPost();
    }

    @GetMapping("/getAllPosts")
    public List<PostResponse> getAllPosts() {
        return postClient.getAllPosts();
    }

    @GetMapping("/getPostById/{postId}")
    public PostResponse getPostById(@PathVariable String postId) {
        return postClient.getPostById(postId);
    }

    @GetMapping("/checkClient")
    public String checkClient() {
        return clientClient.checkClient();
    }

    @GetMapping("/getAllClients")
    public List<ClientResponse> getAllClients() {
        return clientClient.getAllClients();
    }

    @GetMapping("/getClientById/{clientId}")
    public ClientResponse getClientById(@PathVariable String clientId) {
        return clientClient.getClientById(clientId);
    }

    @GetMapping("/getPostDetails/{postId}")
    public PostResponse getPostDetails(@PathVariable String postId) {
        PostResponse post = postClient.getPostById(postId);
        ClientResponse client = clientClient.getClientById(post.getClient().getClientId());
        ClientResponse receiver = clientClient.getClientById(post.getReceiver().getClientId());
        post.setClient(client);
        post.setReceiver(receiver);
        return post;
    }
}
