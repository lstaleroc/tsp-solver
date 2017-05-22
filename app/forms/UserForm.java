package forms;

/**
 * Created by LuisSebastian on 5/21/17.
 * Represents the login form.
 */
public class UserForm {

    protected String email;
    protected String password;

    public void setEmail(String email) {

        this.email = email;
    }

    public String getEmail() {

        return email;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String getPassword() {

        return password;
    }

}
