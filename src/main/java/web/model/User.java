package web.model;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {

   @Id
   @Column(name = "Id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "user_name")
   private String username;

   @Column(name = "Email")
   private String email;

   @Column(name = "Password")
   private String password;

   @Column
   @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE) // значения ролей в jsp подтягиваются из БД
   private Set<Role> roles;

   public User() {}

   public User(String username, String email, String password, Set<Role> roles) {
      this.username = username;
      this.email = email;
      this.password = password;
      this.roles = roles;
   }

   public User(long id, String username, String email, String password, Set<Role> roles) {
      this.id = id;
      this.username = username;
      this.email = email;
      this.password = password;
      this.roles = roles;
   }


   @Override
   public String toString() {
      return String.format("User id is %s\n, User name is %s\n, User email is %s\n, User password is %s\n, User roles are %s\n",
              id, username, email, password, roles);
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public Set<Role> getRoles() {
      return roles;
   }

   public void setRoles(Set<Role> roles) {
      this.roles = roles;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return roles;
   }
}
