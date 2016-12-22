/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean.grlbean;

import entidade.GrlSexo;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import sessao.GrlSexoFacade;

/**
 *
 * @author gemix
 */
@ManagedBean
@RequestScoped
public class SexoBean
{
    @EJB
    private GrlSexoFacade grlSexoFacade;

    /**
     * Creates a new instance of SexoBean
     */
    public SexoBean()
    {
        grlSexoFacade = new GrlSexoFacade();
    }
    
    public List<GrlSexo> getSexos()
    {
        return grlSexoFacade.findAll();
    }
    
    public int getTotalSexos(){
        return grlSexoFacade.count();
    }
    
}
