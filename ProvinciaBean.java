/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean.grlbean;

import entidade.GrlPais;
import entidade.GrlProvincia;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import sessao.GrlProvinciaFacade;
import util.Mensagem;

/**
 *
 * @author Ornela F. Boaventura
 */
@ManagedBean
@SessionScoped
public class ProvinciaBean
{
    @Resource
    private UserTransaction userTransaction;

    @EJB
    private GrlProvinciaFacade provinciaFacade;

    /**
     *
     * Entidades
     */
    private GrlProvincia provincia;

    /**
     * Creates a new instance of ProvinciaBean
     */
    public ProvinciaBean ()
    {
    }

    public GrlProvincia getProvincia ()
    {
        if(this.provincia == null)
        {
            provincia = new GrlProvincia();
            provincia.setFkIdPais(new GrlPais());
        }
        
        return provincia;
    }

    public void setProvincia (GrlProvincia provincia)
    {
        this.provincia = provincia;
    }
    
    
    public String create ()
    {
        try
        {
            userTransaction.begin();
            provinciaFacade.create(provincia);
            userTransaction.commit();
            Mensagem.sucessoMsg("Província guardada com sucesso!");
        }
        catch (Exception e)
        {
            try
            {
                Mensagem.erroMsg(e.toString());
                userTransaction.rollback();
            }
            catch (IllegalStateException | SecurityException | SystemException ex)
            {
                Mensagem.erroMsg("Rollback: " + ex.toString());
            }
        }

        provincia = null;

        return null;
    }

    public String edit ()
    {
        try
        {
            userTransaction.begin();
            if (provincia.getPkIdProvincia() == null)
            {
                throw new NullPointerException("PK -> NULL");
            }
            int dep = provincia.getFkIdPais().getPkIdPais();
            provincia.setFkIdPais(new GrlPais(dep));
            provinciaFacade.edit(provincia);
            userTransaction.commit();
            Mensagem.sucessoMsg("Província editada com sucesso! ");
        }
        catch (Exception e)
        {
            try
            {
                Mensagem.erroMsg(e.toString());
                userTransaction.rollback();
            }
            catch (IllegalStateException | SecurityException | SystemException ex)
            {
                Mensagem.erroMsg("Rollback: " + ex.toString());
            }
        }

        provincia = null;

        return null;
    }

    public List<GrlProvincia> pesquisaPorPais ()
    {
        if(provincia == null)
            return null;
        return provinciaFacade.pesquisaPorPais(provincia.getFkIdPais().getPkIdPais());
    }
    
    public String limpar()
    {
        provincia = null;
        return "provinciaGrl?faces-redirect=true";
    }
}
