/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos;

import Controller.FrontCommand;
import EJB.Log;
import EJB.Stats;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Miki
 */
public class showPageStats extends FrontCommand {

    static String MSG = "#=== showPageData";

    @Override
    public void process() {

        try {
            Log log;
            log = (Log) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/Log");
            log.addLogs(MSG);

            Stats stats;
            stats = (Stats) InitialContext.doLookup("java:global/DigitalReading/DigitalReading-ejb/Stats");
            
            HttpSession session = (HttpSession) request.getSession();

            session.setAttribute("stats", stats);

            forward("/showPageStats.jsp");
        } catch (Exception ex) {
            Logger.getLogger(showPageStats.class.getName()).log(Level.SEVERE, null, ex);
            forward("/commandErrorPage.jsp");
        }

    }

}
