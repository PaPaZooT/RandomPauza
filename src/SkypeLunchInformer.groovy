import com.skype.Chat
import com.skype.Skype

class SkypeLunchInformer {

    def skypeIds

    SkypeLunchInformer(String...skypeIds) {
        this.skypeIds = skypeIds
    }

    void inform(Restaurant restaurant) {
        Chat chat = Skype.chat(skypeIds)
        chat.send(restaurant.url)
    }

}
