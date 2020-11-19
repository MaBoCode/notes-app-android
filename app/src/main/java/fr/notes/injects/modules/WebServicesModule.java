package fr.notes.injects.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import fr.notes.core.note.webservices.NoteClient;
import fr.notes.core.note.webservices.NoteClientRetrofit;
import fr.notes.injects.bus.AppBus;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Module
public class WebServicesModule {

    private Context context;

    public WebServicesModule(Context context) {
        this.context = context;
    }

    private Retrofit createRetrofit(OkHttpClient okHttpClient) {
        String serverUrl = "http://localhost:3000/api/";

        return new Retrofit.Builder()
                .baseUrl(serverUrl)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    public NoteClient provideNoteClient(AppBus bus, Retrofit retrofit) {
        return new NoteClientRetrofit(bus, retrofit, context);
    }

}