package utils;

import model.MyException.EmailValidateException;

public class PersonValidition {

    public static void validateEmail(String email) throws EmailValidateException {
        // 1. Должна присутствовать @
        int indexAt = email.indexOf('@');
        if (indexAt == -1 || indexAt != email.lastIndexOf('@')) throw new EmailValidateException(" Должна присутствовать @ error");

        // 2. Точка после собаки
        if (email.indexOf('.', indexAt + 2) == -1) throw new EmailValidateException("Должна присутствовать точка после собаки . after @ error");

        // 3. После последней точки должно быть минимум 2 символа
        if (email.lastIndexOf('.') >= email.length() - 2) throw new EmailValidateException("После последней точки должно быть минимум 2 символа last . error");

        for (int i = 0; i < email.length(); i++) {
            char ch = email.charAt(i);
            if (!(Character.isAlphabetic(ch)
                    || Character.isDigit(ch)
                    || ch == '_'
                    || ch == '-'
                    || ch == '.'
                    || ch == '@')) { // Символ НЕ подходит
                throw new EmailValidateException("Символ НЕ подходит illegal symbol");
            }
        }

        // 5. До собаки должен быть хотя бы 1 символ
        if (indexAt == 0) throw new EmailValidateException("До @ должен быть хотя бы 1 символ ");

        if (!Character.isAlphabetic(email.charAt(0))) throw new EmailValidateException("Первый символ должен быть буквой");

    }


}
