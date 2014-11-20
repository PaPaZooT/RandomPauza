import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

class RestaurantListParser {

    private Document htmlDocument
    private String defaultAverageDeliveryTime = "45 min"

    RestaurantListParser(String url) {
        this.htmlDocument = Jsoup.connect(url).get()
    }

    List<Restaurant> getAllRestaurants() {
        ArrayList<Restaurant> restaurants = getNormalRestaurants()
        //restaurants.addAll(getHighlightedRestaurants())
        restaurants
    }

    private List<Restaurant> getNormalRestaurants() {
        Elements restaurantListItems = htmlDocument.getElementsByClass("index-items").first().select("ul").first().select("li")

        def restaurants = new ArrayList<Restaurant>()
        for (restaurantListItem in restaurantListItems) {
            Element link = restaurantListItem.getElementsByClass("having-bookmark").select("a[href]").first()
            String name = link.text()
            String url = link.attr("href")

            Element detailsBox = restaurantListItem.getElementsByClass("box").first()
            String ratingS = detailsBox.getElementsByClass("rating").first().text()
            double rating = ratingToDouble(ratingS)

            String minOrderS = detailsBox.select("p").first().text()
            int minOrder = minOrderToInt(minOrderS)


            Elements detailsBoxPs = detailsBox.select("p")
            String averageDeliveryMinutesS = defaultAverageDeliveryTime
            if(detailsBoxPs.size() > 1) {
                averageDeliveryMinutesS = detailsBox.select("p").get(1).text()
            }
            int averageDeliveryMinutes = averageDeliveryMinutesToInt(averageDeliveryMinutesS)

            restaurants.add(new Restaurant(name, url, rating, minOrder, averageDeliveryMinutes))
        }

        restaurants
    }

    private double ratingToDouble(String rating) {
        rating.subSequence(1, rating.length() - 1).toDouble()
    }

    private int minOrderToInt(String minOrder) {
        def regex = ~ /\d+\.\d\d/
        def matcher = minOrder.replace(",", ".") =~ regex
        matcher.getAt(0).toString().toDouble().toInteger()
    }

    private int averageDeliveryMinutesToInt(String averageDeliveryMinutes) {
        def regex = ~ /\d+ min/
        def matcher = averageDeliveryMinutes =~ regex
        if (matcher.size() > 0) {
            matcher.getAt(0).toString().replace("min", "").toInteger()
        } else {
            regex = ~ /(\d+) h : (\d+) m/
            matcher = averageDeliveryMinutes =~ regex

            String hours = matcher.getAt(0).getAt(1)
            String minutes = matcher.getAt(0).getAt(2)
            hours.toInteger() * 60 + minutes.toInteger()
        }
    }

/*
    private List<Restaurant> getHighlightedRestaurants() {
        Elements links = htmlDocument.getElementsByClass("index-two-item").first().select("h3").select("a[href]")
        List<Restaurant> restaurants = new ArrayList<Restaurant>()
        for (link in links) {
            String name = link.text()
            String url = link.attr("href")
            if (link.attr("title").startsWith(name)) {
                restaurants.add(new Restaurant(name, url))
            }
        }
        restaurants
    }*/

}
