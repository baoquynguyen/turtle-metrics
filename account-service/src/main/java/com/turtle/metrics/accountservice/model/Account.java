package com.turtle.metrics.accountservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "last_seen")
    private LocalDateTime lastSeen;

    @Embedded
    private Saving saving;

    @Column(columnDefinition = "TEXT")
    private String note;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Item> items = new ArrayList<>();

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    @JsonManagedReference
    private User user;

    //Domain logic
    public List<Item> getIncomes(){
        return items.stream()
                .filter(i -> i.getType() == ItemType.INCOME)
                .toList();
    }

    public List<Item> getExpense(){
        return items.stream()
                .filter(i -> i.getType() == ItemType.EXPENSE)
                .toList();
    }
}
