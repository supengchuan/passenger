package com.supc.passenger.controller;


import com.supc.passenger.MainAPP;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginStageController implements ControlledStage, Initializable {
    StageController stageController;

    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    public void initialize(URL location, ResourceBundle resouces){}
    public void eventLogin(){
        stageController.setStage(MainAPP.mainViewID, MainAPP.loginViewID);
    }
}
