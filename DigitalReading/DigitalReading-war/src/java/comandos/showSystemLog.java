/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos;

import Controller.FrontCommand;
import EJB.Log;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Miki
 */
public class showSystemLog extends FrontCommand {

    static String MSG = "#=== showSystemLog";

    @Override
    public void process() {

        try {
            HttpSession session = (HttpSession) request.getSession();

            //Log log = (Log) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/Log");
            Log log;
            log = (Log) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/Log");
            log.addLogs(MSG);

            List<String> logs = log.getLogs();
            session.setAttribute("logs", logs);

            forward("/showSystemLog.jsp");
        } catch (NamingException ex) {
            Logger.getLogger(showSystemLog.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
