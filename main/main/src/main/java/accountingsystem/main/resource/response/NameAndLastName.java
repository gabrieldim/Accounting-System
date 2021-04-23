package accountingsystem.main.resource.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NameAndLastName {
    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    public NameAndLastName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public NameAndLastName() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
