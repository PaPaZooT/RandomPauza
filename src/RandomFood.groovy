import org.jsoup.Jsoup

neighbourhood = "zagreb-blato"
htmlDocument = Jsoup.
        connect("http://www.pauza.hr/" + neighbourhood)
        .get()

/*
areaSelect = htmlDocument.getElementById("area_id")
println areaSelect

areaSelectOptions = areaSelect.getElementsByTag("option")
println areaSelectOptions

for (areaSelectOption in areaSelectOptions)
    println areaSelectOption.text()
*/

parser = new RestaurantListParser(htmlDocument)
allRestaurants = parser.getAllRestaurants()
println allRestaurants.size()