package ro.tuc.tp.Bll.Validators;

import ro.tuc.tp.Model.Client;

import java.util.regex.Pattern;
/**
 * @author Talpos Andreea
 * Class which validates the email
 */
public class EmailValidator implements Validator<Client> {
    private static final String EMAIL_PATTERN =("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    public boolean validate(Client t) {
            Pattern pattern = Pattern.compile(EMAIL_PATTERN);
            if (!pattern.matcher(t.getEmail()).matches()) {
                System.out.println(t.getEmail());
                throw new IllegalArgumentException(" Invalid email!");
            }
        return true;
    }
}
