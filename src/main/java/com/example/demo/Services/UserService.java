package com.example.demo.Services;

import com.example.demo.Models.FilterParams;
import com.example.demo.Models.User;
import com.example.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.getAllUsers();
    }
    public List<User> filterUsers(FilterParams filterParams){
        // This function might look horrible, but it allows Client to filter Users by all parameters at once
        //Validating null input
        if (filterParams == null){ return userRepository.getAllUsers();}

        //Declaring Predicates
        Predicate<User> namePredicate = user -> user.getName().toLowerCase(Locale.ROOT).contains(filterParams.getSearch());
        Predicate<User> surnamePredicate = user -> user.getSurname().toLowerCase(Locale.ROOT).contains(filterParams.getSearch());
        Predicate<User> emailPredicate = user -> user.getEmail().toLowerCase(Locale.ROOT).contains(filterParams.getSearch());
        Predicate<User> presetPredicate = user -> user.getPhoneNumber()[0].contains(filterParams.getPreset());
        Predicate<User> statusPredicate = user -> user.isStatus();
        if (filterParams.getStatus().equals("deactivated")) {
            statusPredicate = user -> !user.isStatus();
        }
        Predicate<User> dateFromPredicate = user -> user.getDateOfCreation().isAfter(filterParams.getFrom());
        Predicate<User> dateToPredicate = user -> user.getDateOfCreation().isBefore(filterParams.getTo());

        //Combining Predicates
        List<User> filteredUsers = userRepository.getAllUsers()
                .stream()
                .filter((namePredicate
                        .or(surnamePredicate)
                        .or(emailPredicate))
                        .and(presetPredicate))
                .collect(Collectors.toList());

        if (filterParams.getFrom() != null){
            filteredUsers = filteredUsers.stream().filter(dateFromPredicate).collect(Collectors.toList());
        }
        if (filterParams.getTo() != null){
            filteredUsers = filteredUsers.stream().filter(dateToPredicate).collect(Collectors.toList());
        }
        if (filterParams.getStatus() != "") {
            filteredUsers = filteredUsers.stream().filter(statusPredicate).collect(Collectors.toList());
        }

        return filteredUsers;

    }
    public void changeUserStatus(boolean changeStatus, Integer id){
        for (User user : userRepository.getAllUsers()){
            if (user.getId() == id){
                user.setStatus(changeStatus);
                break;
            }
        }
    }
    public void deleteUser(Integer id){
        for (User user : userRepository.getAllUsers()){
            if (user.getId() == id){
                userRepository.removeUser(user);
                break;
            }
        }
    }
    public User findUserById(Integer id){
        User selectedUser = userRepository.getAllUsers().stream().filter(u -> id == u.getId()).findAny().orElse(null);
        return selectedUser;
    }
    public void editUser(Integer id, String name, String surname, String email, String preset, String phoneNumber){
        String[] newPhoneNumber = new String[2];
        for (User user : userRepository.getAllUsers()){
            if (user.getId() == id){
                if(name != null && name != ""){
                    user.setName(name);
                }
                if(surname != null && surname != ""){
                    user.setSurname(surname);
                }
                if(email != null && email != ""){
                    user.setEmail(email);
                }
                if(preset != null && preset != ""){
                    newPhoneNumber[0] = preset;
                }
                else{
                    newPhoneNumber[0] = user.getPhoneNumber()[0];
                }
                if(phoneNumber != null && phoneNumber != ""){
                    newPhoneNumber[1] = phoneNumber;
                }
                else{
                    newPhoneNumber[1] = user.getPhoneNumber()[1];
                }
                user.setPhoneNumber(newPhoneNumber);
                break;
            }
        }
    }


}







