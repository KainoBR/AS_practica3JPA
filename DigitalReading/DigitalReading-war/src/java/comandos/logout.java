/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos;

import Controller.FrontCommand;
import entities.Cart;
import EJB.Log;
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
public class logout extends FrontCommand {

    static String MSG = "#=== logout";

    @Override
    public void process() {

        System.out.println(MSG);

        try {

            Log log;
            log = (Log) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/Log");
            log.addLogs(MSG);

            Stats stats;
            stats = (Stats) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/Stats");

            HttpSession session = request.getSession();

            User user = (User) session.getAttribute("user");

            Cart cart = (Cart) session.getAttribute("carrito");

            stats.removeUser(user.toString());

            //@remove the 2 stateful bean
            //@remove
            cart.remove();

            //@remove
            //user.remove();

            forward("/index.jsp");

        } catch (Exception e) {
            System.out.println("ERROR en" + MSG);
            Logger.getLogger(logout.class.getName()).log(Level.SEVERE, null, e);
            forward("/commandErrorPage.jsp");
        }
    }

}
