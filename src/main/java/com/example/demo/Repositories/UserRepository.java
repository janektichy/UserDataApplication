package com.example.demo.Repositories;

import com.example.demo.Models.User;

import java.time.LocalDate;
import java.util.*;


public class UserRepository {
    private List<User> allUsers;
    private String[] names;
    private String[] surnames;
    private String[] emails;
    private Random random;
    private Date now;
    private String[] presets;
    private boolean[] statuses;
    private int id;
    public UserRepository() {
        this.now = new Date();
        this.random = new Random();
        this.names = new String[] {"John", "Jack", "Peter", "Josh", "Thomas"};
        this.surnames = new String[] {"Brown", "Yellow", "Green", "Black", "Blue", "Purple", "Pink", "White"};
        this.emails = new String[] {"email.cz", "gmail.com", "seznam.cz"};
        this.presets = new String[] {"420", "735", "450"};
        this.id = 0;
        this.allUsers = new ArrayList<User>();
        this.statuses = new boolean[] {false, true};
        fillUsers(55);
    }
    // I wanted to try the app using more than 1000 users, and I obviously didn't want to spend hours hardcoding it, so I made this Random User Generator to speed it up
    public User generateRandomUser(){
        String name = names[random.nextInt(names.length)];
        String surname = surnames[random.nextInt(surnames.length)];
        String email = name.toLowerCase(Locale.ROOT) + '.' + surname.toLowerCase(Locale.ROOT) + '@' + emails[random.nextInt(emails.length)];
        LocalDate dateOfCreation = LocalDate.of(random.nextInt(7) + 2015, random.nextInt(12) + 1, random.nextInt(28) + 1);
        String preset = presets[random.nextInt(presets.length)];
        String number = String.valueOf(100000000 + random.nextInt(900000000));
        String[] phoneNumber = new String[] { preset, number };
        this.id+=1;
        User user = new User(this.id, name, surname, statuses[random.nextInt(2)], email, phoneNumber, dateOfCreation);
        return user;
    }
    public void fillUsers(int amount){
        for (int i = 0; i < amount; i++){
            this.allUsers.add(generateRandomUser());
        }
    }
    public void removeUser(User user) {
        this.allUsers.remove(user);
    }
    public List<User> getAllUsers() {
        return allUsers;
    }
}