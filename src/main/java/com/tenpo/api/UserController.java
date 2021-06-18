package com.tenpo.api;

import com.tenpo.exceptions.BadRequestException;
import com.tenpo.model.UserResource;
import com.tenpo.exceptions.AuthException;
import com.tenpo.response.Error;
import com.tenpo.response.UserResponse;
import com.tenpo.service.UserService;
import com.tenpo.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserResponse> create(@RequestBody UserResource userResource) {
        try {
            if (Utils.validatePassword(userResource.getPassword())) {
                userResource.setPassword(Utils.encrypt(userResource.getPassword()));
            }
            return new ResponseEntity<>(new UserResponse(userService.save(userResource)), HttpStatus.CREATED);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(new UserResponse(new Error(e.getMessage(), HttpStatus.BAD_REQUEST.value())), HttpStatus.BAD_REQUEST);
        } catch (AuthException e) {
            return new ResponseEntity<>(new UserResponse(new Error(e.getMessage(), HttpStatus.UNAUTHORIZED.value())), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(new UserResponse(new Error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value())), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<UserResponse> login(@RequestBody UserResource userResource) {
        try {
            if (Utils.validatePassword(userResource.getPassword())) {
                userResource.setPassword(Utils.encrypt(userResource.getPassword()));
            }
            return new ResponseEntity<>(new UserResponse(userService.login(userResource)), HttpStatus.ACCEPTED);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(new UserResponse(new Error(e.getMessage(), HttpStatus.BAD_REQUEST.value())), HttpStatus.BAD_REQUEST);
        } catch (AuthException e) {
            return new ResponseEntity<>(new UserResponse(new Error(e.getMessage(), HttpStatus.UNAUTHORIZED.value())), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(new UserResponse(new Error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value())), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> logout(@RequestHeader(value = "Authorization", required = false) String auth) {
        try {
            //This could be implemented with different ways, with a blacklist with redis, or a db with all tokens
            return new ResponseEntity<>("Session will expire whit the JWT.", HttpStatus.OK);
        } catch (AuthException e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

}
