import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

class RestaurantListParser {

    Document htmlDocument

    RestaurantListParser(Document htmlDocument) {
        this.htmlDocument = htmlDocument
    }

    List<Restaurant> getAllRestaurants() {
        ArrayList<Restaurant> restaurants = getNormalRestaurants()
        //restaurants.addAll(getHighlightedRestaurants())
        restaurants
    }

    private List<Restaurant> getNormalRestaurants() {
        Elements restaurantElements = htmlDocument.getElementsByClass("index-items").first().getElementsByIndexEquals(0).first().getElementsByClass("having-bookmark")
        List<Restaurant> restaurants = new ArrayList<Restaurant>()
        for (restaurantElement in restaurantElements) {
            Element link = restaurantElement.select("a[href]").first()
            String name = link.text()
            String url = link.attr("href")
            restaurants.add(new Restaurant(name, url))
        }
        restaurants
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
