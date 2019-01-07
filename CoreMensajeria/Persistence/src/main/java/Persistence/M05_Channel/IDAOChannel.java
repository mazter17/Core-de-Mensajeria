package Persistence.M05_Channel;

import Entities.Entity;
import Exceptions.ChannelNotFoundException;
import Exceptions.DatabaseConnectionProblemException;
import Persistence.IDAO;
import java.util.ArrayList;

public interface IDAOChannel extends IDAO {

    ArrayList<Entity> listChannel() throws DatabaseConnectionProblemException;
    ArrayList<Entity> listIntegratorByChannel(Entity e) throws DatabaseConnectionProblemException,
            ChannelNotFoundException;
}
