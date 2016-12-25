/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessao;

import entidade.RhCategoriaProfissao;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Ornela F. Boaventura
 */
@Stateless
public class RhCategoriaProfissaoFacade extends AbstractFacade<RhCategoriaProfissao>
{

    @PersistenceContext(unitName = "SIG_HDP_Master-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager ()
    {
        return em;
    }

    public RhCategoriaProfissaoFacade ()
    {
        super(RhCategoriaProfissao.class);
    }

    public List<RhCategoriaProfissao> pesquisaPorProfissao (Integer idProfissao)
    {
        TypedQuery<RhCategoriaProfissao> t = this.em.createQuery("SELECT c FROM RhCategoriaProfissao c WHERE c.fkIdProfissao.pkIdProfissao = :idProfissao", RhCategoriaProfissao.class).setParameter("idProfissao", idProfissao);

        List<RhCategoriaProfissao> resultado = t.getResultList();

        return resultado;
    }
}
