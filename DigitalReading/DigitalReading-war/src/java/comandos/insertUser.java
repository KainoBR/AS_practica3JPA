/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos;

import Controller.FrontCommand;
import EJB.UserFacade;
import EJB.Log;
import entities.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.InitialContext;

/**
 *
 * @author Miki
 */
public class insertUser extends FrontCommand {



    //register User
    static String MSG = "#=== insertUser";

    @Override
    public void process() {

        System.out.println(MSG);
        try {

            Log log;
            log = (Log) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/Log");
            log.addLogs(MSG);
            
            UserFacade userDB;
            userDB = (UserFacade) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/UserFacade");


            String name = request.getParameter("username");
            String lastname = request.getParameter("lastname");
            String email = request.getParameter("email");
            String password = request.getParameter("password");

//ModelUser.isExists(name, lastname)
            if (!userDB.isExists(name, lastname)) {
                //ModelUser.register(name, lastname, email, password);
                User newUser = new User(name, lastname, email, password, 0, 0.0);
                userDB.create(newUser);

                forward("/index.jsp");
            } else {
                forward("/register.jsp");
            }

        } catch (Exception e) {
            System.out.println("ERROR en" + MSG);
            Logger.getLogger(insertUser.class.getName()).log(Level.SEVERE, null, e);
            forward("/commandErrorPage.jsp");
        }

    }

}
