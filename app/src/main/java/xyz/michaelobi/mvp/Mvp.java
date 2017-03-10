package xyz.michaelobi.mvp;

/**
 * Created by Michael on 10/11/2016.
 */

public interface Mvp {

    public interface View {
    }

    public interface Presenter<V extends Mvp.View> {
        void attachView(V view);

        void detachView();
    }
}