package com.example.helloWorld.user;



import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.helloWorld.user.permission.ADMIN_CREATE;
import static com.example.helloWorld.user.permission.ADMIN_DELETE;
import static com.example.helloWorld.user.permission.ADMIN_READ;
import static com.example.helloWorld.user.permission.ADMIN_UPDATE;
import static com.example.helloWorld.user.permission.MANAGER_CREATE;
import static com.example.helloWorld.user.permission.MANAGER_DELETE;
import static com.example.helloWorld.user.permission.MANAGER_READ;
import static com.example.helloWorld.user.permission.MANAGER_UPDATE;

@RequiredArgsConstructor
public enum Role {

  USER,
  ADMIN
}
