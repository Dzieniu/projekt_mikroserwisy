package com.dzieniu.controller;

import com.dzieniu.entity.UserDto;
import com.dzieniu.entity.UserMapper;
import com.dzieniu.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Enumeration;

@RestController
@CrossOrigin(origins = "*")
public class RedirectController {

    private static final String reservation_server = "reservationService";
    private static final int reservation_port = 80;

    private static final String caisa_server = "host.docker.internal";
    private static final int caisa_port = 38080;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @RequestMapping("reservations/**")
    public ResponseEntity redirectToReservations(@RequestBody(required = false) String body,
                                                 HttpMethod method, HttpServletRequest request, HttpServletResponse response)
            throws URISyntaxException, JsonProcessingException {
        String requestUrl = request.getRequestURI();

        URI uri = new URI("http", null, reservation_server, reservation_port, null, null, null);
        uri = UriComponentsBuilder.fromUri(uri)
                .path(requestUrl)
                .query(request.getQueryString())
                .build(true).toUri();

        HttpHeaders headers = new HttpHeaders();

        String authenticatedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        if(!authenticatedUser.matches("anonymousUser")) {
            com.dzieniu.entity.User user = userService.getByUsername(((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
            UserDto userDto = userMapper.toDto(user);
            headers.add("userProfile", new ObjectMapper().writeValueAsString(userDto));
        }

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headers.set(headerName, request.getHeader(headerName));
        }

        HttpEntity<String> httpEntity = new HttpEntity<>(body, headers);
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.exchange(uri, method, httpEntity, String.class);
        } catch(HttpStatusCodeException e) {
            return ResponseEntity.status(e.getRawStatusCode())
                    .headers(e.getResponseHeaders())
                    .body(e.getResponseBodyAsString());
        }
    }

    @RequestMapping("CommentAndImageStoreApp-0.1/**")
    public ResponseEntity redirectCaisa(@RequestBody(required = false) String body,
                                           HttpMethod method, HttpServletRequest request, HttpServletResponse response)
            throws URISyntaxException, JsonProcessingException {
        String requestUrl = request.getRequestURI();

        URI uri = new URI("http", null, caisa_server, caisa_port, null, null, null);
        uri = UriComponentsBuilder.fromUri(uri)
                .path(requestUrl)
                .query(request.getQueryString())
                .build(true).toUri();

        HttpHeaders headers = new HttpHeaders();


        String authenticatedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        if(!authenticatedUser.matches("anonymousUser")) {
            com.dzieniu.entity.User user = userService.getByUsername(((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
            UserDto userDto = userMapper.toDto(user);
            headers.add("userProfile", new ObjectMapper().writeValueAsString(userDto));
        }


        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headers.set(headerName, request.getHeader(headerName));
        }

        HttpEntity<String> httpEntity = new HttpEntity<>(body, headers);
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.exchange(uri, method, httpEntity, String.class);
        } catch(HttpStatusCodeException e) {
            return ResponseEntity.status(e.getRawStatusCode())
                    .headers(e.getResponseHeaders())
                    .body(e.getResponseBodyAsString());
        }
    }
}
