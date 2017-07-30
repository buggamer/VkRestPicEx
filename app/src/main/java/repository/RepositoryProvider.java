package repository;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

/**
 * Created by DAIM on 22.07.2017.
 */

public class RepositoryProvider {
    private static VKRepository sVKRepository;

    private RepositoryProvider(){}

    @NonNull
    public static VKRepository provideVKRepository(){
        if(sVKRepository == null){
            sVKRepository = new DefaultVKRepository();
        }
        return sVKRepository;
    }

    public static void setVKRepository(@NonNull VKRepository vkRepository){
        sVKRepository = vkRepository;
    }

    @MainThread
    public static void init(){
        sVKRepository = new DefaultVKRepository();
    }
}
