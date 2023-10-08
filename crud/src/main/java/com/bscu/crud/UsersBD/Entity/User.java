package com.bscu.crud.UsersBD.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.GenericGenerator;

@Data
@jakarta.persistence.Entity
@jakarta.persistence.Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @jakarta.persistence.Id
    @jakarta.persistence.GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @jakarta.validation.constraints.NotNull(message = "El campo no puede ser nulo")
    private String name;
    @jakarta.validation.constraints.NotNull(message = "El campo no puede ser nulo")
    private String lastName;
    @jakarta.validation.constraints.NotNull(message = "El campo no puede ser nulo")
    private String age;
    @jakarta.validation.constraints.Email
    private String email;
    @jakarta.validation.constraints.NotNull(message = "El campo no puede ser nulo")
    private String numberPhone;
}