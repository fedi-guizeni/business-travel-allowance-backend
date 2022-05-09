package com.pfe22.ava.Service;

import com.pfe22.ava.entities.AppUsers.User;
import com.pfe22.ava.exception.AppUsers.EmailExistException;
import com.pfe22.ava.exception.AppUsers.EmailNotFoundException;
import com.pfe22.ava.exception.AppUsers.UserNotFoundException;
import com.pfe22.ava.exception.AppUsers.UsernameExistException;

import javax.mail.MessagingException;
import java.util.List;

public interface UserService {
 User register (String firstname , String lastname , String username , String email) throws EmailExistException, UsernameExistException, UserNotFoundException, MessagingException;

 List<User> getUsers();

 User findByUsername (String username);

 User findUserByEmail(String email);

 User addNewUser( String firstName ,String lastname ,  String username , String email , String role , boolean isnonLocked , boolean isActive) throws UserNotFoundException, EmailExistException, UsernameExistException;

 User updateUser( String  currentUserName, String newFirstName ,String newlastname, String NewUsername , String newEmail , String role , boolean isnonLocked , boolean isActive) throws UserNotFoundException, EmailExistException, UsernameExistException;


 void  deleteUser (Long id);

 void  resetPassword(String email) throws EmailNotFoundException, MessagingException;


}
