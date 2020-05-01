package web.service;




import org.springframework.http.ResponseEntity;
import web.model.User;

import java.util.List;

public interface UserServiсe {
    void addUser(User user);

    void deleteUser(Long userId);

    void updateUser(User user);

    ResponseEntity<List> getAllUsers();

    User getUserById(User user);


}
