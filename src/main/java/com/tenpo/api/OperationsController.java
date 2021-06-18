package com.tenpo.api;

import com.tenpo.response.Error;
import com.tenpo.response.OperationResponse;
import com.tenpo.service.OperationService;
import com.tenpo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/operations")
public class OperationsController {

    private final OperationService operationService;
    private final UserService userService;

    @Autowired
    public OperationsController(OperationService operationService, UserService userService) {
        this.operationService = operationService;
        this.userService = userService;
    }

    @GetMapping("/multiply/{a}/{b}")
    public ResponseEntity<OperationResponse> multiplyNumbers(@RequestHeader(value = "Authorization", required = false) String auth,
                                                             @PathVariable String a,
                                                             @PathVariable String b) {
        try {
            boolean isLoggedIn = userService.validateJwt(auth);
            if (isLoggedIn) {
                Double first_number = Double.valueOf(a);
                Double second_number = Double.valueOf(b);

                return new ResponseEntity<>(new OperationResponse(operationService.multiply(first_number, second_number)), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new OperationResponse(new Error(e.getMessage(), HttpStatus.UNAUTHORIZED.value())), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(new OperationResponse(new Error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value())), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
