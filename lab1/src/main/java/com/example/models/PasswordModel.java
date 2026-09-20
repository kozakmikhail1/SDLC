package com.example.models;

import java.util.ArrayList;
import java.util.List;

import com.example.interfaces.ModelChangeListener;

public class PasswordModel {
    
    private int passwordLength = 8;
    private boolean includeDigits = true;
    private boolean includeLetters = true;
    private boolean includeSpecial = false;
    private int passwordCount = 1;
    private List<String> generatedPasswords = new ArrayList<>();
    
    private List<ModelChangeListener> listeners = new ArrayList<>();
    
    public void addListener(ModelChangeListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }
    
    public void removeListener(ModelChangeListener listener) {
        listeners.remove(listener);
    }
    
    private void notifyListeners() {
        for (ModelChangeListener listener : listeners) {
            listener.onModelChanged();
        }
    }
    
    public int getPasswordLength() {
        return passwordLength;
    }
    
    public void setPasswordLength(int passwordLength) {
        if (this.passwordLength != passwordLength) {
            this.passwordLength = passwordLength;
            notifyListeners();
        }
    }
    
    public boolean isIncludeDigits() {
        return includeDigits;
    }
    
    public void setIncludeDigits(boolean includeDigits) {
        if (this.includeDigits != includeDigits) {
            this.includeDigits = includeDigits;
            notifyListeners();
        }
    }
    
    public boolean isIncludeLetters() {
        return includeLetters;
    }
    
    public void setIncludeLetters(boolean includeLetters) {
        if (this.includeLetters != includeLetters) {
            this.includeLetters = includeLetters;
            notifyListeners();
        }
    }
    
    public boolean isIncludeSpecial() {
        return includeSpecial;
    }
    
    public void setIncludeSpecial(boolean includeSpecial) {
        if (this.includeSpecial != includeSpecial) {
            this.includeSpecial = includeSpecial;
            notifyListeners();
        }
    }
    
    public int getPasswordCount() {
        return passwordCount;
    }
    
    public void setPasswordCount(int passwordCount) {
        if (this.passwordCount != passwordCount) {
            this.passwordCount = passwordCount;
            notifyListeners();
        }
    }
    
    public List<String> getGeneratedPasswords() {
        return new ArrayList<>(generatedPasswords);
    }
    
    public void setGeneratedPasswords(List<String> passwords) {
        this.generatedPasswords = new ArrayList<>(passwords);
        notifyListeners();
    }
    
    public void clearPasswords() {
        this.generatedPasswords.clear();
        notifyListeners();
    }
}