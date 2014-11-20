class Settings {

    String pauzaUrl
    String neighbourhood
    int maxMinOrder
    int maxAverageDeliveryTime
    double minRating
    String[] skypeIds
    boolean shouldOpenBrowser

    static Settings fromSettingsXml(String settingsFilePath) {
        def settingsXml = new XmlSlurper().parse(settingsFilePath)
        new Settings (
                settingsXml.pauzaUrl.toString(),
                settingsXml.neighbourhood.toString(),
                settingsXml.maxMinOrder.toInteger(),
                settingsXml.maxAverageDeliveryTime.toInteger(),
                settingsXml.minRating.toDouble(),
                (String[])settingsXml.peopleToNotify,
                settingsXml.shouldOpenBrowser.toBoolean())
    }

    private Settings(String pauzaUrl, String neighbourhood, int maxMinOrder, int maxAverageDeliveryTime, double minRating, String[] skypeIds, boolean shouldOpenBrowser) {
        this.pauzaUrl = pauzaUrl
        this.neighbourhood = neighbourhood
        this.maxMinOrder = maxMinOrder
        this.maxAverageDeliveryTime = maxAverageDeliveryTime
        this.minRating = minRating
        this.skypeIds = skypeIds
        this.shouldOpenBrowser = shouldOpenBrowser
    }

    def createFilter() {
        {Restaurant restaurant ->
            restaurant.minOrder <= maxMinOrder &&
                    restaurant.averageDeliveryMinutes <= maxAverageDeliveryTime &&
                    restaurant.rating > minRating
        }
    }

}
