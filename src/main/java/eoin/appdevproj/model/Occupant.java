package eoin.appdevproj.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Occupant {
    private int id;
    private String name;
    private int age;
    private String occupation;
    private String eircode;
}
