/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos;

import Controller.FrontCommand;
import EJB.UserFacade;
import EJB.Log;
import EJB.StatefulBeans;
import EJB.Stats;
import entities.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Miki
 */
public class updateUser extends FrontCommand {

    static String MSG = "#=== updateUser";

    @Override
    public void process() {

        System.out.println(MSG);
        try {

            Log log;
            log = (Log) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/Log");
            log.addLogs(MSG);

            Stats stats;
            stats = (Stats) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/Stats");

            StatefulBeans allStatefulBean;
            allStatefulBean = (StatefulBeans) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/StatefulBeans");

            UserFacade userDB;
            userDB = (UserFacade) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/UserFacade");

            // proces()
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            
            User newUser = updateUserForm(user);

            userDB.edit(newUser);
            
            
            user = userDB.find(user.getId());
            session.setAttribute("user", user);

            stats.removeUser(user.toString());
            stats.addUser(newUser.toString());

            allStatefulBean.updateUser(user.toString(), newUser.toString());

            forward("/showMyProfile.jsp");

        } catch (Exception e) {
            System.out.println("ERROR en" + MSG);
            Logger.getLogger(updateUser.class.getName()).log(Level.SEVERE, null, e);
            forward("/commandErrorPage.jsp");
        }
    }

    private User updateUserForm(User oldUser) {
        String name = request.getParameter("username");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        return new User(oldUser.getId(), name, lastname, email, password, oldUser.getAdmin(), oldUser.getAccount());
    }
}
