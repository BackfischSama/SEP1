package Onlineshop;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public final class WarenkorbIstLeerExeption extends Exception {
    WarenkorbIstLeerExeption(String warenkorb) {
        super(String.format("Warenkorb konnte nicht gefunden werden"));
    }

}

