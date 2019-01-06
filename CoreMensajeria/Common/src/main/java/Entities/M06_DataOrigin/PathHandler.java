package Entities.M06_DataOrigin;

import Entities.M02_Company.Company;
import Entities.M02_Company.CompanyDAO;
import Entities.M08_Validation.WatchDirectory;
import Exceptions.CompanyDoesntExistsException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.util.ArrayList;

@WebListener
public class PathHandler implements ServletContextListener {

    private static String ORIGIN_HOME = System.getProperty("user.home") + "/CoreMensajeria";
    private static String ORIGIN_HOME_INBOX = ORIGIN_HOME + "/Inbox";
    private static String ORIGIN_HOME_TRASH = ORIGIN_HOME + "/Trash";
    private ArrayList<String> _paths = new ArrayList<>();

    public PathHandler() {
    }

    /**
     * Metodo que corre cuando se inicializa el servidor (crea los correspondientes directorios)
     * @param servletContextEvent el ServletContextEvent que esta siendo manejado
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        CompanyDAO companyDAO = new CompanyDAO();

        this.createDirectory(ORIGIN_HOME);
        this.createDirectory(ORIGIN_HOME_INBOX);
        this.createDirectory(ORIGIN_HOME_TRASH);

        try {
            this.createOriginDirectories(ORIGIN_HOME_INBOX,companyDAO.companyListAll());
            this.createOriginDirectories(ORIGIN_HOME_TRASH,companyDAO.companyListAll());
            System.out.println("--------------->PATHS CREATED");

            Thread myThread = new Thread(WatchDirectory.getInstance(_paths));
            myThread.setDaemon(true);
            myThread.start();

        } catch (CompanyDoesntExistsException e) {
            System.out.println("Error creating paths");
        }
    }

    /**
     * Metodo que corre cuando se inicializa el servidor (debido a un Listener)
     * @param servletContextEvent el ServletContextEvent que esta siendo manejado
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    /**
     *
     * @param path direccion de path
     */
    private void createDirectory(String path){
        File pathFile = new File(path);
        if (!pathFile.exists()){
            pathFile.mkdir();
        }
    }

    /**
     *
     * @param rootPath direccion raiz de path
     * @param companies Lista (array) de companias
     */
    private void createOriginDirectories(String rootPath, ArrayList<Company> companies){
        for (Company company : companies) {
            _paths.add(rootPath + "/" + company.get_link());
            this.createDirectory(rootPath + "/" + company.get_link());
        }
    }

    /**
     *
     * @param company objeto compania
     * @return direccion de path generada para la compania senalada
     */
    public String generatePath(Company company){
        return company.get_name().replaceAll("\\s","") + "_" + Encrypter.getCurrentDatePathFormat();
    }

    public static String getInboxPath(){
        return ORIGIN_HOME_INBOX;
    }

    public static String getTrashPath(){
        return ORIGIN_HOME_TRASH;
    }

    public ArrayList<String> get_paths() {
        return _paths;
    }

    public void set_paths(ArrayList<String> _paths) {
        this._paths = _paths;
    }
}
