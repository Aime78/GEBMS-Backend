package com.gebms.gebmsbackend.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserRequest {

        private String id;

        private String firstName;

        private String lastName;

        private String email;

        private String password;

        private List<String> phoneNumbers;

        private String role;

        private String department;

        public UserRequest(String firstName, String lastName, String email, String password, List<String> phoneNumbers, String role, String department) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.password = password;
            this.phoneNumbers = phoneNumbers != null ? phoneNumbers : new ArrayList<>();
            this.role = role;
            this.department = department;
        }

}
