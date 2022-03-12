package ro.tuc.tp.Bll.Validators;

public interface Validator<T> {
    public boolean validate(T t);
}
