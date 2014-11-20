class RestaurantPicker {

    List<Restaurant> restaurants

    RestaurantPicker(List<Restaurant> restaurants) {
        this.restaurants = restaurants
    }

    Restaurant pickRandom() {
        int index = new Random().nextInt(restaurants.size())
        restaurants.get(index)
    }

}
