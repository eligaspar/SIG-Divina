/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean.grlbean;

import entidade.GrlPais;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import sessao.GrlPaisFacade;
import util.Mensagem;

/**
 *
 * @author Ornela F. Boaventura
 */
@ManagedBean
@SessionScoped
public class PaisBean
{

    @Resource
    private UserTransaction userTransaction;

    @EJB
    private GrlPaisFacade paisFacade;

    /**
     *
     * Entidades
     */
    private GrlPais pais;

    /**
     * Creates a new instance of PaisBean
     */
    public PaisBean ()
    {
    }

    public GrlPais getPais ()
    {
        if (this.pais == null)
        {
            this.pais = new GrlPais();
        }

        return pais;
    }

    public void setPais (GrlPais pais)
    {
        this.pais = pais;
    }

    public String create ()
    {
        try
        {
            userTransaction.begin();
            paisFacade.create(pais);
            userTransaction.commit();
            Mensagem.sucessoMsg("Pais guardado com sucesso!");
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

        pais = null;

        return null;
    }

    public String edit ()
    {
        try
        {
            userTransaction.begin();
            if (pais.getPkIdPais() == null)
            {
                throw new NullPointerException("PK -> NULL");
            }
            paisFacade.edit(pais);
            userTransaction.commit();
            Mensagem.sucessoMsg("Pais editado com sucesso!");
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

        pais = null;

        return null;
    }

    public List<GrlPais> findAll ()
    {
        return paisFacade.findAll();
    }

    public String limpar()
    {
        pais = null;
        return "paisGrl?faces-redirect=true";
    }

}
