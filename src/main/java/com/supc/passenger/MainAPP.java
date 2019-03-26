package com.supc.passenger;


import com.supc.passenger.controller.StageController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class MainAPP extends Application {

    private StageController stageController;


    /**
     * 程序入口
     *
     * @param args 输入参数
     */
    public static void main(String[] args) {
        //launchApp(MainController.class, LoginStageView.class, args);
        launch(args);
    }

    /**
     * @param primaryStage
     * @throws Exception
     */

    @Override
    public void start(Stage primaryStage) {
        //super.start(stage);
        stageController = new StageController();

        stageController.setPrimaryStage("primaryStage", primaryStage);

        stageController.loadStage(StaticRes.loginViewID, StaticRes.loginViewRes, StageStyle.UNDECORATED);
        stageController.loadStage(StaticRes.mainViewID, StaticRes.mainViewRes, StageStyle.UNDECORATED);
        stageController.loadStage(StaticRes.registerViewID, StaticRes.registerViewRes, StageStyle.UNDECORATED);
        stageController.setStage(StaticRes.loginViewID);


    }
}
