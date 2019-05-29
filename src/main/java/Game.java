import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;


public class Game {

    final static Logger logger = Logger.getLogger(Game.class);

    public static void try_logger() {
        //BasicConfigurator.configure();
        logger.error("Hello everybody!");
    }
}
