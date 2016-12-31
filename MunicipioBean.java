/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean.grlbean;

import entidade.GrlMunicipio;
import entidade.GrlProvincia;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import sessao.GrlMunicipioFacade;
import util.Mensagem;

/**
 *
 * @author Ornela F. Boaventura
 */
@ManagedBean
@SessionScoped
public class MunicipioBean
{
    @Resource
    private UserTransaction userTransaction;

    @EJB
    private GrlMunicipioFacade municipioFacade;

    /**
     *
     * Entidades
     */
    private GrlMunicipio municipio;

    /**
     * Creates a new instance of SeccaoBean
     */
    public MunicipioBean ()
    {
    }

    public GrlMunicipio getMunicipio ()
    {
        if(this.municipio == null)
        {
            municipio = new GrlMunicipio();
            municipio.setFkIdProvincia(new GrlProvincia());
        }
        
        return municipio;
    }

    public void setMunicipio (GrlMunicipio municipio)
    {
        this.municipio = municipio;
    }
    
    
    public String create ()
    {
        try
        {
            userTransaction.begin();
            municipioFacade.create(municipio);
            userTransaction.commit();
            Mensagem.sucessoMsg("Município guardado com sucesso!");
        }
        catch (Exception e)
        {
            try
            {
                userTransaction.rollback();
                Mensagem.erroMsg(e.toString());
            }
            catch (IllegalStateException | SecurityException | SystemException ex)
            {
                Mensagem.erroMsg("Rollback: " + ex.toString());
            }
        }

        municipio = null;

        return null;
    }

    public String edit ()
    {
        try
        {
            userTransaction.begin();
            if (municipio.getPkIdMunicipio() == null)
            {
                throw new NullPointerException("PK -> NULL");
            }
            int prov = municipio.getFkIdProvincia().getPkIdProvincia();
            municipio.setFkIdProvincia(new GrlProvincia(prov));
            municipioFacade.edit(municipio);
            userTransaction.commit();
            Mensagem.sucessoMsg("Município editado com sucesso! ");
        }
        catch (Exception e)
        {
            try
            {
                userTransaction.rollback();
                Mensagem.erroMsg(e.toString());
            }
            catch (IllegalStateException | SecurityException | SystemException ex)
            {
                Mensagem.erroMsg("Rollback: " + ex.toString());
            }
        }

        municipio = null;

        return null;
    }

    public List<GrlMunicipio> pesquisaPorProvincia ()
    {
        if(municipio == null)
            return null;
        return municipioFacade.pesquisaPorProvincia(municipio.getFkIdProvincia().getPkIdProvincia());
    }
    
    public String limpar()
    {
        municipio = null;
        return "municipioGrl?faces-redirect=true";
    }
}
