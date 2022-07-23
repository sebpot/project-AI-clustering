package pl.si.projekt;

import pl.si.projekt.Model.GroupedOffer;
import pl.si.projekt.Repository.OfferRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        String filePath = "src/main/resources/data.json";

        OfferRepository offerRepository;
        try {
            offerRepository = new OfferRepository(filePath);
        }
        catch (IOException ex) {
            System.err.println("Could not load file: " + filePath);
            return;
        }

        GroupingAlgorithm grouping = new GroupingAlgorithm(offerRepository.getOffers());
        ArrayList<ArrayList<GroupedOffer>> groupedOffers = grouping.groupOffers(4);

        PrintWriter writer = new PrintWriter("java_out.txt", "UTF-8");

        int i = 0;
        for(ArrayList<GroupedOffer> group : groupedOffers){
            System.out.printf("Grupa %d:\n", ++i);
            writer.println("Grupa " + i + ":");
            for (GroupedOffer offer : group) {
                System.out.printf(" - %s %s PLN\n", offer.getName(), offer.getPrice());
                writer.println(" - " + offer.getName() + " " + offer.getPrice() + " PLN");
            }
        }
        writer.close();
    }

}
