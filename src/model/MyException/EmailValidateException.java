package model.MyException;

public class EmailValidateException extends RuntimeException {
    public EmailValidateException(String message) {

        super(message);

    }
    @Override
    public String getMessage() {
        return "Email validate exception | " +  super.getMessage();
    }
}
