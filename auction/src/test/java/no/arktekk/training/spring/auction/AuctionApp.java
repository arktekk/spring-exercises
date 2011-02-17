package no.arktekk.training.spring.auction;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.File;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class AuctionApp {

    public static void main(String[] args) throws Exception {
        new AuctionApp().start();
    }

    public void start() throws Exception {
        Server server = new Server(8080);

        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        webapp.setWar(getWebAppDir());

        ContextHandlerCollection contexts = new ContextHandlerCollection();
        contexts.setHandlers(new Handler[]{webapp});

        server.setHandler(contexts);

        server.start();
        server.join();
    }

    /**
     * *********************************************************************************
     * Since IntelliJ have a stupid way of resolving which directory to start an app in..
     * Well we have to convice it to choose correctly :)
     * **********************************************************************************
     */
    private String getWebAppDir() {
        File dir = new File(getBaseDir(this.getClass()), "src/main/webapp");
        if (!dir.exists()) {
            throw new RuntimeException("Unable to find web application directory");
        }
        return dir.toString();
    }

    private File getBaseDir(Class aClass) {
        String basedir = System.getProperty("basedir");
        if (basedir != null) {
            return new File(basedir);
        } else {
            File file = new File(aClass.getProtectionDomain().getCodeSource().getLocation().getPath());
            if (!file.exists()) {
                throw new RuntimeException("Unable to find basedir");
            }
            while (!new File(file, "pom.xml").exists()) {
                file = file.getParentFile();
                if (file == null) {
                    throw new RuntimeException("Unable to find basedir");
                }
            }
            return file;
        }
    }
}
