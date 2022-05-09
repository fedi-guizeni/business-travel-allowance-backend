package com.pfe22.ava.Service.implementation;

import com.pfe22.ava.entities.AppUsers.User;
import com.pfe22.ava.entities.AppUsers.Userprincipal;
import com.pfe22.ava.Service.EmailService;
import com.pfe22.ava.Service.UserService;
import com.pfe22.ava.enumeration.Role;
import com.pfe22.ava.exception.AppUsers.EmailExistException;
import com.pfe22.ava.exception.AppUsers.EmailNotFoundException;
import com.pfe22.ava.exception.AppUsers.UserNotFoundException;
import com.pfe22.ava.exception.AppUsers.UsernameExistException;
import com.pfe22.ava.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.List;


import static com.pfe22.ava.enumeration.Role.ROLE_USER;

import static com.pfe22.ava.enumeration.Role.VÉRIFICATEUR_RÉGLEMENTAIRE;
import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@Service
@Transactional
@Qualifier("UserDetailsService")
public class UserServiceImplementation implements UserService, UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private UserRepository userRepository ;
    private BCryptPasswordEncoder passwordEncoder;
    private EmailService emailService;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository ,BCryptPasswordEncoder passwordEncoder  ,EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoder= passwordEncoder;
        this.emailService= emailService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);

        if (user == null){
            logger.error("User not found by username"+username);
            throw new UsernameNotFoundException("User not found by username : "+username);

        }else {
            user.setLastLoginDateDisplay(user.getLastLoginDate());
            user.setLastLoginDate(new Date());
            userRepository.save(user);
            Userprincipal userprincipal = new Userprincipal(user);
            logger.info("user found by username:"+ username);
            return userprincipal;
        }


    }

    @Override
    public User register(String firstname,
                         String lastname,
                         String username,
                         String email) throws EmailExistException,
                                                UsernameExistException,
                                                UserNotFoundException,
                                                MessagingException {

        validateNewUsernameAndEmail(StringUtils.EMPTY, username , email);
        User user = new User();
        user.setUserId(generateUserId());
        String password = generatePassword();
        String encodePassword= encodePassword(password);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setUsername(username);
        user.setEmail( email);
        user.setJoinDate(new Date());
        user.setPassword(encodePassword);
        user.setActive(true);
        user.setNotLocked(true);
        user.setAuthorities(VÉRIFICATEUR_RÉGLEMENTAIRE.getAuthorities());
        user.setRole(VÉRIFICATEUR_RÉGLEMENTAIRE.name());
        userRepository.save(user);
        LOGGER.info("new user password : " + password);
        emailService.SendNewPasswordEmail(firstname,password,email);

        return user;
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    private String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(10);
    }

    private String generateUserId() {
        return RandomStringUtils.randomNumeric(10);
    }

    private User validateNewUsernameAndEmail(String currentUsername , String newUsername, String newEmail) throws UsernameExistException, EmailExistException, UserNotFoundException {
       User userByNewUsername = findByUsername(newUsername);
       User userByNewEmail = findUserByEmail(newEmail);
       if (StringUtils.isNotBlank(currentUsername)){
           User currentUser = findByUsername(currentUsername);
           if (currentUser == null){
               throw new UserNotFoundException(" Aucun utilisateur trouvé par"+currentUsername);
           }
           if (userByNewUsername!=null && !currentUser.getId().equals(userByNewUsername.getId())){
               throw new UsernameExistException("Ce nom d'utilisateur existe déjà");
           }
           if(userByNewEmail != null && !currentUser.getId().equals(userByNewEmail.getId())){
               throw new EmailExistException("Email deja utilise");
           }
           return currentUser;

       }else {
           if (userByNewUsername != null){
               throw new UsernameExistException("Ce nom d'utilisateur existe déjà");
           }
           if (userByNewEmail != null){
               throw new EmailExistException("Email deja utilise");
           }
           return null;
       }
    }

    @Override
    public List<User> getUsers() {
        return  userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User addNewUser(String firstName, String lastname, String username, String email, String role, boolean isnonLocked, boolean isActive) throws UserNotFoundException, EmailExistException, UsernameExistException {
        validateNewUsernameAndEmail(StringUtils.EMPTY , username , email);
        User user = new User();
        String password = generatePassword();
        String encodePassword= encodePassword(password);
        user.setUserId(generateUserId());
        user.setFirstName(firstName);
        user.setLastName(lastname);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(encodePassword);
        user.setJoinDate(new Date());
        user.setActive(isActive);
        user.setNotLocked(isnonLocked);
        user.setRole(getRoleEnumName(role).name());
        user.setAuthorities(getRoleEnumName(role).getAuthorities());
        LOGGER.info("new user password" +password);
        userRepository.save(user);

        return user;
    }

    private Role getRoleEnumName(String role) {

        return  Role.valueOf(role.toUpperCase());
    }

    @Override
    public User updateUser(String currentUserName,
                           String newFirstName,
                           String newlastname,
                           String NewUsername,
                           String newEmail,
                           String role,
                           boolean isnonLocked,
                           boolean isActive) throws UserNotFoundException, EmailExistException, UsernameExistException {
        User currentUser = validateNewUsernameAndEmail(currentUserName , NewUsername , newEmail);

        currentUser.setFirstName(newFirstName);
        currentUser.setLastName(newlastname);
        currentUser.setUsername(NewUsername);
        currentUser.setEmail(newEmail);
        currentUser.setJoinDate(new Date());
        currentUser.setActive(isActive);
        currentUser.setNotLocked(isnonLocked);
        currentUser.setRole(getRoleEnumName(role).name());
        currentUser.setAuthorities(getRoleEnumName(role).getAuthorities());
        userRepository.save(currentUser);

        return currentUser;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);

    }

    @Override
    public void resetPassword(String email) throws EmailNotFoundException, MessagingException {
        User user = userRepository.findUserByEmail(email);
        if (user == null){
            throw new EmailNotFoundException("no user found for this email : "+ email);
        }
        String password = generatePassword();
        user.setPassword(encodePassword(password));
        userRepository.save(user);
        emailService.SendNewPasswordEmail(user.getFirstName(), password , user.getEmail());

    }
}
