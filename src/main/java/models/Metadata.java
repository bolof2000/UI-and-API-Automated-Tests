package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Metadata {

    private String authentication_answer1;
    private String authentication_answer2;
    private String authentication_answer3;
    private String authentication_question1;
    private String authentication_question2;
    private String authentication_question3;
    private String key1;
    private String key2;
    private String notification_email;
    private String notification_language;

}
