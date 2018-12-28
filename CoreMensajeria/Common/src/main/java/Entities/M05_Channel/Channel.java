package Entities.M05_Channel;

import Entities.M04_Integrator.Integrator;

import java.util.ArrayList;

/**
 * Clase abstracta Channel que nos proporciona los metodos
 * para trabajar con los canales en concreto.
 *
 * @author José Salas
 * @author Manuel Espinoza
 * @author José Cedeño
 */

public abstract class Channel {
    private int idChannel;
    private String nameChannel;
    private String descriptionChannel;
    ArrayList<Integrator> integrators;

    public Channel(int idChannel, String channelName, String channelDescription, ArrayList<Integrator> integrators) {
        this.idChannel = idChannel;
        this.nameChannel = channelName;
        this.descriptionChannel = channelDescription;
        this.integrators = integrators;
    }

    public int getIdChannel() {
        return idChannel;
    }

    public void setIdChannel(int idChannel) {
        this.idChannel = idChannel;
    }

    public String getNameChannel() {
        return nameChannel;
    }

    public void setNameChannel(String nameChannel) {
        this.nameChannel = nameChannel;
    }

    public String getDescriptionChannel() {
        return descriptionChannel;
    }

    public void setDescriptionChannel(String descriptionChannel) {
        this.descriptionChannel = descriptionChannel;
    }

    public ArrayList<Integrator> getIntegrators() {
        return integrators;
    }

    public void setIntegrators(ArrayList<Integrator> integrators) {
        this.integrators = integrators;
    }

}