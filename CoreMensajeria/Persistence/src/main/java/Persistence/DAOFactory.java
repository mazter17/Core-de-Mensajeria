package Persistence;

import Persistence.M01_Login.GetUserDao;
import Persistence.M09_Statistics.DAOStatistic;
import Persistence.M02_Company.DAOCompany;

public class DAOFactory {

    //region M01

    static public GetUserDao instanciateDaoUser () {
        return new GetUserDao();
    }

    //endregion

    //region M09

    static public DAOStatistic instanciateDaoStatistics(){ return new DAOStatistic(); }

    //endregion

   static public DAOCompany instanciateDaoCompany ( ) { return new DAOCompany(); }

}
