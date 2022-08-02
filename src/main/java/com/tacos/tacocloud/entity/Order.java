package com.tacos.tacocloud.entity;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.tacos.tacocloud.udt.TacoUDT;
import com.tacos.tacocloud.udt.UserUDT;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("tacoorders")
public class Order implements Serializable{

    @PrimaryKey
    private UUID id = Uuids.timeBased();

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Street is required")
    private String street;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is required")
    private String state;

    @Digits(integer = 5, fraction = 0, message = "Invalid CVV")
    private String zip;

    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "Must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;

    private Date placedAt;

    @Column("tacos")
    private List<TacoUDT> tacos = new ArrayList<>();

    @Column("user")
    private UserUDT user;

    public void addDesign(Taco taco) {
        tacos.add(new TacoUDT(taco.getTacoName(), taco.getIngredients()));
    }

}
