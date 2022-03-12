package ro.tuc.tp.Bll.Validators;

import ro.tuc.tp.Model.Client;

import java.util.regex.Pattern;
/**
 * @author Talpos Andreea
 * Class which validates the address
 */
public class AddressValidator implements Validator<Client> {
    private static final String ADDRESS_PATTERN = ("^[#.0-9a-zA-Z\\s,-]+$");

    public boolean validate(Client c) {
            Pattern pattern = Pattern.compile(ADDRESS_PATTERN);
            if (!pattern.matcher(c.getAdresa()).matches()) {
                throw new IllegalArgumentException(" Invalid address!");
            }
        return true;
    }
}


