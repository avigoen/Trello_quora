package com.upgrad.quora.api.controller;

import com.upgrad.quora.api.model.UserDeleteResponse;
import com.upgrad.quora.service.business.UserAdminBusinessService;
import com.upgrad.quora.service.exception.AuthorizationFailedException;
import com.upgrad.quora.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserAdminController {

    @Autowired
    private UserAdminBusinessService userAdminBusinessService;

    /**
     * This method deletes user in system by admin.
     *
     * @param userUuid      The UUID of the User to be deleted
     * @param authorization The JWT access token of the user passed in the request header.
     * @return ResponseEntity
     * @throws AuthorizationFailedException
     * @throws UserNotFoundException
     */
    @RequestMapping(value = "/admin/user/{userId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserDeleteResponse> deleteUser(@PathVariable("userId") final String userUuid, @RequestHeader("authorization") final String authorization) throws AuthorizationFailedException, UserNotFoundException {
        userAdminBusinessService.deleteUser(userUuid, authorization);
        UserDeleteResponse userDeleteResponse = new UserDeleteResponse().id(userUuid).status("USER SUCCESSFULLY DELETED");
        return new ResponseEntity<>(userDeleteResponse, HttpStatus.OK);
    }
}
