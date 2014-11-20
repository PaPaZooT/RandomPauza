final String settingsFilePath = '../settings.xml'
def settings = Settings.fromSettingsXml(settingsFilePath)

def parser = new RestaurantListParser("$settings.pauzaUrl$settings.neighbourhood")
List restaurants = parser.getAllRestaurants()
def filtered = restaurants.findAll(settings.createFilter())

def picker = new RestaurantPicker(filtered)
def restaurant = picker.pickRandom()

//def informer = new SkypeLunchInformer(settings.skypeIds)
//informer.inform(restaurant)

println restaurant
