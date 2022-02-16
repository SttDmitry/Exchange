package com.base.exchange.entity;

import java.util.Objects;

public class ExchangeRate {
    private String ID;
    private String NumCode;
    private String CharCode;
    private int Nominal;
    private String Name;
    private double Value;
    private double Previous;

    public ExchangeRate(String ID, String numCode, String charCode, int nominal, String name, double value, double previous) {
        this.ID = ID;
        NumCode = numCode;
        CharCode = charCode;
        Nominal = nominal;
        Name = name;
        Value = value;
        Previous = previous;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNumCode() {
        return NumCode;
    }

    public void setNumCode(String numCode) {
        NumCode = numCode;
    }

    public String getCharCode() {
        return CharCode;
    }

    public void setCharCode(String charCode) {
        CharCode = charCode;
    }

    public int getNominal() {
        return Nominal;
    }

    public void setNominal(int nominal) {
        Nominal = nominal;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getValue() {
        return Value;
    }

    public void setValue(double value) {
        Value = value;
    }

    public double getPrevious() {
        return Previous;
    }

    public void setPrevious(double previous) {
        Previous = previous;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExchangeRate that = (ExchangeRate) o;
        return Nominal == that.Nominal && Double.compare(that.Value, Value) == 0 && Double.compare(that.Previous, Previous) == 0 && Objects.equals(ID, that.ID) && Objects.equals(NumCode, that.NumCode) && Objects.equals(CharCode, that.CharCode) && Objects.equals(Name, that.Name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, NumCode, CharCode, Nominal, Name, Value, Previous);
    }

    @Override
    public String toString() {
        return "ExchangeRate{" +
                "ID='" + ID + '\'' +
                ", NumCode='" + NumCode + '\'' +
                ", CharCode='" + CharCode + '\'' +
                ", Nominal=" + Nominal +
                ", Name='" + Name + '\'' +
                ", Value=" + Value +
                ", Previous=" + Previous +
                '}';
    }
}
