package pl.si.projekt.Model;

import pl.si.projekt.Tokenizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

public class GroupedOffer {
    private String name;
    private double price;
    private List<String> nameTokens;

    public GroupedOffer(Offer offer) {
        this.name = offer.getName();
        this.price = offer.getPrice();
        Tokenizer tokenizer = new Tokenizer();
        nameTokens = tokenizer.tokenize(name);
    }

    @Override
    public String toString() {
        String ret = "";
        for(String token : nameTokens){
            ret += (token + ", ");
        }
        return ret;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<String> getNameTokens() { return nameTokens; }
}
