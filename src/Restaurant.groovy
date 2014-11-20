class Restaurant {

    String name
    String url
    int minOrder
    float rating
    int averageDeliveryMinutes

    Restaurant(String name, String url) {
        this.name = name
        this.url = url
    }

    @Override
    String toString() {
        return url
    }

}
