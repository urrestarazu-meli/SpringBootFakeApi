package com.photogram.fake.api.modules.repositories;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.photogram.fake.api.modules.entities.domain.Post;
import com.photogram.fake.api.modules.entities.domain.User;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

class UsersRepositoryTest {
    private RestTemplate restTemplate = mock(RestTemplate.class);
    private Gson gson = new GsonBuilder().create();

    @Test
    void get() {
        Long userId = 999L;
        String url = String.format("https://jsonplaceholder.typicode.com/users/%d", userId);

        String bodyResponse = "{\n" +
                "  \"id\": 999,\n" +
                "  \"name\": \"Leanne Graham\",\n" +
                "  \"username\": \"Bret\",\n" +
                "  \"email\": \"Sincere@april.biz\",\n" +
                "  \"address\": {\n" +
                "    \"street\": \"Kulas Light\",\n" +
                "    \"suite\": \"Apt. 556\",\n" +
                "    \"city\": \"Gwenborough\",\n" +
                "    \"zipcode\": \"92998-3874\",\n" +
                "    \"geo\": {\n" +
                "      \"lat\": \"-37.3159\",\n" +
                "      \"lng\": \"81.1496\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"phone\": \"1-770-736-8031 x56442\",\n" +
                "  \"website\": \"hildegard.org\",\n" +
                "  \"company\": {\n" +
                "    \"name\": \"Romaguera-Crona\",\n" +
                "    \"catchPhrase\": \"Multi-layered client-server neural-net\",\n" +
                "    \"bs\": \"harness real-time e-markets\"\n" +
                "  }\n" +
                "}";
        when(restTemplate.getForEntity(url, String.class))
                .thenReturn(new ResponseEntity(bodyResponse, HttpStatus.OK));

        UsersRepository usersRepository = new UsersRepository(restTemplate, gson);
        User result = usersRepository.get(userId);

        assertNotNull(result);
        assertEquals(userId, result.getId());
    }

    @Test
    void getPosts() {
        Long userId = 999L;
        String url = String.format("https://jsonplaceholder.typicode.com/users/%d/posts", userId);

        String bodyResponse = "[\n" +
                "  {\n" +
                "    \"userId\": 1,\n" +
                "    \"id\": 1,\n" +
                "    \"title\": \"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\n" +
                "    \"body\": \"quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"userId\": 1,\n" +
                "    \"id\": 2,\n" +
                "    \"title\": \"qui est esse\",\n" +
                "    \"body\": \"est rerum tempore vitae\\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\\nqui aperiam non debitis possimus qui neque nisi nulla\"\n" +
                "  }\n" +
                "]";

        when(restTemplate.getForEntity(url, String.class))
                .thenReturn(new ResponseEntity(bodyResponse, HttpStatus.OK));

        UsersRepository usersRepository = new UsersRepository(restTemplate, gson);
        List<Post> result = usersRepository.getPosts(userId);
        
        assertFalse(result.isEmpty());
        assertEquals(2, result.size() );
    }
}