package com.supc.passenger.controller;

import com.supc.CLSignature.CLPK;
import com.supc.CLSignature.CLSignature;
import com.supc.CLSignature.SignatureBody;
import com.supc.CLSignature.SignatureBodyBytes;
import com.supc.Entity.RealNameUser;
import com.supc.passenger.StaticRes;
import com.supc.passenger.service.RegisterService;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
    private CLPK clpk = null;
    private RegisterService registerService;

    private Pairing pairing;
    private Element g;
    private Element X;
    private Element Y;
    private CLSignature cl;
    private SignatureBody signatureBody;

    public void setStageController(StageController s) {
        this.stageController = s;
    }

    //需要先获取CL签名的公共参数，所以先发送一个请求，获取参数
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initialized......");
        pairing = PairingFactory.getPairing("a.properties");
        registerService = new RegisterService();
        clpk = registerService.getCLPK();

        if (clpk == null) {
            System.out.println("get cl signature pk failed!");
        } else {
            this.g = pairing.getG1().newElementFromBytes(clpk.getG()).getImmutable();
            //System.out.println("get g from sever: "+this.g);
            this.X = pairing.getG1().newElementFromBytes(clpk.getX()).getImmutable();
            this.Y = pairing.getG1().newElementFromBytes(clpk.getY()).getImmutable();
        }
        cl = new CLSignature();
        cl.setCLPK(pairing, this.g, this.X, this.Y);

        signatureBody = new SignatureBody();
    }


    @FXML
    private void eventRegsiter(ActionEvent event) {
        realNameUser = new RealNameUser();
        realNameUser.setUserId(userID.getText());
        realNameUser.setPhoneNum(phoneNum.getText());
        realNameUser.setPassword(password.getText());
        Integer cFromTextField = new Integer(c.getText());
        //从文本框中获得一个整数，初始化Zr下的一个元素，获得c，而C=g^c
        realNameUser.setC(this.g.powZn(pairing.getZr().newElement(cFromTextField)).toBytes());

        SignatureBodyBytes bodyBytes = registerService.realNameRegSevice(realNameUser);

        signatureBody.setA(this.pairing.getG1().newElementFromBytes(bodyBytes.getA()));
        signatureBody.setB(this.pairing.getG1().newElementFromBytes(bodyBytes.getB()));
        signatureBody.setC(this.pairing.getG1().newElementFromBytes(bodyBytes.getC()));



        if (cl.userVerify(signatureBody, cl.getPairing().getZr().newElement(cFromTextField))) {
            System.out.println("ok");
            Alert information = new Alert(Alert.AlertType.INFORMATION, "恭喜您，注册成功！");
            information.setTitle("注册提示...");
            information.setHeaderText("注册...");
            information.showAndWait();
        }

    }

    public void quit() {
        stageController.setStage(StaticRes.loginViewID, StaticRes.registerViewID);
    }
}
