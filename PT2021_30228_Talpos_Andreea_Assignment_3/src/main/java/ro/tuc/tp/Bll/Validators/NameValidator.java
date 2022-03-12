package ro.tuc.tp.Bll.Validators;

import ro.tuc.tp.Model.Client;
import ro.tuc.tp.Model.Product;

import java.util.regex.Pattern;
/**
 * @author Talpos Andreea
 * Class which validates names
 */
public class NameValidator<T> implements Validator<T> {
    private static final String NAME_PATTERN = ("\\b([A-zÀ-ÿ][-,a-z. ']+[ ]*)+");

    public boolean validate(T t) {
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        if (t.getClass().getSimpleName().equals(Client.class.getSimpleName())) {
            if (!pattern.matcher(((Client) t).getNume()).matches()) {
                throw new IllegalArgumentException(" Invalid name!");
            }
        }else {
            if (t.getClass().getSimpleName().equals(Product.class.getSimpleName())) {
                if (!pattern.matcher(((Product) t).getNume()).matches()) {
                    throw new IllegalArgumentException(" Invalid name!");
                }
            }
        }
            return true;
        }

    }