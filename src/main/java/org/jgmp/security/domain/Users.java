package org.jgmp.security.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class Users {

    //    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    @Column
    private int id;
    @Column(name = "username")
    private String userName;
    @Column
    private String password;
    @Column
    private int enabled;


    public Users(String username, String hashedPassword, int i) {
        this.userName = username;
        this.password = hashedPassword;
        this.enabled = i;
    }
}
