package com.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import com.example.interfaces.ModelChangeListener;
import com.example.models.PasswordModel;
import com.example.utils.PasswordGenerator;

public class PasswordGeneratorController implements ModelChangeListener {
    
    @FXML private Label lblLength;
    @FXML private Label lblDigits;
    @FXML private Label lblLetters;
    @FXML private Label lblSpecial;
    @FXML private Label lblCount;
    @FXML private TextArea txtPasswords;
    @FXML private Button btnGenerate;
    
    private PasswordModel model;
    private PasswordGenerator generator;
    
    @FXML
    public void initialize() {
        model = new PasswordModel();
        generator = new PasswordGenerator();
        
        model.addListener(this);
        
        updateUI();
    }
    
    @FXML
    private void handleEnterData() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/InputDialog.fxml"));
            Parent root = loader.load();
            
            InputDialogController dialogController = loader.getController();
            dialogController.setModel(model);
            dialogController.setMainController(this);
            
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Ввод данных");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.setScene(new Scene(root));
            dialogStage.setResizable(false);
            dialogStage.showAndWait();
            
        } catch (IOException e) {
            e.printStackTrace();
            showError("Ошибка загрузки окна ввода данных: " + e.getMessage());
        }
    }
    
    @FXML
    private void handleGenerate() {
        try {
            var passwords = generator.generatePasswords(
                model.getPasswordLength(),
                model.isIncludeDigits(),
                model.isIncludeLetters(),
                model.isIncludeSpecial(),
                model.getPasswordCount()
            );
            
            model.setGeneratedPasswords(passwords);
            
        } catch (IllegalArgumentException e) {
            showError(e.getMessage());
        }
    }
    
    @Override
    public void onModelChanged() {
        updateUI();
    }
    
    private void updateUI() {
        lblLength.setText(String.valueOf(model.getPasswordLength()));
        lblDigits.setText(model.isIncludeDigits() ? "Да" : "Нет");
        lblLetters.setText(model.isIncludeLetters() ? "Да" : "Нет");
        lblSpecial.setText(model.isIncludeSpecial() ? "Да" : "Нет");
        lblCount.setText(String.valueOf(model.getPasswordCount()));
        
        updatePasswordsDisplay();
        
        btnGenerate.setDisable(false);
    }
    
    private void updatePasswordsDisplay() {
        var passwords = model.getGeneratedPasswords();
        if (passwords.isEmpty()) {
            txtPasswords.clear();
            return;
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < passwords.size(); i++) {
            sb.append(i + 1).append(". ").append(passwords.get(i)).append("\n");
        }
        txtPasswords.setText(sb.toString());
    }
    
    public void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}