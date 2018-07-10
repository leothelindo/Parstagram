package me.leojlindo.parstagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

import me.leojlindo.parstagram.model.Post;

public class ParseApp extends Application{

    @Override
    public void onCreate(){
        super.onCreate();

        ParseObject.registerSubclass(Post.class);

        final Parse.Configuration configuration = new Parse.Configuration.Builder(this)
                .applicationId("yorkcityfc")
                .clientKey("linkin-park")
                .server("http://leothelindo-fbu-instagram.herokuapp.com/parse")
                .build();
        Parse.initialize(configuration);
    }
}
