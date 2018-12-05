//package Airbnb;
//import java.util.*;
///**
// * Created by yuqishi on 10/25/18.
// */
//public class TravelBuddy {
//
//
//    List<Buddy> friends;
//    Set<String> myCities;
//    List<Buddy> myBuddies;
//
//    public TravelBuddy(List<Buddy> friends, Set<String> myCities) {
//        this.friends = friends;
//        this.myCities = myCities;
//    }
//
//    public List<String> getBuddies() {
//        List<Buddy> Buddies = new ArrayList<>();
//        int num = myCities.size() / 2;
//
//        for (Buddy b : friends) {
//            int count = 0;
//            for (String city : b.cityList) {
//                if (myCities.contains(city)) {
//                    count++;
//                } else {
//                    b.diffCities.add(city);
//                }
//            }
//            b.similarity = count;
//            if (count >= num) {
//                Buddies.add(b);
//            }
//        }
//        Collections.sort(Buddies, new Comparator<Buddy>() {
//            public int compare(Buddy o1, Buddy o2) {
//                if (o1.similarity == o2.similarity) {
//                    return o1.name.compareTo(o2.name);
//                }
//                return o2.similarity - o1.similarity;
//            }
//        });
//        this.myBuddies = Buddies;
//
//        List<String> res = new ArrayList<>();
//        for (Buddy b : Buddies) {
//            res.add(b.name);
//        }
//        return res;
//    }
//
//    public Set<String> getCities(int k) {
//        Set<String> res = new LinkedHashSet<>(); //一定要申明成LinkedHashSet! 不然输出顺序不对
//        for (int i = 0; i < myBuddies.size(); i++) {
//            Buddy cur = myBuddies.get(i);
//            for (String city : cur.diffCities) {
//                res.add(city);
//                if (res.size() == k) {
//                    return res;
//                }
//            }
//        }
//        return res;
//    }
//
//    public static void main(String[] argus) {
//        Buddy b1 = new Buddy("Tom", Arrays.asList("S","N","Q","W")); //2
//        Buddy b2 = new Buddy("Mary", Arrays.asList("S","N","B","H")); //3
//        Buddy b3 = new Buddy("Janet", Arrays.asList("A","P","L","M")); //1
//        List<Buddy> list = Arrays.asList(b1, b2, b3);
//        Set<String> myCities = new HashSet<>();
//        myCities.add("S");
//        myCities.add("N");
//        myCities.add("B");
//        myCities.add("A");
//
//        TravelBuddy tb = new TravelBuddy(list, myCities);
//        tb.getBuddies();
//        System.out.print("Buddies are: ");
//        for (Buddy b : tb.myBuddies) {
//            System.out.print(b.name + ", ");
//        }
//
//        System.out.println();
//        System.out.print("cities are: ");
//        Set<String> res2 = tb.getCities(2);
//        for (String ss : res2) {
//            System.out.print(ss + ", ");
//        }
//    }
//}
//
//
//
//class Buddy {
//    String name;
//    List<String> cityList;
//    int similarity;
//    List<String> diffCities;
//    public Buddy (String name, List<String> cities) {
//        this.name = name;
//        this.cityList = cities;
//        this.similarity = 0;
//        this.diffCities = new ArrayList<>();
//    }
//}
