package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private int count;
    private int start_index;
    private int end_index;
    private boolean is_more;
    private List<Data> data;

}
