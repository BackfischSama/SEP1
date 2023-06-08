package Onlineshop;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public final class SortimentIstLeerException extends Exception {
    private final String name;

    SortimentIstLeerException(String name) {
        super(String.format("Das Sortiment %s ist Leer.", name));
        this.name = name;
    }
}