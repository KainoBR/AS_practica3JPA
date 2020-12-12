/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos;

import Controller.FrontCommand;
import EJB.Log;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.InitialContext;

/**
 *
 * @author Miki
 */
public class addUserTimer extends FrontCommand {

    static String MSG = "#=== addUserTimer";

    @Override
    public void process() {

        try {
            Log log;
            log = (Log) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/Log");
            log.addLogs(MSG);

            int miliseconds = Integer.parseInt(request.getParameter("miliseconds"));
            boolean loop = ("on").equals(request.getParameter("loop"));
            /*
            log.setLoop(loop);
            log.setMiliseconds(miliseconds * 1000);

            log.setUserTimer();
             */
            forward("/showSystemLog.jsp");
        } catch (Exception ex) {
            Logger.getLogger(addUserTimer.class.getName()).log(Level.SEVERE, null, ex);
            forward("/commandErrorPage.jsp");
        }

    }

}
