package com.example.lotterymvvm;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class AppViewModel extends BaseObservable {

    // The Data Binding Library generates a class named BR in the module package
    // which contains the IDs of the resources used for data binding.

    // creating object of Model class
    private Model model;

    // string variables for toast messages
    private String successMessage = "Login successful";
    private String errorMessage = "Username or Password is not valid";

    @Bindable
    // string variable for toast message
    private String toastMessage = null;

    // constructor of ViewModel class
    public AppViewModel() {

        // instantiating object of model class
        model = new Model("", "");
    }

    // getter and setter methods for toast message
    public String getToastMessage() {
        return toastMessage;
    }

    private void setToastMessage(String toastMessage) {
        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }

    // getter and setter methods for username variable
    @Bindable
    public String getUsername() {
        return model.getUsername();
    }

    public void setUsername(String username) {
        model.setUsername(username);
        notifyPropertyChanged(BR.username);
    }

    // getter and setter methods for password variable
    @Bindable
    public String getPassword() {
        return model.getPassword();
    }

    public void setPassword(String password) {
        model.setPassword(password);
        notifyPropertyChanged(BR.password);
    }

    // actions to be performed when user clicks the LOGIN button
    public void onButtonClicked() {
        if (isValid())
            setToastMessage(successMessage);
        else
            setToastMessage(errorMessage);
    }

    // check for validity
    public boolean isValid() {
        return getUsername().length() >= 5 && getPassword().equals("admin");
    }
}
