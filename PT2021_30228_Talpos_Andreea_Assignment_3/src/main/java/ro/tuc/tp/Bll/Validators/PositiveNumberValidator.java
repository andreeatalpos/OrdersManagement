package ro.tuc.tp.Bll.Validators;

import ro.tuc.tp.Model.Client;
import ro.tuc.tp.Model.Orders;
import ro.tuc.tp.Model.Product;

import java.util.regex.Pattern;
/**
 * @author Talpos Andreea
 * Class which validates numbers
 */
public class PositiveNumberValidator<T> implements Validator<T> {
    private static final String NUMBER_PATTERN = "\\d*\\d+|\\d+";

    public boolean validate(T t) {
        Pattern pattern = Pattern.compile(NUMBER_PATTERN);
        if (t.getClass().getSimpleName().equals(Client.class.getSimpleName())) {
            if (!pattern.matcher(((Orders) t).getCantitate() + "").matches()) {
                throw new IllegalArgumentException(" Invalid number!");
            }
        } else if (t.getClass().getSimpleName().equals(Product.class.getSimpleName())) {
            if (!pattern.matcher(((Product) t).getPret() + "").matches()) {
                throw new IllegalArgumentException(" Invalid number!");
            }
            if (!pattern.matcher(((Product) t).getStoc() + "").matches()) {
                throw new IllegalArgumentException(" Invalid number!");
            }
        }

        return true;
    }
}
