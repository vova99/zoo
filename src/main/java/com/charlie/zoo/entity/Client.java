package com.charlie.zoo.entity;

import com.charlie.zoo.enums.ClientRoles;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private double defaultDiscount = 0;
    private String note;

    @OneToMany(mappedBy = "client")
    private List<Phone> phones;
    @Enumerated(EnumType.STRING)
    private ClientRoles role = ClientRoles.RETAILER;
}
