package models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@lombok.Data
public class Data {

    private String token;
    private boolean active;
    private String gender;
    private String address1;
    private String city;
    private  String state;
    private String postal_code;
    private Date birth_date;
    private String country;
    private String phone;
    private String first_name;
    private String last_name;
    private String email;
    private boolean uses_parent_account;
    private boolean corporate_card_holder;
    private String password;
    private String created_time;
    private String last_modified_time;
    private  Metadata metadata;
    private String account_holder_group_token;
    private String status;
    private List<Identifications> identifications;

}
