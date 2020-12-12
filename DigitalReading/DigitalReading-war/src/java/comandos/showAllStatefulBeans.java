/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos;

import Controller.FrontCommand;
import EJB.Log;
import EJB.StatefulBeans;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Miki
 */
public class showAllStatefulBeans extends FrontCommand {

    static String MSG = "#=== showAllStatefulBeans";

    @Override
    public void process() {

// es posible que este método sobre debido a la naturaleza del singleton
        System.out.println(MSG);

        try {

            Log log;
            log = (Log) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/Log");
            log.addLogs(MSG);

            StatefulBeans allStatefulBean;
            allStatefulBean = (StatefulBeans) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/StatefulBeans");

            HttpSession session = request.getSession();

            session.setAttribute("statefulBean", allStatefulBean);

            forward("/showAllStatefullBeans.jsp");

        } catch (Exception e) {
            System.out.println("ERROR en " + MSG);
            Logger.getLogger(addBookToBookshelf.class.getName()).log(Level.SEVERE, null, e);
            forward("/commandErrorPage.jsp");
        }
    }

}
