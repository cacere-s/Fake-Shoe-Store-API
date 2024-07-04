package com.fakepi.ShoeStore.Models;

import com.fakepi.ShoeStore.Helpers.UserRole;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class UserModel implements UserDetails {

  @JsonIgnore
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  @Column(nullable = false)
  private String username;
  @JsonIgnore
  private String email;
  @JsonIgnore
  private String password;
  @JsonIgnore
  @Enumerated(EnumType.STRING)
  private UserRole rol;
  @JsonBackReference
  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private List<ShoeModel> shoe;

  @JsonIgnore
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority((rol.name())));
  }
  @JsonIgnore
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }
  @JsonIgnore
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }
  @JsonIgnore
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }
  @JsonIgnore
  @Override
  public boolean isEnabled() {
    return true;
  }
}
