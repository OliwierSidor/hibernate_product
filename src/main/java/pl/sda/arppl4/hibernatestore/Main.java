package pl.sda.arppl4.hibernatestore;

import pl.sda.arppl4.hibernatestore.dao.ProduktDao;
import pl.sda.arppl4.hibernatestore.parser.ProductCommandLineParser;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProduktDao dao = new ProduktDao();
        ProductCommandLineParser parser = new ProductCommandLineParser(scanner, dao);
        parser.parse();

    }
}
