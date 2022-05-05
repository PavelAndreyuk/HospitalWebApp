package com.testtask.hospitalwebapp.models;

import com.testtask.hospitalwebapp.PostgreSQLEnumType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@NoArgsConstructor
@TypeDefs({
        @TypeDef(
                name = "pgsql_enum",
                typeClass = PostgreSQLEnumType.class
        )
})
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue
    private Integer id;

    @Column(name = "name")
    @NotNull
    @NotEmpty
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    @Type(type = "pgsql_enum")
    @NotNull
    private UserRole role;

    public User(String name, UserRole role) {
        this.name = name;
        this.role = role;
    }
}
