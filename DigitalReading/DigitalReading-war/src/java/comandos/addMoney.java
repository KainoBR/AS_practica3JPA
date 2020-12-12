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
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Miki
 */
public class addMoney extends FrontCommand {

    static String MSG = "#=== addMoney";

    @Override
    public void process() {

        System.out.println(MSG);

        try {

            Log log;
            log = (Log) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/Log");
            log.addLogs(MSG);

            UserFacade userDB;
            userDB = (UserFacade) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/UserFacade");

            HttpSession session = request.getSession();

            User user = (User) session.getAttribute("user");

            int userID = user.getId();

            double money = Double.parseDouble(request.getParameter("deposit"));

            user.setAccount(user.getAccount() + money);

            //userDB.changeAccount(userID, user.getAccount());
            userDB.edit(user);
            //user = userDB.find(userID);
            
            session.setAttribute("user", user);

            forward("/showMyProfile.jsp");

        } catch (Exception e) {
            System.out.println("ERROR en" + MSG);
            Logger.getLogger(addMoney.class.getName()).log(Level.SEVERE, null, e);
            forward("/commandErrorPage.jsp");
        }
    }

}
