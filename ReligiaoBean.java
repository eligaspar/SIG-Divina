/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean.grlbean;

import entidade.GrlReligiao;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import sessao.GrlReligiaoFacade;
import util.Mensagem;

/**
 *
 * @author Ornela F. Boaventura
 */
@ManagedBean
@SessionScoped
public class ReligiaoBean
{

    @Resource
    private UserTransaction userTransaction;

    @EJB
    private GrlReligiaoFacade religiaoFacade;

    /**
     *
     * Entidades
     */
    private GrlReligiao religiao;

    /**
     * Creates a new instance of ReligiaoBean
     */
    public ReligiaoBean ()
    {
    }

    public GrlReligiao getReligiao ()
    {
        if (this.religiao == null)
        {
            this.religiao = new GrlReligiao();
        }

        return religiao;
    }

    public void setReligiao (GrlReligiao religiao)
    {
        this.religiao = religiao;
    }

    public String create ()
    {
        try
        {
            userTransaction.begin();
            religiaoFacade.create(religiao);
            userTransaction.commit();
            Mensagem.sucessoMsg("Religião guardada com sucesso!");
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

        religiao = null;

        return null;
    }

    public String edit ()
    {
        try
        {
            userTransaction.begin();
            if (religiao.getPkIdReligiao()== null)
            {
                throw new NullPointerException("PK -> NULL");
            }
            religiaoFacade.edit(religiao);
            userTransaction.commit();
            Mensagem.sucessoMsg("Religião editada com sucesso!");
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

        religiao = null;

        return null;
    }

    public List<GrlReligiao> findAll ()
    {
        return religiaoFacade.findAll();
    }

    public String limpar()
    {
        religiao = null;
        return "religiaoRh?faces-redirect=true";
    }

}
