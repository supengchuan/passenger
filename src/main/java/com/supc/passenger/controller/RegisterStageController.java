package com.supc.passenger.controller;

import com.supc.CLSignature.CLPK;
import com.supc.Entity.RealNameUser;
import com.supc.passenger.StaticRes;
import com.supc.passenger.service.RegisterService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterStageController implements ControlledStage, Initializable {
    StageController stageController;

    @FXML
    private TextField userID;
    @FXML
    private TextField phoneNum;
    @FXML
    private TextField password;
    @FXML
    private TextField c;

    private RealNameUser realNameUser;
    private CLPK clpk;
    private RegisterService registerService;

    public void setStageController(StageController s) {
        this.stageController = s;
    }

    //需要先获取CL签名的公共参数，所以先发送一个请求，获取参数
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initialized......");
        registerService = new RegisterService();
        clpk = registerService.getCLPK();
    }


    @FXML
    private void eventRegsiter(ActionEvent event) {
        realNameUser = new RealNameUser();
        realNameUser.setUserId(userID.getText());
        realNameUser.setPhoneNum(phoneNum.getText());
        realNameUser.setPassword(password.getText());

    }

    public void quit() {
        stageController.setStage(StaticRes.loginViewID, StaticRes.registerViewID);
    }
}
