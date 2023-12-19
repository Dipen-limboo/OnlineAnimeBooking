package com.springbootAnmte.animte.entity;

import java.util.List;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role", nullable = false, unique = true, columnDefinition = "VARCHAR(255) DEFAULT 'USER'")
    private String role;


    @ManyToMany(mappedBy="roles")
    private List<User> users;

    public Role() {
        super();
    }

	public Role(Long id, String role, List<com.springbootAnmte.animte.entity.User> users) {
		super();
		this.id = id;
		this.role = role;
		this.users = users;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
    
    
}