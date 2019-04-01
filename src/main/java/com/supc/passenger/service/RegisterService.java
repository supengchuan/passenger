package com.supc.passenger.service;

import com.supc.CLSignature.CLPK;
import com.supc.CLSignature.SignatureBodyBytes;
import com.supc.Entity.Message;
import com.supc.Entity.MessageType;
import com.supc.Entity.RealNameUser;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


/**
 * 这里需要做的事情就是处理在注册的时候需要的服务器，实名注册、假名注册
 * 当然首先需要获取的是CL的公钥信息
 */
public class RegisterService implements MessageType {


    public RegisterService() {

    }


    public CLPK getCLPK() {
        ObjectInputStream is = null;
        ObjectOutputStream os = null;
        try {
            Socket socket = new Socket("localhost", 10000);
            System.out.println("获取公钥。。。。。。");
            os = new ObjectOutputStream(socket.getOutputStream());
            os.writeObject(new Message(GET_CL_PK, null));
            os.flush();

            is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            Message m = (Message) is.readObject();
            if (m.getType() == GET_CL_PK && m.getBody() instanceof CLPK) {
                System.out.println("获得了公钥。。。。");
                //Pairing p = PairingFactory.getPairing("a.properties");
                CLPK clpk = (CLPK) m.getBody();
                //Element g = p.getG1().newElementFromBytes(clpk.getG());
                //System.out.println(g);
                return clpk;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public SignatureBodyBytes realNameRegSevice(RealNameUser user) {
        ObjectInputStream is = null;
        ObjectOutputStream os = null;
        try {
            Socket socket = new Socket("localhost", 10000);
            System.out.println("实名注册，获取CL签名.....");
            os = new ObjectOutputStream(socket.getOutputStream());
            os.writeObject(new Message(REAL_NAME_REG, user));
            os.flush();

            is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            Message m = (Message) is.readObject();
            if (m.getType() == REAL_NAME_REG && m.getBody() instanceof SignatureBodyBytes) {
                System.out.println("获得了服务器的CL签名.....");
                SignatureBodyBytes bodyBytes = (SignatureBodyBytes) m.getBody();
                return bodyBytes;
            }
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
            System.out.println("找不到类........\n");
            e.printStackTrace();
        }

        return null;
    }
}
