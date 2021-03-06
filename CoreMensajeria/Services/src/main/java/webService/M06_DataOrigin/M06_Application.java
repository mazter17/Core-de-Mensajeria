package webService.M06_DataOrigin;

import DTO.M06_DataOrigin.DTOAddApplication;
import Entities.Entity;
import Entities.M06_DataOrigin.AddApplicationData;
import Entities.M06_DataOrigin.Application;
import Logic.Command;
import Logic.CommandsFactory;
import Logic.M06_DataOrigin.CreateApplicationCommand;
import Logic.M06_DataOrigin.UpdateApplicationCommand;
import Mappers.M06_DataOrigin.MapperDTOAddApplication;
import Mappers.MapperFactory;
import com.google.gson.*;
import Exceptions.ApplicationNotFoundException;
import Exceptions.DatabaseConnectionProblemException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/applications")
public class M06_Application {

    private Gson gson = new Gson();
    //    private ApplicationDAO _applicationDAO = new ApplicationDAO();

    //                   GET ENDPOINTS
    //Get all applications endpoint. Path: applications
    /**
     * Path: applications
     * @return Response Builder con los detalles de los Application endpoint
     */
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getApplications(){
        try{
            Command c = CommandsFactory.GetApplication();
            c.execute();
            return Response.ok(gson.toJson(c.Return())).build();
        }
        catch (DatabaseConnectionProblemException e) {
            return Response.status(500).entity(this.generateErrorAsJson(e.getMessage())).build();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    //Get all applications by company ID endpoint. Path: applications/company/{companyID}
    /**
     * Path: applications/company/{companyID}
     * @param companyId recibe el id de la Company que se desea ver con detalle
     * @return Response Builder con los detalles de la Company correspondiente al companyId
     */
    @GET
    @Path("/company/{companyId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getApplications(@PathParam("companyId") int companyId){
        try{
            Command c = CommandsFactory.GetApplicationCompanyId(companyId);
            c.execute();
            return Response.ok(gson.toJson(c.Return())).build();
        }
        catch (DatabaseConnectionProblemException e) {
            return Response.status(500).entity(this.generateErrorAsJson(e.getMessage())).build();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //Get application by application ID endpoint. Path: applications/id/(id)
    /**
     * Path: applications/id/(id)
     * @param id recibe el id de la Application que se desea ver con detalle
     * @return Response Builder con los detalles de la Application correspondiente al id
     */
    @GET
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getApplication(@PathParam("id") int id){
        try{
            Command c = CommandsFactory.GetApplicationId(id);
            c.execute();
            return Response.ok(gson.toJson(c.Return())).build();
        }catch (ApplicationNotFoundException e){
            return Response.status(404).entity(this.generateErrorAsJson(e.getMessage())).build();
        }
        catch (DatabaseConnectionProblemException e) {
            return Response.status(500).entity(this.generateErrorAsJson(e.getMessage())).build();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    //Get application by Token endpoint. Path: applications/token/{token}
    /**
     * Path: applications/token/{token}
     * @param token recibe el token de la Appication que se desea ver con detalle
     * @return Response Builder con los detalles de la Application correspondiente al token
     */
    @GET
    @Path("/token/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getApplication(@PathParam("token") String token){
        try{
            Command c = CommandsFactory.GetApplicationToken(token);
            c.execute();
            return Response.ok(gson.toJson(c.Return())).build();
        }catch (ApplicationNotFoundException e){
            return Response.status(404).entity(this.generateErrorAsJson(e.getMessage())).build();
        }
        catch (DatabaseConnectionProblemException e) {
            return Response.status(500).entity(this.generateErrorAsJson(e.getMessage())).build();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    //                   PUT ENDPOINTS
    //Activate an application by application Id. Path: applications/active/{id}
    /**
     * Path: applications/active/{id}
     * @param id recibe el id de la Application que se desea activar
     * @return Response Builder con un mensaje de activacion exitosa
     */
    @PUT
    @Path("/active/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response activeApplication(@PathParam("id") int id) {
        try {
            Command c = CommandsFactory.UpdateApplication(id , 1);
            c.execute();
            return Response.ok(generateSuccessAsJson("Aplicacion activada exitosamente.",
                    ((UpdateApplicationCommand) c).Return())).build();
        } catch (DatabaseConnectionProblemException e) {
            return Response.status(500).entity(this.generateErrorAsJson(e.getMessage())).build();
        } catch (ApplicationNotFoundException e){
            return Response.status(404).entity(this.generateErrorAsJson(e.getMessage())).build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    //Inactivate an application by application Id. Path: applications/inactive/{id}
    /**
     * Path: applications/inactive/{id}
     * @param id recibe el id de la Application que se desea pausar
     * @return Response Builder con un mensaje de pausa exitosa
     */
    @PUT
    @Path("/inactive/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response inactiveApplication(@PathParam("id") int id) {
        try {
            Command c = CommandsFactory.UpdateApplication(id , 0);
            c.execute();
            return Response.ok(this.generateSuccessAsJson("Aplicacion pausada exitosamente.",
                    ((UpdateApplicationCommand) c).Return())).build();
        } catch (DatabaseConnectionProblemException e) {
            return Response.status(500).entity(this.generateErrorAsJson(e.getMessage())).build();
        } catch (ApplicationNotFoundException e){
            return Response.status(404).entity(this.generateErrorAsJson(e.getMessage())).build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    //                   POST ENDPOINTS
    //Add a new application. Path: applications/
    /**
     * Path: applications/
     * @param application recibe el application (objeto AddApplicationData) con solo los datos que el
     *                    usuario ingresa
     * @return Response Builder con un mensaje de Application creada exitosamente y la creacion de la
     *         misma
     */
    @Path("/")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addApplication(DTOAddApplication application){
        try {
            MapperDTOAddApplication _mapper = MapperFactory.mapperDTOAddAplication();
            Entity _addApplication = _mapper.CreateEntity(application);
            Command c = CommandsFactory.CreateApplication(_addApplication);
            c.execute();
            return Response.ok(this.generateSuccessAsJson("Aplicacion creada exitosamente.",
                    ((CreateApplicationCommand) c).Return())).build();
        } catch (DatabaseConnectionProblemException e) {
            return Response.status(500).entity(this.generateErrorAsJson(e.getMessage())).build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    //                   DELETE ENDPOINTS
    //Delete an application by application Id. Path: applications/{id}
    /*@DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteApplication(@PathParam("id") int id) {
        try {
            _applicationDAO.deleteApplication(id);
            return Response.ok(this.generateSuccessAsJson("Aplicacion eliminada exitosamente.")).build();
        } catch (ApplicationNotFoundException e) {
            return Response.status(404).entity(this.generateErrorAsJson(e.getMessage())).build();
        } catch (DatabaseConnectionProblemException e) {
            return Response.status(500).entity(this.generateErrorAsJson(e.getMessage())).build();
        }
    }*/

    //                   UTILITIES
    //Produces a Json String with a given success message
    /**
     *
     * @param message recibe un mensaje escrito
     * @return json con la respuesta recibida en formato prestablecido
     */
    private String generateSuccessAsJson(String message){
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("_message", message);
        return gson.toJson(jsonResponse);
    }

    /**
     *
     * @param message recibe un mensaje escrito
     * @param application recibe el Application al cual se asociara el mensaje en el json
     * @return json con la respuesta recibida en formato prestablecido
     */
    private String generateSuccessAsJson(String message, Application application){
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("_message", message);
        jsonResponse.add("application", gson.toJsonTree(application));
        return gson.toJson(jsonResponse);
    }

    //Produces a Json String with a given error message
    /**
     *
     * @param message recibe un mensaje escrito
     * @return json con la respuesta recibida en formato prestablecido
     */
    private String generateErrorAsJson(String message){
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("_message", message);
        return gson.toJson(jsonResponse);
    }
}