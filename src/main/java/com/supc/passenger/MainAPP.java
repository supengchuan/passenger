package com.supc.passenger;


import com.supc.passenger.controller.StageController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class MainAPP extends Application {

    public static String mainViewID = "MainView";
    public static String mainViewRes = "/view/MainStage.fxml";
    public static String loginViewID = "LoginView";
    public static String loginViewRes = "/view/LoginStage.fxml";

    private StageController stageController;


    /**
     * 程序入口
     * @param  args 输入参数
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

        stageController.loadStage(loginViewID, loginViewRes, StageStyle.UNDECORATED);
        stageController.loadStage(mainViewID, mainViewRes, StageStyle.UNDECORATED);

        stageController.setStage(loginViewID);


    }
}
