import java.awt.Desktop

def settingsFilePath = '../settings.xml'
def settings = Settings.fromSettingsXml(settingsFilePath)

def orderByRating = "?order=2"
def parser = new RestaurantListParser("$settings.pauzaUrl$settings.neighbourhood$orderByRating")
def restaurants = parser.getAllRestaurants()
def filtered = restaurants.findAll(settings.createFilter())

def picker = new RestaurantPicker(filtered)
def restaurant = picker.pickRandom()

def informer = new SkypeLunchInformer(settings.skypeIds)
informer.inform(restaurant)
println restaurant

if(settings.shouldOpenBrowser && Desktop.isDesktopSupported()) {
        Desktop.getDesktop().browse(new URI(restaurant.url))
}