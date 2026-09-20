package com.example.controllers;

import com.example.models.PasswordModel;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InputDialogController {
    
    @FXML private TextField txtLength;
    @FXML private CheckBox chkDigits;
    @FXML private CheckBox chkLetters;
    @FXML private CheckBox chkSpecial;
    @FXML private TextField txtCount;
    
    private PasswordModel model;
    private PasswordGeneratorController mainController;
    
    public void setModel(PasswordModel model) {
        this.model = model;
        loadFromModel();
    }
    
    public void setMainController(PasswordGeneratorController controller) {
        this.mainController = controller;
    }
    
    private void loadFromModel() {
        txtLength.setText(String.valueOf(model.getPasswordLength()));
        chkDigits.setSelected(model.isIncludeDigits());
        chkLetters.setSelected(model.isIncludeLetters());
        chkSpecial.setSelected(model.isIncludeSpecial());
        txtCount.setText(String.valueOf(model.getPasswordCount()));
    }
    
    @FXML
    private void handleSave() {
        try {
            int length = Integer.parseInt(txtLength.getText().trim());
            int count = Integer.parseInt(txtCount.getText().trim());
            
            if (length < 4 || length > 50) {
                mainController.showError("Длина пароля должна быть от 4 до 50 символов");
                return;
            }
            
            if (count < 1 || count > 100) {
                mainController.showError("Количество паролей должно быть от 1 до 100");
                return;
            }
            
            if (!chkDigits.isSelected() && !chkLetters.isSelected() && !chkSpecial.isSelected()) {
                mainController.showError("Выберите хотя бы один набор символов");
                return;
            }
            
            model.setPasswordLength(length);
            model.setIncludeDigits(chkDigits.isSelected());
            model.setIncludeLetters(chkLetters.isSelected());
            model.setIncludeSpecial(chkSpecial.isSelected());
            model.setPasswordCount(count);
            
            model.clearPasswords();
            
            closeDialog();
            
        } catch (NumberFormatException e) {
            mainController.showError("Пожалуйста, введите корректные числовые значения");
        }
    }
    
    @FXML
    private void handleCancel() {
        closeDialog();
    }
    
    private void closeDialog() {
        Stage stage = (Stage) txtLength.getScene().getWindow();
        stage.close();
    }
}