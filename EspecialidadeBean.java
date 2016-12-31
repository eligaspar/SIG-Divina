/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean.grlbean;

import entidade.RhProfissao;
import entidade.GrlEspecialidade;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import sessao.GrlEspecialidadeFacade;
import util.Mensagem;

/**
 *
 * @author Ornela F. Boaventura
 */
@ManagedBean
@SessionScoped
public class EspecialidadeBean
{
    @Resource
    private UserTransaction userTransaction;

    @EJB
    private GrlEspecialidadeFacade especialidadeFacade;

    /**
     *
     * Entidades
     */
    private GrlEspecialidade especialidade;

    /**
     * Creates a new instance of EspecialidadeBean
     */
    public EspecialidadeBean ()
    {
    }

    public GrlEspecialidade getEspecialidade ()
    {
        if(this.especialidade == null)
        {
            especialidade = new GrlEspecialidade();
            especialidade.setFkIdProfissao(new RhProfissao());
        }
        
        return especialidade;
    }

    public void setEspecialidade (GrlEspecialidade especialidade)
    {
        this.especialidade = especialidade;
    }
    
    public String create ()
    {
        try
        {
            userTransaction.begin();
            especialidadeFacade.create(especialidade);
            userTransaction.commit();
            Mensagem.sucessoMsg("Especialidade guardada com sucesso!");
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

        especialidade = null;

        return null;
    }

    public String edit ()
    {
        try
        {
            userTransaction.begin();
            if (especialidade.getPkIdEspecialidade()== null)
            {
                throw new NullPointerException("PK -> NULL");
            }
            int dep = especialidade.getFkIdProfissao().getPkIdProfissao();
            especialidade.setFkIdProfissao(new RhProfissao(dep));
            especialidadeFacade.edit(especialidade);
            userTransaction.commit();
            Mensagem.sucessoMsg("Especialidade editada com sucesso! ");
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
        
        especialidade = null;

        return null;
    }

    public List<GrlEspecialidade> pesquisaPorProfissao ()
    {
        if(especialidade == null)
            return null;
        return especialidadeFacade.pesquisaPorProfissao(especialidade.getFkIdProfissao().getPkIdProfissao());
    }
    
    public String limpar()
    {
        especialidade = null;
        return "especialidadeGrl?faces-redirect=true";
    }
}
