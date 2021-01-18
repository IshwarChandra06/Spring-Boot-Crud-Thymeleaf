package com.example.demo.model;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    
    @NotNull
	@Size(min=2, max=30,message="length should be between 2 to 30")
    @Column(name = "first_name")
    private String firstName;
    
    @Size(min=2, max=30,message="length should be between 2 to 30")
    @Column(name = "last_name")
    private String lastName;
    
    @NotEmpty(message="email should not be empty")
    @Email(regexp="^(.+)@(.+)$",message="Invalid email")
    @Column(name = "email")
    private String email;
    
    @Column//(columnDefinition = "default false")
    private boolean isDelete;
    
    @ManyToOne
    @JoinColumn(name="dept_id")
    private Department dept;
    
    public Department getDept() {
		return dept;
	}
	public void setDept(Department dept) {
		this.dept = dept;
	}
	public long getId() 
    {
        return id;
    }
    public void setId(long id) 
    {
        this.id = id;
    }
    public String getFirstName() 
    {
        return firstName;
    }
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    public String getLastName()
    {
        return lastName;
    }
    public void setLastName(String lastName) 
    {
        this.lastName = lastName;
    }
    public String getEmail() 
    {
        return email;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }
	public boolean isDelete() {
		return isDelete;
	}
	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

		
	}
	
    
