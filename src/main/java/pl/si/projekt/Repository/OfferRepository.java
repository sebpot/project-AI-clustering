package pl.si.projekt.Repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import pl.si.projekt.Model.Offer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class OfferRepository {

    private List<Offer> offers;

    public OfferRepository(String filePath) throws IOException {
        Gson gson = new Gson();

        Path dataPath = Path.of(filePath);
        String data = Files.readString(dataPath);

        offers = gson.fromJson(data, new TypeToken<ArrayList<Offer>>(){}.getType());
    }

    public List<Offer> getOffers() {
        return offers;
    }

}
