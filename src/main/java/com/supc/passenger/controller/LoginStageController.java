package com.supc.passenger.controller;


import com.supc.passenger.StaticRes;
import javafx.fxml.Initializable;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginStageController implements ControlledStage, Initializable {
    StageController stageController;

    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    public void initialize(URL location, ResourceBundle resouces) {
    }

    public void eventLogin() {
        stageController.setStage(StaticRes.mainViewID, StaticRes.loginViewID);
    }

    public void eventRegister() {
        stageController.setStage(StaticRes.registerViewID, StaticRes.loginViewID);
    }
}
