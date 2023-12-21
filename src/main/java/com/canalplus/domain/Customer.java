package com.canalplus.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


@Data
@Entity(name="customer")
@Table(name="Customer")

//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "subscriptions"})
public class Customer implements Serializable{
    @Id
    @GeneratedValue
    @Column(name= "id")
    private Long id;
    @Column(name = "firstname", nullable = false)
    @NotBlank(message = "Please provide the Customer first name")
    private String firstName;
    @Column(name = "lastname", nullable =false)
    @NotBlank(message = "Please provide the Customer last name")
    private String lastName;
    @Email(message = "Email is not valid")
    @NotEmpty(message = "Email cannot be empty")
    @Column(name="email")
    private String email;
    @Column(name="phone", nullable = false)
    @NotBlank(message = "Please provide the Phone Number")
    private String phone;
    @Column(columnDefinition = "boolean default true", name="active")
    private Boolean active;
    @Column(name = "creationdate", nullable=false, updatable=false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp  creationDate;
   // @Column(name = "disactivatedate", updatable=false, nullable = true)
   // private Timestamp disactivateDate; 
   // @column(name = 'status', nullable = false)
   // private String status;   /* Represents different status: Active, Resiled, Suspended,...*/

    @OneToMany(mappedBy="customer", cascade= CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Subscription> subscriptions;


    @PrePersist
    public void fillMissing() {
        if (this.active == null){
            this.active = true;
        }
        if(this.creationDate== null){
            this.creationDate = new Timestamp(System.currentTimeMillis());
        }
    }
     


}
