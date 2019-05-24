/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import static com.codename1.ui.events.ActionEvent.Type.Log;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.Map;

/**
 *
 * @author asus
 */
public class AuthenticationService {

    public boolean login(String username, String password) {
        try {
            ConnectionRequest con = new ConnectionRequest();
            String Url = "http://localhost/mySmartStartSymphony/web/app_dev.php/api/user/login/" + username + "/" + password;
            con.setUrl(Url);

            con.addResponseListener((e) -> {
                String str = new String(con.getResponseData());//Récupération de la réponse du serveur
                //Map<String, Object> result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(con.getResponseData()), "UTF-8"));

                //Map<String, Object> response = (Map<String, Object>) result.get("response");
            });
            NetworkManager.getInstance().addToQueue(con);
            System.out.println("yes!");
        } catch (Exception err) {
            System.out.println(err);
            return false;
        }
        return false;

    }
}
