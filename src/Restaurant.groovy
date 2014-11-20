class Restaurant {

    String name
    String url
    int minOrder
    double rating
    int averageDeliveryMinutes

    Restaurant(String name, String url, double rating, int minOrder, int averageDeliveryMinutes) {
        this.name = name
        this.url = url
        this.rating = rating
        this.minOrder = minOrder
        this.averageDeliveryMinutes = averageDeliveryMinutes
    }

    @Override
    String toString() {
        return "url: $url, minOrder: $minOrder, rating: $rating, averageDeliveryMinutes: $averageDeliveryMinutes"
    }

}
