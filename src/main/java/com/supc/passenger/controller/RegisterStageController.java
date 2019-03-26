package com.supc.passenger.controller;

import com.supc.passenger.StaticRes;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterStageController implements ControlledStage, Initializable {
    StageController stageController;

    public void setStageController(StageController s) {
        this.stageController = s;
    }

    public void initialize(URL location, ResourceBundle resouces) {
    }

    public void quit() {
        stageController.getStage(StaticRes.registerViewID).close();
    }
}
