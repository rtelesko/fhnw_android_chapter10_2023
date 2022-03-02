package com.example.lotterymvvm;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class AppViewModel extends BaseObservable {

    // The Data Binding Library generates a class named BR in the module package
    // which contains the IDs of the resources used for data binding.

    // Creating object of Model class
    private Model model;

    // String variables for toast messages
    private String successMessage = "Login successful";
    private String errorMessage = "Username or Password is not valid";

    @Bindable
    // String variable for toast message
    private String toastMessage = null;

    // Constructor of ViewModel class
    public AppViewModel() {

        // Instantiating object of model class
        model = new Model("", "");
    }

    // Getter and setter methods for toast message
    public String getToastMessage() {
        return toastMessage;
    }

    private void setToastMessage(String toastMessage) {
        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }

    // Getter and setter methods for username variable
    @Bindable
    public String getUsername() {
        return model.getUsername();
    }

    public void setUsername(String username) {
        model.setUsername(username);
        notifyPropertyChanged(BR.username);
    }

    // Getter and setter methods for password variable
    @Bindable
    public String getPassword() {
        return model.getPassword();
    }

    public void setPassword(String password) {
        model.setPassword(password);
        notifyPropertyChanged(BR.password);
    }

    // Actions to be performed when user clicks the LOGIN button
    public void onButtonClicked() {
        if (isValid())
            setToastMessage(successMessage);
        else
            setToastMessage(errorMessage);
    }

    // Check for validity
    public boolean isValid() {
        return getUsername().length() >= 5 && getPassword().equals("admin");
    }
}
