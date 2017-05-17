package com.szymczak.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by mateu on 12.05.2017.
 */
@AllArgsConstructor
@Getter
public enum WindowsOrientation {
    NORTH("Północ"), SOUTH("Południe"), EAST("Wschód"), WEST("Zachód");
    private String displayName;
}
