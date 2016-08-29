package dominio;

import java.io.Serializable;

/**
 * Created by seu_raul on 29/08/16.
 */
public class Person implements Serializable {

    String name;
    String phone;
    String gender;

    public Person(String name, String phone, String gender) {
        this.name = name;
        this.phone = phone;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
