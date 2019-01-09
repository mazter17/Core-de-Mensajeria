package M04_Integrator;

import Entities.Entity;
import Entities.EntityFactory;
import Entities.M04_Integrator.*;
import Exceptions.ChannelNotFoundException;
import Exceptions.DatabaseConnectionProblemException;
import Exceptions.IntegratorNotFoundException;
import Persistence.M04_Integrator.DAOIntegrator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DAOIntegratorTest {
    private static DAOIntegrator _integratorDAO;
    private static ArrayList<Entity> _integratorList;
    private static Integrator _movistar;
    private static Integrator _integrator;
    private static MessageIntegrator _messageIntegrator;
    boolean status;


    @BeforeAll
    public static void before() {

        _integratorList = new ArrayList<>();
        _movistar = EntityFactory.CreateIntegrator("MOVISTAR",1, "Movistar", 13.4f,
                25 , "oqiwueyeiu", true);
        _integrator = null;
        _messageIntegrator = new MessageIntegrator("TestMsg", "TestDir", "TestId");
        _messageIntegrator.setSend(true);

    }

    @BeforeEach
    public void init() {
        _integratorDAO = new DAOIntegrator();
    }

    /**
     * Prueba que nos permite  saber si la lista de
     * los integradores es diferente a Null.
     */
    @Test
    public void ListIntegratorTest() {
        try {
            _integratorList = _integratorDAO.listIntegrator();
            assertNotNull(_integratorList);
        } catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prueba que nos permite  saber si se estan
     * obteniendo correctamente los atributos de
     * cada integrador desde la base de datos.
     */
    @Test
    public void ConcreteIntegratorTest() {
        try {
            _integrator = (Integrator) _integratorDAO.getConcreteIntegrator(1);
            assertNotNull(_integrator);
            assertEquals(_movistar.getNameIntegrator(), _integrator.getNameIntegrator());
            assertEquals(_movistar.getApiIntegrator(), _integrator.getApiIntegrator());
            assertEquals(_movistar.getIdIntegrator(), _integrator.getIdIntegrator());
            assertEquals(_movistar.getThreadCapacity(), _integrator.getThreadCapacity());
            assertEquals(_movistar.getMessageCost(), _integrator.getMessageCost());
        } catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
        } catch (IntegratorNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prueba a la Excepcion de Integrador no encontrado
     * realizando una consulta a la base de datos a un id
     * no existente.
     */

    @Test
    public void IntegratorNotFoundExceptionTest() {
        assertThrows(IntegratorNotFoundException.class, () -> {
            _integrator = (Integrator) _integratorDAO.getConcreteIntegrator(15);
        });
    }

    /**
     * Prueba que nos permite verificar que un integrador
     * se encuentre en estado deshabilitado.
     */
    @Test
    public void IntegratorDisabledTest() {
        try {
            _integrator = (Integrator)_integratorDAO.getConcreteIntegrator(1);
            status = _integrator.isEnabled();
            _integratorDAO = new DAOIntegrator();
            _integratorDAO.disableIntegrator(1);
            _integratorDAO = new DAOIntegrator();
            _integrator = (Integrator)_integratorDAO.getConcreteIntegrator(1);
            if (status) {
                _integratorDAO = new DAOIntegrator();
                _integratorDAO.enableIntegrator(1);
            }
            assertEquals(false, _integrator.isEnabled());
        } catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
        } catch (IntegratorNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prueba que nos permite verificar que un integrador
     * se encuentre en estado habilitado.
     */

    @Test
    public void IntegratorEnableTest() {
        try {
            _integrator = (Integrator)_integratorDAO.getConcreteIntegrator(1);
            status = _integrator.isEnabled();
            _integratorDAO = new DAOIntegrator();
            _integratorDAO.enableIntegrator(1);
            _integratorDAO = new DAOIntegrator();
            _integrator = (Integrator)_integratorDAO.getConcreteIntegrator(1);
            if (!status) {
                _integratorDAO = new DAOIntegrator();
                _integratorDAO.disableIntegrator(1);
            }
            assertEquals(true, _integrator.isEnabled());
        } catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
        } catch (IntegratorNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * Prueba que nos permite  saber si la lista de
     * los integradores por canales es diferente a Null.
     */
    @Test
    public void TestIntegradorsByChannel() {
        try {
            _integratorList = _integratorDAO.listIntegratorByChannel(1);
            assertNotNull(_integratorList);
        } catch (DatabaseConnectionProblemException e) {
            e.printStackTrace();
        } catch (ChannelNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * Prueba de la excepción, ejecutando el método de buscar un integrador
     * cuando no se encuentra un canal.
     */

    @Test
    public void channelNotFoundExceptionTest() {
        assertThrows(ChannelNotFoundException.class, () -> {
            _integratorList = _integratorDAO.listIntegratorByChannel(15);
        });
    }
    /**
     * Prueba que nos permite verificar que un integrador
     * este realizando el envio de los mensajes correctamente.
     */
/*
    @Test
    public void sendMessageTest() {
        MessageIntegrator testMessage = _movistar.sendMessage("TestMsg", "TestDir", "TestId");
        assertEquals(_messageIntegrator, testMessage);
    }*/
}