/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;

/**
 *
 * @author Miki
 */
@Startup
@Singleton
@LocalBean
public class Log {

    static String classMSG = "Log";

    private List<String> logs;
    int size;

    public Log() {
        System.out.println(classMSG + "::constructor()");
        size = 0;
        this.logs = new ArrayList<>();
    }

    /*
    @Resource
    TimerService userTimer;

    //la variable loop permite indicar al usuario si quiere, o no, que se 
    //repita el timer
    private boolean loop;

    public void setLoop(boolean loop) {
        System.out.println(classMSG + "::setLoop(" + loop + ")");
        this.loop = loop;
    }

    public void setMiliseconds(int miliseconds) {
        System.out.println(classMSG + "::setMiliseconds(" + miliseconds + ")");
        this.miliseconds = miliseconds;
    }
    private long miliseconds;

    public void setUserTimer() {
        System.out.println(classMSG + "::setUserTimer()");
        userTimer.createSingleActionTimer(miliseconds,
                new TimerConfig());
    }

    @Timeout
    public void userTimeout(Timer timer) {
        size++; // para que no afecte al @Schedule
        addLogs(" ----- User time out ----- ");
        if (loop) {
            userTimer.createSingleActionTimer(miliseconds,
                    new TimerConfig());
        }
    }
     */
    /**
     * Timer automático que comprueba la inactividad
     *
     * @param timer
     */
    //  @Schedule(second = "*/5", minute = "*", hour = "*")
    /*  public void checkLog(Timer timer) {
        if (size == logs.size()) {
                this.addLogs("El sistema está inactivo");
        } else {
            size = logs.size();
        }
    }
     */
    /**
     * Vacia el List<String> de logs
     */
    public void clearLogs() {
        System.out.println(classMSG + "::clearLogs()");
        logs.clear();
    }

    /**
     * @return Todos los logs en formato List<String>
     */
    public List<String> getLogs() {
        System.out.println(classMSG + "::getLogs()");
        return logs;
    }

    public void addLogs(String newlog) {
        System.out.println(classMSG + "::addLogs(" + newlog + ")");
        logs.add(newlog);
    }

    @PostConstruct
    public void init() {
        System.out.println(classMSG + "::init() - @PostConstruct ");
    }

    @PreDestroy
    public void kill() {
        System.out.println(classMSG + "::kill() - @PreDestroy ");
    }
    
}
