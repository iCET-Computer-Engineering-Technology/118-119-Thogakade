package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    private String id;
    private String title;
    private String name;
    private LocalDate dobValue;
    private Double salary;
    private String address;
    private String city;
    private String province;
    private String postalCode;
}
