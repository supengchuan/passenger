package com.supc.passenger.service;

import com.sun.org.apache.xml.internal.security.algorithms.MessageDigestAlgorithm;
import com.supc.CLSignature.CLPK;
import com.supc.Entity.Message;
import com.supc.Entity.MessageType;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

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

    private ObjectInputStream is;
    private ObjectOutputStream os;

    public RegisterService() {

    }


    public CLPK getCLPK() {
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
                Pairing p = PairingFactory.getPairing("a.properties");
                CLPK clpk = (CLPK) m.getBody();
                Element g = p.getG1().newElementFromBytes(clpk.getG());
                System.out.println(g);
                return clpk;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
