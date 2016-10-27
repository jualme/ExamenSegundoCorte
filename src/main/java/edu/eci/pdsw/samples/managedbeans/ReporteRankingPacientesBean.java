/*
 * Copyright (C) 2016 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.pdsw.samples.managedbeans;

import edu.eci.pdsw.samples.entities.Paciente;
import edu.eci.pdsw.samples.services.ServicesFacade;
import java.sql.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author hcadavid
 */
@ManagedBean(name = "Usuarios")
@SessionScoped
public class ReporteRankingPacientesBean {
    ServicesFacade sf = ServicesFacade.getInstance("applicationconfig.properties");
    int ranking = 0;
    int fechaHora = 2016;
    List<Paciente> p;
    Paciente paciente;
       

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
    public List<Paciente> getP(){        
        return p;
    }
        
    public Paciente getPaciente() {
        return paciente;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public void setFechaHora(int fechaHora) {
        this.fechaHora = fechaHora;
    }

    public int getRanking() {
        return ranking;
    }

    public int getFechaHora() {
        return fechaHora;
    }
       
    public void submit() {
        FacesMessage message = null;
        try{            
            p = sf.topNPacientesPorAnyo(ranking, fechaHora);     
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "Se ha consultado la informacion");
        } catch(Exception e){
            /*e.printStackTrace();*/
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error!!", "No se ha podido consultar");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    
}
