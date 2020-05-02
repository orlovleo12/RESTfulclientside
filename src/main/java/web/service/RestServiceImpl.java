package web.service;


import org.springframework.http.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import web.model.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RestServiceImpl implements UserDetailsService, UserServiсe {
    private static RestTemplate restTemplate = new RestTemplate();

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Map<String, String> params = new HashMap<String, String>();
        params.put("s", s);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://localhost:8081/rest/user/{s}", User.class, params);
    }

    @Override
    public void addUser(User user) {
        restTemplate.postForObject("http://localhost:8081/rest/create", user, User.class);
    }

    @Override
    public void deleteUser(Long userId) {
        Map<String, Long> params = new HashMap<String, Long>();
        params.put("id", userId);
        restTemplate.delete("http://localhost:8081/rest/delete/" + userId);
    }

    @Override
    public void updateUser(User user) {
        Map<String, String> params = new HashMap<>();
        params.put("id", user.getId().toString());
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put("http://localhost:8081/rest/update", user, params);

    }

    @Override
    public ResponseEntity<List> getAllUsers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<List> result = restTemplate.exchange("http://localhost:8081/rest/list", HttpMethod.GET, entity,
                List.class);
        return result;
    }

    @Override
    public User getUserById(User user) {
        Map<String, Long> params = new HashMap<>();
        params.put("id", user.getId());

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://localhost:8081/rest/read/{id}", User.class, params);
    }


}
