package los.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class mainModel {
    private BooleanProperty LoggedIn = new SimpleBooleanProperty(false);

    public boolean isLoggedIn() {
        return LoggedIn.get();
    }

    public BooleanProperty loggedInProperty() {
        return LoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.LoggedIn.set(loggedIn);

    }

    public mainModel() {}

    public mainModel(boolean loggedIn) {
        LoggedIn = new SimpleBooleanProperty(loggedIn);
    }
}
