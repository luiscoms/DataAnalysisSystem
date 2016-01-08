package br.com.luiscoms.parse;

import java.io.BufferedReader;
import java.util.ArrayList;

public interface Parser {
    public ArrayList<? extends Object> parse(BufferedReader bufferedReader);
}
