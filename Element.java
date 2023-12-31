package main.java.com.lista2_zad1;

import java.math.BigDecimal;
import java.math.RoundingMode;

//Klasa jest odpowiedzialna jedynie za przechowywanie danych pojedynczego elementu (wysoka spójność).
//Klasa sama oblicza wartości pól wartoscnetto, wartoscvat i wartoscbrutto, ponieważ posiada wszystkie
//potrzebne do tego informacje (Ekspert), dzięki czemu inna klasa nie posiada tej nadmiernej dla
//niej funkcjonalności (wysoka spójność).
//Konstruktor sprawdza poprawność danych, dzięki czemu nie musi tego robić klasa przekazująca
//dane (wysoka spójność).

public class Element
{
    public static double zaokraglanie(double liczba)
    {
        BigDecimal pomocniczy = BigDecimal.valueOf(liczba);
        pomocniczy = pomocniczy.setScale(2, RoundingMode.HALF_UP);
        return pomocniczy.doubleValue();
    }

    public String nazwa;
    public double jednostkowanetto;
    public int ilosc;
    public int VAT;

    public double wartoscnetto;
    public double wartoscvat;
    public double wartoscbrutto;

    public Element(String nazw, double netto, int il, int v) throws ZleDane
    {
        if (netto<=0.0 || il<0 || v<0 || nazw.isEmpty())
        {
            throw new ZleDane();
        }
        else
        {
            this.nazwa = nazw;
            this.jednostkowanetto = netto;
            this.ilosc = il;
            this.VAT = v;

            this.wartoscnetto = zaokraglanie(jednostkowanetto*ilosc);
            this.wartoscvat = zaokraglanie(wartoscnetto*0.01*VAT);
            this.wartoscbrutto = zaokraglanie(wartoscnetto + wartoscvat);
        }
    }
}