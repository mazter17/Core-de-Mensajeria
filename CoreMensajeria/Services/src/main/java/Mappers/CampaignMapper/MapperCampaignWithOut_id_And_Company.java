package Mappers.CampaignMapper;

import DTO.M03_DTO.DTOCampaignWithOut_id_And_Company;

import Entities.Entity;
import Entities.Factory.EntityFactory;
import Entities.M03_Campaign.Campaign;
import Factory.DTOFactory;
import Mappers.GenericMapper;

import java.util.ArrayList;
import java.util.List;

public class MapperCampaignWithOut_id_And_Company extends GenericMapper <DTOCampaignWithOut_id_And_Company> {

    @Override
    public DTOCampaignWithOut_id_And_Company CreateDto(Entity entity) {
        try {
            DTOCampaignWithOut_id_And_Company dto = null;
            Campaign _cam = (Campaign) entity;
            dto = DTOFactory.CreateDtoCampaignWithOut_id_And_Company(_cam.get_nameCampaign(), _cam.get_descCampaign(),
                                                                     _cam.get_statusCampaign(), _cam.get_startCampaign()
                                                                     , _cam.get_endCampaign());

            return dto;
        }
        catch (Exception e) {

            throw e;
        }
    }

    @Override
    public List<DTOCampaignWithOut_id_And_Company> CreateDtoList(List<Entity> entities) {
        try
        {
            ArrayList<DTOCampaignWithOut_id_And_Company> dtos = new ArrayList<>();

            for (Entity _cam : entities) {
                dtos.add( CreateDto( _cam ) );
            }

            return dtos;

        }
        catch (Exception e)
        {
            throw e;
        }
    }

    @Override
    public List<Entity> CreateEntityList(List<DTOCampaignWithOut_id_And_Company> dtos) {
        try
        {
            ArrayList<Entity> Campaign = new ArrayList<>();

            for (DTOCampaignWithOut_id_And_Company _dtocam : dtos) {
                Campaign.add ( CreateEntity ( _dtocam ) );
            }

            return Campaign ;

        }
        catch (Exception e)
        {
            throw e;
        }
    }

    @Override
    public Entity CreateEntity(DTOCampaignWithOut_id_And_Company dto) {
        try {
            Campaign _cam = EntityFactory.CreateCampaignWithOut_ID_Company(dto.get_nameCampaign(),
                                                                       dto.get_descCampaign(), dto.get_statusCampaign(),
                                                                       dto.get_startCampaign(), dto.get_endCampaign());
            return _cam ;

        }
        catch (Exception e){
            throw e;
        }
    }
}