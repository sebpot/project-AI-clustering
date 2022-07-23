package pl.si.projekt;

import pl.si.projekt.Model.GroupedOffer;
import pl.si.projekt.Model.Offer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GroupingAlgorithm {
    private ArrayList<GroupedOffer> groupedOffers;

    public GroupingAlgorithm(List<Offer> offers) {
        groupedOffers = new ArrayList<>();
        for(Offer o : offers){
            groupedOffers.add(new GroupedOffer(o));
        }
    }

    public GroupingAlgorithm(ArrayList<GroupedOffer> offers) {
        groupedOffers = offers;
    }

    public ArrayList<ArrayList<GroupedOffer>> groupOffers(int depth) {
        ArrayList<ArrayList<GroupedOffer>> list = new ArrayList<>();

        if(groupedOffers.size() == 1){
            list.add(groupedOffers);
            return list;
        }

        ArrayList<GroupedOffer> list1 = new ArrayList<>();
        ArrayList<GroupedOffer> list2 = new ArrayList<>();

        int max = 0;
        String mostOftenOccur = "";
        for(GroupedOffer groupedOffer : groupedOffers){
            List<String> tokens = groupedOffer.getNameTokens();
            Iterator<String> iterator = tokens.iterator();

            while(iterator.hasNext()){
                String token = iterator.next();
                int counter = 0;

                for (GroupedOffer offer : groupedOffers) {
                    if (offer.getNameTokens().contains(token)) {
                        counter++;
                    }
                }
                if(counter == groupedOffers.size()){
                    iterator.remove();
                    for (GroupedOffer offer : groupedOffers) {
                        offer.getNameTokens().remove(token);
                    }
                }
                else if(counter > max){
                    max = counter;
                    mostOftenOccur = token;
                }
            }
        }

        for (GroupedOffer groupedOffer : groupedOffers) {
            if (groupedOffer.getNameTokens().contains(mostOftenOccur)) {
                list1.add(groupedOffer);
            } else {
                list2.add(groupedOffer);
            }
        }

        if(depth == 0){
            if(!list1.isEmpty()) list.add(list1);
            if(!list2.isEmpty()) list.add(list2);
        }
        else{
            if(!list1.isEmpty()) {
                GroupingAlgorithm offers1 = new GroupingAlgorithm(list1);
                ArrayList<ArrayList<GroupedOffer>> groupedList1 = offers1.groupOffers(depth - 1);
                list.addAll(groupedList1);
            }
            if(!list2.isEmpty()) {
                GroupingAlgorithm offers2 = new GroupingAlgorithm(list2);
                ArrayList<ArrayList<GroupedOffer>> groupedList2 = offers2.groupOffers(depth - 1);
                list.addAll(groupedList2);
            }
        }

        return list;
    }

    public List<GroupedOffer> getGroupedOffers() {
        return groupedOffers;
    }

}
